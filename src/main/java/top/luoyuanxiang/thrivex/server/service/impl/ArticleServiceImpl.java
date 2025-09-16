package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.luoyuanxiang.thrivex.server.entity.*;
import top.luoyuanxiang.thrivex.server.exception.CustomException;
import top.luoyuanxiang.thrivex.server.mapper.ArticleMapper;
import top.luoyuanxiang.thrivex.server.mapper.CommentMapper;
import top.luoyuanxiang.thrivex.server.service.*;
import top.luoyuanxiang.thrivex.server.vo.ArticleQueryVO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements IArticleService {

    @Resource
    private IArticleCateService articleCateService;
    @Resource
    private IArticleTagService articleTagService;
    @Resource
    private IArticleConfigService articleConfigService;
    @Resource
    private ICateService cateService;
    @Resource
    private ITagService tagService;
    @Resource
    private CommentMapper commentMapper;
    @Override
    @Transactional
    public void add(ArticleEntity article) {
        article.insert();
        addArticleCorrelationData(article);
    }

    @Override
    @Transactional
    public void del(Integer id, Integer isDel) {
        if (isDel != 0 && isDel != 1) {
            throw new RuntimeException("参数有误：请选择是否严格删除");
        }

        ArticleConfigEntity articleConfig = articleConfigService.lambdaQuery()
                .eq(ArticleConfigEntity::getArticleId, id)
                .one();

        // 严格删除：直接从数据库删除
        if (isDel == 0) {
            // 删除文章关联的数据
            delArticleCorrelationData(Collections.singleton(id));

            // 删除当前文章
            removeById(id);
        }

        // 普通删除：更改 is_del 字段，到时候可以通过更改字段恢复
        if (isDel == 1) {
            articleConfig.setIsDel(1);
            articleConfig.updateById();
        }
    }

    @Override
    public void reduction(Integer id) {
        articleConfigService.lambdaUpdate()
                .eq(ArticleConfigEntity::getArticleId, id)
                .set(ArticleConfigEntity::getIsDel, 0)
                .update();
    }

    @Override
    @Transactional
    public void delBatch(List<Integer> ids) {
        removeBatchByIds(ids);
        delArticleCorrelationData(ids);
    }

    @Override
    @Transactional
    public void edit(ArticleEntity article) {
        if (article.getCateIds() == null || article.getCateIds().isEmpty()) {
            throw new RuntimeException("编辑失败：请绑定分类");
        }

        // 删除文章关联的数据
        delArticleCorrelationData(Collections.singleton(article.getId()));

        addArticleCorrelationData(article);
        article.updateById();
    }

    @Override
    public ArticleEntity get(Integer id, String password) {
        ArticleEntity data = bindingData(id);

        String description = data.getDescription();
        String content = data.getContent();
        // todo ps by:laifeng 这里需要优化， 对于角色判断，请将角色逻辑移到controller层，不要在service中进行，而且可以通过aop进行操作，避免重复判断
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = Objects.nonNull(authentication);

        ArticleConfigEntity config = data.getConfig();

        if (data.getConfig().getIsEncrypt() == 0 && !password.isEmpty()) {
            throw new CustomException(610, "该文章不需要访问密码");
        }

        // 管理员可以查看任何权限的文章
        if (!isAdmin) {
            if (data.getConfig().getIsDel() == 1) {
                throw new CustomException(404, "该文章已被删除");
            }

            if ("hide".equals(config.getStatus())) {
                throw new CustomException(611, "该文章已被隐藏");
            }

            // 如果有密码就必须通过密码才能查看
            if (data.getConfig().getIsEncrypt() == 1) {
                // 如果需要访问密码且没有传递密码参数
                if (password.isEmpty()) {
                    throw new CustomException(612, "请输入文章访问密码");
                }

                data.setDescription("该文章需要密码才能查看");
                data.setContent("该文章需要密码才能查看");

                // 验证密码是否正确
                // if (config.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
                if (config.getPassword().equals(password)) {
                    data.setDescription(description);
                    data.setContent(content);
                } else {
                    throw new CustomException(613, "文章访问密码错误");
                }
            }
        }

        // 获取当前文章的创建时间
        String createTime = data.getCreateTime();

        // 查询上一篇文章
        QueryWrapper<ArticleEntity> prevQueryWrapper = new QueryWrapper<>();
        prevQueryWrapper.lt("create_time", createTime).orderByDesc("create_time").last("LIMIT 1");
        ArticleEntity prevArticle = getOne(prevQueryWrapper);

        if (prevArticle != null) {
            // 检查文章配置
            QueryWrapper<ArticleConfigEntity> prevConfigWrapper = new QueryWrapper<>();
            prevConfigWrapper.eq("article_id", prevArticle.getId()).eq("is_del", 0);
            ArticleConfigEntity prevConfig = articleConfigService.getOne(prevConfigWrapper);

            if (prevConfig != null) {
                Map<String, Object> resultPrev = new HashMap<>();
                resultPrev.put("id", prevArticle.getId());
                resultPrev.put("title", prevArticle.getTitle());
                data.setPrev(resultPrev);
            }
        }

        // 查询下一篇文章
        QueryWrapper<ArticleEntity> nextQueryWrapper = new QueryWrapper<>();
        nextQueryWrapper.gt("create_time", createTime).orderByAsc("create_time").last("LIMIT 1");
        ArticleEntity nextArticle = getOne(nextQueryWrapper);

        if (nextArticle != null) {
            // 检查文章配置
            QueryWrapper<ArticleConfigEntity> nextConfigWrapper = new QueryWrapper<>();
            nextConfigWrapper.eq("article_id", nextArticle.getId()).eq("is_del", 0);
            ArticleConfigEntity nextConfig = articleConfigService.getOne(nextConfigWrapper);

            if (nextConfig != null) {
                Map<String, Object> resultNext = new HashMap<>();
                resultNext.put("id", nextArticle.getId());
                resultNext.put("title", nextArticle.getTitle());
                data.setNext(resultNext);
            }
        }

        return data;
    }

    @Override
    public List<ArticleEntity> list(ArticleQueryVO articleQueryVO) {
        // 首先根据文章配置表的条件筛选出符合条件的文章ID
        QueryWrapper<ArticleConfigEntity> configQueryWrapper = new QueryWrapper<>();

        // 根据草稿状态筛选
        if (articleQueryVO.getIsDraft() != null) {
            configQueryWrapper.eq("is_draft", articleQueryVO.getIsDraft());
        }

        // 根据删除状态筛选
        if (articleQueryVO.getIsDel() != null) {
            configQueryWrapper.eq("is_del", articleQueryVO.getIsDel());
        }

        // 获取符合条件的文章ID列表
        List<Integer> articleIds = articleConfigService.list(configQueryWrapper)
                .stream()
                .map(ArticleConfigEntity::getArticleId)
                .collect(Collectors.toList());

        // 如果没有找到符合条件的文章ID，直接返回空列表
        if (articleIds.isEmpty()) {
            return new ArrayList<>();
        }

        // 构建文章查询条件
        QueryWrapper<ArticleEntity> queryWrapper = queryWrapperArticle(articleQueryVO);
        queryWrapper.in("id", articleIds);
        List<ArticleEntity> list = list(queryWrapper);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = Objects.nonNull(authentication);
        list = list.stream()
                .map(article -> bindingData(article.getId()))
                // 如果是普通用户则不显示隐藏的文章，如果是管理员则显示
                .filter(article -> {
                    ArticleConfigEntity config = article.getConfig();
                    // 管理员可以看到所有文章
                    if (isAdmin) {
                        return true;
                    }

                    // 非管理员不能看到隐藏文章
                    return !Objects.equals(article.getConfig().getStatus(), "hide");
                })
                .collect(Collectors.toList());

        // 处理加密文章
        for (ArticleEntity article : list) {
            ArticleConfigEntity config = article.getConfig();
            if (config.getIsEncrypt() == 1) {
                article.setDescription("该文章是加密的");
                article.setContent("该文章是加密的");
            }
        }

        return list;
    }

    @Override
    public Page<ArticleEntity> paging(Page<ArticleEntity> page, ArticleQueryVO articleQueryVO) {
        List<ArticleEntity> list = list(articleQueryVO);
        boolean isAdmin = Objects.nonNull(SecurityContextHolder.getContext().getAuthentication());
        if (!isAdmin) {
            list = list.stream().filter(k -> !Objects.equals(k.getConfig().getStatus(), "no_home")).collect(Collectors.toList());
        }
        int start = Math.toIntExact((page.getCurrent() - 1) * page.getSize());
        int end = Math.toIntExact(Math.min(start + page.getSize(), list.size()));
        List<ArticleEntity> pagedRecords = list.subList(start, end);

        Page<ArticleEntity> result = new Page<>(page.getCurrent(), page.getSize());
        result.setRecords(pagedRecords);
        result.setTotal(list.size());
        return result;
    }

    @Override
    public List<ArticleEntity> getRandomArticles(Integer count) {
        List<Integer> ids = list().stream()
                // 不能是加密文章，且能够正常显示
                .map(ArticleEntity::getId)
                .filter(id -> {
                    QueryWrapper<ArticleConfigEntity> articleConfigQueryWrapper = new QueryWrapper<>();
                    articleConfigQueryWrapper.eq("article_id", id);
                    ArticleConfigEntity config = articleConfigService.getOne(articleConfigQueryWrapper);
                    return config != null && "".equals(config.getPassword()) && Objects.equals(config.getStatus(), "default");
                })
                .collect(Collectors.toList());
        // 优化：提前返回
        if (ids.isEmpty()) return new ArrayList<>();

        // 不能是已删除或草稿
        LambdaQueryWrapper<ArticleConfigEntity> articleConfigLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleConfigLambdaQueryWrapper.in(ArticleConfigEntity::getArticleId, ids);
        articleConfigLambdaQueryWrapper.eq(ArticleConfigEntity::getIsDraft, 0);
        articleConfigLambdaQueryWrapper.eq(ArticleConfigEntity::getIsDel, 0);
        ids = articleConfigService.list(articleConfigLambdaQueryWrapper).stream().map(ArticleConfigEntity::getArticleId).collect(Collectors.toList());
        if (ids.size() <= count) {
            return ids.stream().map(id -> get(id, "")).collect(Collectors.toList());
        }
        // 随机打乱文章ID列表
        Collections.shuffle(ids, new Random());

        // 选择前 count 个文章ID
        List<Integer> randomArticleIds = ids.subList(0, count);

        return randomArticleIds.stream().map(this::bindingData).collect(Collectors.toList());
    }

    @Override
    public List<ArticleEntity> getRecommendedArticles(Integer count) {
        QueryWrapper<ArticleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view").last("LIMIT " + count);
        return list(queryWrapper);
    }

    @Override
    public void recordView(Integer articleId) {
        ArticleEntity data = getById(articleId);
        if (data == null) throw new CustomException(400, "获取失败：该文章不存在");
        data.setView(data.getView() + 1);
        data.updateById();
    }

    @Override
    @Transactional
    public void importArticle(MultipartFile[] list) throws IOException {
        if (list == null || list.length == 0) throw new CustomException(400, "导入失败：文件列表为空");

        // 验证所有文件格式
        for (MultipartFile file : list) {
            if (file == null || file.getOriginalFilename() == null || !file.getOriginalFilename().endsWith(".md")) {
                throw new CustomException(400, "导入失败：请确保所有文件都是 .md 格式");
            }
        }

        // 如果所有文件格式都正确，则继续处理
        for (MultipartFile file : list) {
            // 读取文件内容
            String content = new String(file.getBytes(), StandardCharsets.UTF_8);

            // 解析 Markdown 内容
            String[] lines = content.split("\n");
            String title = "";
            String description = "";
            StringBuilder articleContent = new StringBuilder();

            // 提取标题（第一个 # 开头的行）
            for (String line : lines) {
                if (line.startsWith("# ")) {
                    title = line.substring(2).trim();
                    break;
                }
            }

            // 提取描述（第一个空行后的第一段）
            boolean foundDescription = false;
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    foundDescription = true;
                    continue;
                }
                if (foundDescription && !line.startsWith("#")) {
                    description = line.trim();
                    break;
                }
            }

            // 提取文章内容（跳过标题和描述后的所有内容）
            boolean startContent = false;
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    startContent = true;
                    continue;
                }
                if (startContent) {
                    articleContent.append(line).append("\n");
                }
            }

            // 创建文章对象
            ArticleEntity article = new ArticleEntity();
            article.setTitle(title);
            article.setDescription(description);
            article.setContent(articleContent.toString().trim());
            article.setCreateTime(String.valueOf(LocalDateTime.now()));

            // 设置默认分类（这里假设使用 ID 为 1 的分类）
            article.setCateIds(Collections.singletonList(1));

            // 设置默认文章配置
            ArticleConfigEntity config = new ArticleConfigEntity();
            config.setStatus("default");
            config.setPassword("");
            config.setIsDraft(0);
            config.setIsEncrypt(0);
            config.setIsDel(0);
            article.setConfig(config);

            // 保存文章
            add(article);
        }
    }

    @Override
    public ResponseEntity<byte[]> exportArticle(List<Integer> ids) {
        // 创建一个临时目录用于存储导出的Markdown文件
        java.io.File tempDir = new java.io.File(System.getProperty("java.io.tmpdir"), "exported_articles");

        if (!tempDir.exists() && !tempDir.mkdirs()) {
            throw new CustomException("无法创建临时目录");
        }

        if (ids == null || ids.isEmpty()) {
            // 查询所有的文章
            List<ArticleEntity> list = this.lambdaQuery().select(ArticleEntity::getId).list();
            if (list == null || list.isEmpty()) {
                throw new CustomException("没有文章可以导出");
            }
            ids = list.stream().map(ArticleEntity::getId).collect(Collectors.toList());
        }

        try {
            // 遍历文章ID列表，生成Markdown文件
            for (Integer id : ids) {
                ArticleEntity article = getById(id);
                if (article != null) {
                    String markdownContent = buildMarkdownContent(article);
                    String fileName = sanitizeFileName(article.getTitle()) + ".md";
                    java.io.File markdownFile = new java.io.File(tempDir, fileName);
                    try (java.io.FileWriter writer = new java.io.FileWriter(markdownFile)) {
                        writer.write(markdownContent);
                    } catch (IOException e) {
                        throw new CustomException("写入Markdown文件失败");
                    }
                }
            }


            // 将所有Markdown文件压缩为一个ZIP文件
            ByteArrayOutputStream zipOutputStream = new ByteArrayOutputStream();
            try (ZipOutputStream zos = new ZipOutputStream(zipOutputStream)) {
                for (java.io.File file : Objects.requireNonNull(tempDir.listFiles())) {
                    if (file.isFile() && file.getName().endsWith(".md")) {
                        try (java.io.FileInputStream fis = new java.io.FileInputStream(file)) {
                            ZipEntry zipEntry = new ZipEntry(file.getName());
                            zos.putNextEntry(zipEntry);
                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = fis.read(buffer)) > 0) {
                                zos.write(buffer, 0, length);
                            }
                            zos.closeEntry();
                        }
                    }
                }
                zos.finish(); // 确保 ZIP 文件正确关闭
            } catch (Exception e) {
                log.error("生成 ZIP 文件失败", e);
                throw new CustomException("生成 ZIP 文件失败");
            }

            // 获取ZIP文件的字节数组
            byte[] zipBytes = zipOutputStream.toByteArray();

            // 删除临时目录及其内容
            java.io.File[] files = tempDir.listFiles();
            if (files != null) {
                for (java.io.File file : files) {
                    if (!file.delete()) {
                        log.warn("无法删除临时文件: {}", file.getAbsolutePath());
                    }
                }
            }

            if (!tempDir.delete()) {
                log.warn("无法删除临时目录: {}", tempDir.getAbsolutePath());
            }

            // 返回ResponseEntity
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=articles.zip")
                    .body(zipBytes);
        } catch (Exception e) {
            log.error("导出文章失败", e);
            throw new CustomException("导出文章失败");
        }
    }

    /**
     * 构建Markdown格式的文章内容
     */
    private String buildMarkdownContent(ArticleEntity article) {
        StringBuilder content = new StringBuilder();

        // 添加标题
        content.append("# ").append(article.getTitle()).append("\n\n");

        // 添加描述（如果有）
        if (article.getDescription() != null && !article.getDescription().isEmpty()) {
            content.append(article.getDescription()).append("\n\n");
        }

        // 添加内容
        content.append(article.getContent());

        // 添加元数据（可选）
        content.append("\n\n---\n");
        content.append("导出时间: ").append(LocalDateTime.now()).append("\n");
        content.append("原文ID: ").append(article.getId()).append("\n");

        return content.toString();
    }

    /**
     * 清理文件名，移除非法字符
     */
    private String sanitizeFileName(String fileName) {
        if (fileName == null) {
            return "untitled";
        }
        // 替换Windows和Linux文件系统中的非法字符
        return fileName.replaceAll("[\\\\/:*?\"<>|]", "_");
    }

    /**
     * 查询包装器文章
     *
     * @param articleQueryVO 文章查询 vo
     * @return {@link QueryWrapper }<{@link ArticleEntity }>
     */
    private QueryWrapper<ArticleEntity> queryWrapperArticle(ArticleQueryVO articleQueryVO) {
        QueryWrapper<ArticleEntity> queryWrapper = articleQueryVO.buildQueryWrapper("title");

        // 根据分类id过滤
        if (articleQueryVO.getCateId() != null) {
            QueryWrapper<ArticleCateEntity> queryWrapperArticleIds = new QueryWrapper<>();
            queryWrapperArticleIds.in("cate_id", articleQueryVO.getCateId());
            List<Integer> articleIds = articleCateService.list(queryWrapperArticleIds).stream().map(ArticleCateEntity::getArticleId).collect(Collectors.toList());

            if (!articleIds.isEmpty()) {
                queryWrapper.in("id", articleIds);
            } else {
                // 添加一个始终为假的条件
                queryWrapper.in("id", -1); // -1 假设为不存在的ID
            }
        }

        // 根据标签id过滤
        if (articleQueryVO.getTagId() != null) {
            QueryWrapper<ArticleTagEntity> queryWrapperArticleIds = new QueryWrapper<>();
            queryWrapperArticleIds.in("tag_id", articleQueryVO.getTagId());
            List<Integer> articleIds = articleTagService.list(queryWrapperArticleIds).stream().map(ArticleTagEntity::getArticleId).collect(Collectors.toList());

            if (!articleIds.isEmpty()) {
                queryWrapper.in("id", articleIds);
            } else {
                // 添加一个始终为假的条件
                queryWrapper.in("id", -1); // -1 假设为不存在的ID
            }
        }

        return queryWrapper;
    }

    /**
     * 绑定数据
     *
     * @param id id
     * @return {@link ArticleEntity }
     */
    public ArticleEntity bindingData(Integer id) {
        ArticleEntity data = getById(id);

        if (data == null) throw new CustomException(400, "获取文章失败：该文章不存在");

        // 查询当前文章的分类ID
        QueryWrapper<ArticleCateEntity> queryWrapperCateIds = new QueryWrapper<>();
        queryWrapperCateIds.eq("article_id", id);
        List<Integer> cate_ids = articleCateService.list(queryWrapperCateIds).stream().map(ArticleCateEntity::getCateId).collect(Collectors.toList());

        // 如果有分类，则绑定分类信息
        if (!cate_ids.isEmpty()) {
            QueryWrapper<CateEntity> queryWrapperCateList = new QueryWrapper<>();
            queryWrapperCateList.in("id", cate_ids);
            List<CateEntity> cates = cateService.buildCateTree(cateService.list(queryWrapperCateList), 0);
            data.setCateList(cates);
        }

        // 查询当前文章的标签ID
        QueryWrapper<ArticleTagEntity> queryWrapperTagIds = new QueryWrapper<>();
        queryWrapperTagIds.eq("article_id", id);
        List<Integer> tag_ids = articleTagService.list(queryWrapperTagIds).stream().map(ArticleTagEntity::getTagId).collect(Collectors.toList());

        if (!tag_ids.isEmpty()) {
            QueryWrapper<TagEntity> queryWrapperTagList = new QueryWrapper<>();
            queryWrapperTagList.in("id", tag_ids);
            List<TagEntity> tags = tagService.list(queryWrapperTagList);
            data.setTagList(tags);
        }

        data.setComment(commentMapper.getCommentList(id).size());

        // 查找文章配置
        QueryWrapper<ArticleConfigEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", id);
        ArticleConfigEntity articleConfig = articleConfigService.getOne(queryWrapper);
        data.setConfig(articleConfig);

        return data;
    }



    /**
     * 添加文章关联数据
     *
     * @param article 品
     */
    private void addArticleCorrelationData(ArticleEntity article) {
        // 新增分类
        List<Integer> cateIdList = article.getCateIds();
        if (!cateIdList.isEmpty()) {
            ArrayList<ArticleCateEntity> cateArrayList = new ArrayList<>(cateIdList.size());
            for (Integer id : cateIdList) {
                ArticleCateEntity articleCate = new ArticleCateEntity();
                articleCate.setArticleId(article.getId());
                articleCate.setCateId(id);
                cateArrayList.add(articleCate);
            }
            articleCateService.saveBatch(cateArrayList);
        }

        // 新增标签
        List<Integer> tagIdList = article.getTagIds();

        if (tagIdList != null && !tagIdList.isEmpty()) {
            ArrayList<ArticleTagEntity> tagArrayList = new ArrayList<>(tagIdList.size());
            for (Integer id : tagIdList) {
                ArticleTagEntity articleTag = new ArticleTagEntity();
                articleTag.setArticleId(article.getId());
                articleTag.setTagId(id);
                tagArrayList.add(articleTag);
            }
            articleTagService.saveBatch(tagArrayList);
        }

        // 新增文章配置
        ArticleConfigEntity config = article.getConfig();
        config.setIsDel(0);
        config.setArticleId(article.getId());
        config.insert();
    }

    /**
     * DEL 文章相关数据
     *
     */
    private void delArticleCorrelationData(Collection<Integer> ids) {
        if (ids == null || ids.isEmpty()) return;

        // 删除绑定的分类
        articleCateService.lambdaUpdate()
                .in(ArticleCateEntity::getArticleId, ids)
                .remove();

        // 删除绑定的标签
        articleTagService.lambdaUpdate()
                .in(ArticleTagEntity::getArticleId, ids)
                .remove();

        // 删除文章配置
        articleConfigService.lambdaUpdate()
                .in(ArticleConfigEntity::getArticleId, ids)
                .remove();
    }
}
