package top.luoyuanxiang.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.luoyuanxiang.api.entity.*;
import top.luoyuanxiang.api.execption.CustomException;
import top.luoyuanxiang.api.mapper.ArticleMapper;
import top.luoyuanxiang.api.service.*;
import top.luoyuanxiang.api.utils.ThreadUserUtil;
import top.luoyuanxiang.api.vo.article.ArticleFillterVo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

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
    private ICommentService commentService;

    @Override
    @Transactional
    public void add(Article article) {
        article.insert();
        // 新增分类
        saveCate(article);
        // 新增标签
        saveTag(article);

        // 新增文章配置
        saveArticleConfig(article);

    }

    @Override
    public void del(Integer id, Integer isDel) {
        Article article = getById(id);
        if (article == null) throw new CustomException(400, "文章不存在！");
        ArticleConfig articleConfig = articleConfigService.lambdaQuery()
                .eq(ArticleConfig::getArticleId, id).one();

        // 严格删除：直接从数据库删除
        if (isDel == 0) {
            // 删除文章关联的数据
            delArticleCorrelationData(Collections.singletonList(id));
            // 删除当前文章
            removeById(id);
        }
        // 普通删除：更改 is_del 字段，到时候可以通过更改字段恢复
        if (isDel == 1) {
            articleConfig.setIsDel(1);
            articleConfigService.updateById(articleConfig);
        }
        if (isDel != 0 && isDel != 1) {
            throw new CustomException(400, "参数有误：请选择是否严格删除");
        }
    }

    @Override
    public void reduction(Integer id) {
        Article article = getById(id);
        articleConfigService.lambdaUpdate()
                        .eq(ArticleConfig::getArticleId, id)
                        .set(ArticleConfig::getIsDel, 0)
                .update();
    }

    @Override
    public void delBatch(List<Integer> ids) {
        delArticleCorrelationData(ids);
        // 批量删除文章
        lambdaUpdate()
                .in(Article::getId, ids)
                .remove();
    }

    @Override
    @Transactional
    public void edit(Article article) {
        if (article.getCateIds() == null) throw new CustomException(400, "编辑失败：请绑定分类");

        // 删除文章关联的数据
        delArticleCorrelationData(Collections.singletonList(article.getId()));

        // 重新绑定分类
        saveCate(article);

        // 重新绑定标签
        saveTag(article);

        // 重新绑定文章配置
        saveArticleConfig(article);

        // 修改文章
        article.updateById();
    }

    @Override
    public Article get(Integer id, String password) {
        Article data = bindingData(id);

        String description = data.getDescription();
        String content = data.getContent();

        ArticleConfig config = data.getConfig();

        if (data.getConfig().getIsEncrypt() == 0 && !password.isEmpty()) {
            throw new CustomException(610, "该文章不需要访问密码");
        }

        // 管理员可以查看任何权限的文章
        if (!ThreadUserUtil.isAdmin()) {
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
                if (config.getPassword().equals(password)) {
                    data.setDescription(description);
                    data.setContent(content);
                } else {
                    throw new CustomException(613, "文章访问密码错误");
                }
            }
        }

        // 获取当前文章的创建时间
        LocalDateTime createTime = data.getCreateTime();

        // 查询上一篇文章
        Article prevArticle = lambdaQuery()
                .lt(Article::getCreateTime, createTime)
                .orderByDesc(Article::getCreateTime)
                .last("limit 1")
                .one();

        if (prevArticle != null) {
            // 检查文章配置
            ArticleConfig prevConfig = articleConfigService.lambdaQuery()
                    .eq(ArticleConfig::getArticleId, prevArticle.getId())
                    .eq(ArticleConfig::getIsDel, 0)
                    .one();

            if (prevConfig != null) {
                Map<String, Object> resultPrev = new HashMap<>();
                resultPrev.put("id", prevArticle.getId());
                resultPrev.put("title", prevArticle.getTitle());
                data.setPrev(resultPrev);
            }
        }

        // 查询下一篇文章
        Article nextArticle = lambdaQuery()
                .gt(Article::getCreateTime, createTime)
                .orderByAsc(Article::getCreateTime)
                .last("limit 1")
                .one();

        if (nextArticle != null) {
            // 检查文章配置
            QueryWrapper<ArticleConfig> nextConfigWrapper = new QueryWrapper<>();
            nextConfigWrapper.eq("article_id", nextArticle.getId()).eq("is_del", 0);
            ArticleConfig nextConfig = articleConfigService.lambdaQuery()
                    .eq(ArticleConfig::getArticleId, nextArticle.getId())
                    .eq(ArticleConfig::getIsDel, 0)
                    .one();

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
    public List<Article> list(ArticleFillterVo filterVo) {
        List<Article> list = baseMapper.list(filterVo, ThreadUserUtil.isAdmin());
        // 处理加密文章
        for (Article article : list) {
            ArticleConfig config = article.getConfig();
            if (config.getIsEncrypt() == 1) {
                article.setDescription("该文章是加密的");
                article.setContent("该文章是加密的");
            }
        }
        return list;
    }

    @Override
    public void paging(Page<Article> page, ArticleFillterVo filterVo) {
        baseMapper.paging(page, filterVo, ThreadUserUtil.isAdmin());
        // 处理加密文章
        for (Article article : page.getRecords()) {
            ArticleConfig config = article.getConfig();
            if (config.getIsEncrypt() == 1) {
                article.setDescription("该文章是加密的");
                article.setContent("该文章是加密的");
            }
        }
    }

    @Override
    public List<Article> getRandomArticles(Integer count) {
        return baseMapper.getRandomArticles(count);
    }

    @Override
    public List<Article> getRecommendedArticles(Integer count) {
        return lambdaQuery()
                .last("ORDER BY view DESC LIMIT " + count)
                .list();
    }

    @Override
    public void recordView(Integer articleId) {
        Article data = getById(articleId);
        if (data == null) throw new CustomException(400, "获取失败：该文章不存在");
        data.setView(data.getView() + 1);
        updateById(data);
    }

    @Override
    @Transactional
    public void importArticle(MultipartFile[] files) throws IOException {
        // 首先验证所有文件格式
        for (MultipartFile file : files) {
            if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".md")) {
                throw new CustomException(400, "导入失败：存在非 Markdown 格式文件，请确保所有文件都是 .md 格式");
            }
        }

        // 如果所有文件格式都正确，则继续处理
        for (MultipartFile file : files) {
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
            Article article = new Article();
            article.setTitle(title);
            article.setDescription(description);
            article.setContent(articleContent.toString().trim());
            article.setView(0);
            article.setComment(0);
            article.setCreateTime(LocalDateTime.now());

            // 设置默认分类（这里假设使用 ID 为 1 的分类）
            article.setCateIds(Collections.singletonList(1));

            // 设置默认文章配置
            ArticleConfig config = new ArticleConfig();
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
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }

        // 遍历文章ID列表，生成Markdown文件
        for (Integer id : ids) {
            Article article = getById(id);
            if (article != null) {
                String markdownContent = buildMarkdownContent(article);
                String fileName = sanitizeFileName(article.getTitle()) + ".md";
                java.io.File markdownFile = new java.io.File(tempDir, fileName);
                try (java.io.FileWriter writer = new java.io.FileWriter(markdownFile)) {
                    writer.write(markdownContent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 将所有Markdown文件压缩为一个ZIP文件
        java.io.File zipFile = new java.io.File(tempDir, "articles.zip");
        try (java.util.zip.ZipOutputStream zos = new java.util.zip.ZipOutputStream(new java.io.FileOutputStream(zipFile))) {
            for (java.io.File file : tempDir.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".md")) {
                    try (java.io.FileInputStream fis = new java.io.FileInputStream(file)) {
                        java.util.zip.ZipEntry zipEntry = new java.util.zip.ZipEntry(file.getName());
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 读取ZIP文件为字节数组
        byte[] zipBytes = new byte[(int) zipFile.length()];
        try (java.io.FileInputStream fis = new java.io.FileInputStream(zipFile)) {
            int offset = 0;
            int numRead;
            while (offset < zipBytes.length && (numRead = fis.read(zipBytes, offset, zipBytes.length - offset)) >= 0) {
                offset += numRead;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 删除临时目录及其内容
        for (java.io.File file : tempDir.listFiles()) {
            file.delete();
        }
        tempDir.delete();

        // 返回ResponseEntity
        return ResponseEntity.ok()
                .header("Pragma", "no-cache")
                .header("Cache-Control", "no-cache")
                .header("Expires", "0")
                .header("Content-Disposition", "attachment;filename=articles.zip")
                .header("Content-Transfer-Encoding", "binary")
                .header("Access-Control-Expose-Headers", "Content-Disposition")
                .contentLength(zipBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(zipBytes);
    }

    /**
     * 构建Markdown格式的文章内容
     */
    private String buildMarkdownContent(Article article) {
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
     * 绑定数据
     *
     * @param id 身份证
     * @return {@link Article }
     */
    public Article bindingData(Integer id) {
        Article data = getById(id);
        if (data == null) throw new CustomException(400, "获取文章失败：该文章不存在");
        // 查询当前文章的分类ID
        List<Integer> cateIds = articleCateService.lambdaQuery()
                .eq(ArticleCate::getArticleId, id)
                .list()
                .stream()
                .map(ArticleCate::getCateId)
                .collect(Collectors.toList());
        // 如果有分类，则绑定分类信息
        if (!cateIds.isEmpty()) {
            QueryWrapper<Cate> queryWrapperCateList = new QueryWrapper<>();
            queryWrapperCateList.in("id", cateIds);
            List<Cate> list = cateService.lambdaQuery()
                    .in(Cate::getId, cateIds)
                    .list();
            List<Cate> cates = cateService.buildCateTree(list, 0);
            data.setCateList(cates);
        }

        // 查询当前文章的标签ID
        List<Integer> tag_ids = articleTagService.lambdaQuery()
                .eq(ArticleTag::getArticleId, id)
                .list().stream().map(ArticleTag::getTagId).collect(Collectors.toList());

        if (!tag_ids.isEmpty()) {
            List<Tag> tags = tagService.lambdaQuery().in(Tag::getId, tag_ids).list();
            data.setTagList(tags);
        }
        Long count = commentService.lambdaQuery()
                .eq(Comment::getArticleId, id)
                .count();
        data.setComment(Math.toIntExact(count));

        // 查找文章配置
        ArticleConfig articleConfig = articleConfigService.lambdaQuery()
                .eq(ArticleConfig::getArticleId, id)
                .one();
        data.setConfig(articleConfig);

        return data;
    }

    /**
     * 删除文章的关联数据
     *
     * @param ids IDS
     */
    public void delArticleCorrelationData(List<Integer> ids) {
        // 删除绑定的分类
        articleCateService.lambdaUpdate()
                .in(ArticleCate::getArticleId, ids)
                .remove();

        // 删除绑定的标签
        articleTagService.lambdaUpdate()
                .in(ArticleTag::getArticleId, ids)
                .remove();

        // 删除文章配置
        articleConfigService.lambdaUpdate()
                .in(ArticleConfig::getArticleId, ids)
                .remove();
    }

    /**
     * 保存 分类
     *
     * @param article 品
     */
    private void saveCate(Article article) {
        // 新增分类
        List<Integer> cateIdList = article.getCateIds();
        if (!cateIdList.isEmpty()) {
            ArrayList<ArticleCate> cateArrayList = new ArrayList<>(cateIdList.size());
            for (Integer id : cateIdList) {
                ArticleCate articleCate = new ArticleCate();
                articleCate.setArticleId(article.getId());
                articleCate.setCateId(id);
                cateArrayList.add(articleCate);
            }
            articleCateService.saveBatch(cateArrayList);
        }
    }

    /**
     * 保存标签
     *
     * @param article 品
     */
    private void saveTag(Article article) {
        List<Integer> tagIdList = article.getTagIds();
        if (tagIdList != null && !tagIdList.isEmpty()) {
            ArrayList<ArticleTag> tagArrayList = new ArrayList<>(tagIdList.size());
            for (Integer id : tagIdList) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(article.getId());
                articleTag.setTagId(id);
                tagArrayList.add(articleTag);
            }
            articleTagService.saveBatch(tagArrayList);
        }
    }

    /**
     * 保存文章配置
     *
     * @param article 品
     */
    private void saveArticleConfig(Article article) {
        ArticleConfig config = article.getConfig();
        ArticleConfig articleConfig = new ArticleConfig();
        articleConfig.setArticleId(article.getId());
        articleConfig.setStatus(config.getStatus());
        articleConfig.setPassword(config.getPassword());
        articleConfig.setIsDraft(article.getConfig().getIsDraft());
        articleConfig.setIsEncrypt(article.getConfig().getIsEncrypt());
        articleConfig.setIsDel(0);
        articleConfig.setComment(article.getConfig().getComment());
        articleConfig.insert();
    }
}
