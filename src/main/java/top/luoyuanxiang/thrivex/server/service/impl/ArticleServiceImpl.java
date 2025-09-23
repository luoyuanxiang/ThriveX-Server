package top.luoyuanxiang.thrivex.server.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.luoyuanxiang.thrivex.server.entity.*;
import top.luoyuanxiang.thrivex.server.exception.CustomException;
import top.luoyuanxiang.thrivex.server.mapper.ArticleMapper;
import top.luoyuanxiang.thrivex.server.security.SecurityUser;
import top.luoyuanxiang.thrivex.server.service.*;
import top.luoyuanxiang.thrivex.server.utils.MarkdownExporterUtils;
import top.luoyuanxiang.thrivex.server.utils.MarkdownParserUtil;
import top.luoyuanxiang.thrivex.server.vo.ArticleQueryVO;
import top.luoyuanxiang.thrivex.server.vo.FrontMatter;
import top.luoyuanxiang.thrivex.server.vo.MarkdownParseResult;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * 服务实现类
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
    private ICateService cateService;
    @Resource
    private ITagService tagService;

    @Override
    @Transactional
    public void add(ArticleEntity article) {
        article.insert();
        addArticleCorrelationData(article);
    }

    @Override
    @Transactional
    public void del(Integer id, Integer isDel) {
        ArticleEntity articleEntity = getById(id);
        if (isDel != 0 && isDel != 1) {
            throw new RuntimeException("参数有误：请选择是否严格删除");
        }

        // 严格删除：直接从数据库删除
        if (isDel == 0) {
            // 删除文章关联的数据
            delArticleCorrelationData(Collections.singleton(id));

            // 删除当前文章
            removeById(id);
        }

        // 普通删除：更改 is_del 字段，到时候可以通过更改字段恢复
        if (isDel == 1) {
            articleEntity.setIsDel(1);
            articleEntity.updateById();
        }
    }

    @Override
    public void reduction(Integer id) {
        ArticleEntity articleEntity = getById(id);
        articleEntity.setIsDel(0);
        articleEntity.updateById();
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
        ArticleQueryVO vo = new ArticleQueryVO();
        vo.setIds(Collections.singletonList(id));
        List<ArticleEntity> list = list(vo);
        if (list.isEmpty()) {
            throw new RuntimeException("获取文章失败：文章不存在");
        }
        ArticleEntity data = list.get(0);

        String description = data.getDescription();
        String content = data.getContent();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAdmin = principal instanceof SecurityUser;
        // 管理员可以查看任何权限的文章
        if (!isAdmin) {
            if (data.getIsDel() == 1) {
                throw new CustomException(404, "该文章已被删除");
            }

            if ("hide".equals(data.getStatus())) {
                throw new CustomException(611, "该文章已被隐藏");
            }

            // 如果有密码就必须通过密码才能查看
            if (data.getIsEncrypt() == 1) {
                // 如果需要访问密码且没有传递密码参数
                if (password.isEmpty()) {
                    throw new CustomException(612, "请输入文章访问密码");
                }

                data.setDescription("该文章需要密码才能查看");
                data.setContent("该文章需要密码才能查看");

                // 验证密码是否正确
                if (data.getPassword().equals(password)) {
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
        ArticleEntity prevArticle = lambdaQuery()
                .lt(ArticleEntity::getCreateTime, createTime)
                .eq(ArticleEntity::getIsDel, 0)
                .last("limit 1")
                .one();

        if (prevArticle != null) {
            Map<String, Object> resultPrev = new HashMap<>();
            resultPrev.put("id", prevArticle.getId());
            resultPrev.put("title", prevArticle.getTitle());
            data.setPrev(resultPrev);
        }

        // 查询下一篇文章
        ArticleEntity nextArticle = lambdaQuery()
                .gt(ArticleEntity::getCreateTime, createTime)
                .eq(ArticleEntity::getIsDel, 0)
                .last("limit 1")
                .one();

        if (nextArticle != null) {
            // 检查文章配置
            Map<String, Object> resultNext = new HashMap<>();
            resultNext.put("id", nextArticle.getId());
            resultNext.put("title", nextArticle.getTitle());
            data.setNext(resultNext);
        }

        return data;
    }

    @Override
    public List<ArticleEntity> list(ArticleQueryVO articleQueryVO) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();
        boolean isAdmin = principal instanceof SecurityUser;
        articleQueryVO.setAdmin(isAdmin);
        List<ArticleEntity> list = baseMapper.list(articleQueryVO);
        dataPro(list);
        return list;
    }

    @Override
    public Page<ArticleEntity> paging(Page<ArticleEntity> page, ArticleQueryVO articleQueryVO) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();
        boolean isAdmin = principal instanceof SecurityUser;
        articleQueryVO.setAdmin(isAdmin);
        page = baseMapper.list(page, articleQueryVO);
        dataPro(page.getRecords());
        return page;
    }

    @Override
    public List<ArticleEntity> getRandomArticles(Integer count) {
        ArticleQueryVO vo = new ArticleQueryVO();
        vo.setRandCount(count);
        List<ArticleEntity> list = baseMapper.list(vo);
        dataPro(list);
        return list;
    }

    @Override
    public List<ArticleEntity> getRecommendedArticles(Integer count) {
        QueryWrapper<ArticleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view").last("LIMIT " + count);
        List<ArticleEntity> list = list(queryWrapper);
        dataPro(list);
        return list;
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
        ObjectMapper objectMapper = new ObjectMapper();
        // 1. 忽略JSON中不存在的字段（避免因字段不匹配报错）
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 2. 支持Java 8时间类型（LocalDateTime、LocalDate）
        objectMapper.registerModule(new JavaTimeModule());
        // 3. 允许空值（默认支持，可根据需求调整）
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        // 如果所有文件格式都正确，则继续处理
        for (MultipartFile file : list) {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                throw new CustomException(400, "导入失败：请确保所有文件都是 .md/.json 格式");
            }
            // 读取文件内容
            MarkdownParseResult parse;
            if (originalFilename.endsWith(".md")) {
                parse = MarkdownParserUtil.parse(file.getInputStream());
            } else {
                parse = objectMapper.readValue(file.getInputStream(), MarkdownParseResult.class);
            }
            // 读取文件内容
            ArticleEntity article = new ArticleEntity();
            article.setTitle(parse.frontMatter().getTitle());
            article.setDescription(parse.frontMatter().getDescription());
            article.setContent(parse.content());
            article.setCover(parse.frontMatter().getCover());
            if (parse.frontMatter().getDate() != null) {
                article.setCreateTime(parse.frontMatter().getDate().getTime() + "");
            } else {
                article.setCreateTime(new Date().getTime() + "");
            }
            // 标签处理
            List<String> tagList = parse.frontMatter().getTags();
            if (tagList != null && !tagList.isEmpty()) {
                List<Integer> tagIds = new ArrayList<>();
                for (String tagName : tagList) {
                    TagEntity tagEntity = tagService.lambdaQuery()
                            .eq(TagEntity::getName, tagName)
                            .one();
                    if (tagEntity == null) {
                        tagEntity = new TagEntity();
                        tagEntity.setName(tagName);
                        tagService.save(tagEntity);
                    }
                    tagIds.add(tagEntity.getId());
                }
                article.setTagIds(tagIds);
            }
            // 分类处理
            List<String> cateList = parse.frontMatter().getCategories();
            if (cateList != null && !cateList.isEmpty()) {
                List<Integer> cateIds = new ArrayList<>();
                for (String cateName : cateList) {
                    CateEntity cateEntity = cateService.lambdaQuery()
                            .eq(CateEntity::getName, cateName)
                            .eq(CateEntity::getType, "cate")
                            .one();
                    if (cateEntity == null) {
                        cateEntity = new CateEntity();
                        cateEntity.setName(cateName);
                        cateEntity.setType("cate");
                        cateService.save(cateEntity);
                    }
                    cateIds.add(cateEntity.getId());
                }
                article.setCateIds(cateIds);
            }
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
        ArticleQueryVO vo = new ArticleQueryVO();
        vo.setIds(ids);
        vo.setAdmin(true);
        List<ArticleEntity> articleEntityList = list(vo);

        try {
            // 遍历文章ID列表，生成Markdown文件
            for (ArticleEntity article : articleEntityList) {
                FrontMatter frontMatter = new FrontMatter();
                frontMatter.setTitle(article.getTitle());
                frontMatter.setDescription(article.getDescription());
                frontMatter.setCover(article.getCover());
                frontMatter.setDate(new Date(Long.parseLong(article.getCreateTime())));
                frontMatter.setTags(article.getTagList().stream().map(TagEntity::getName).collect(Collectors.toList()));
                frontMatter.setCategories(article.getCateList().stream().map(CateEntity::getName).collect(Collectors.toList()));
                String markdownContent = MarkdownExporterUtils.generateMarkdownContent(frontMatter, article.getContent());
                String fileName = sanitizeFileName(article.getTitle()) + ".md";
                java.io.File markdownFile = new java.io.File(tempDir, fileName);
                try (java.io.FileWriter writer = new java.io.FileWriter(markdownFile)) {
                    writer.write(markdownContent);
                } catch (IOException e) {
                    throw new CustomException("写入Markdown文件失败");
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

            HttpHeaders headers = new HttpHeaders();
            // 1. 设置“附件”标识 + 文件名（中文需编码）
            String fileName = URLEncoder.encode("articles.zip", StandardCharsets.UTF_8);
            headers.add("Content-Disposition", "attachment; filename*=UTF-8''" + fileName);
            // 2. 设置正确的 Content-Type（MD 文件用 text/markdown）
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // 3. 跨域场景需暴露 Content-Disposition（否则前端拿不到）
            headers.add("Access-Control-Expose-Headers", "Content-Disposition");
            // 返回ResponseEntity
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(zipBytes);
        } catch (Exception e) {
            log.error("导出文章失败", e);
            throw new CustomException("导出文章失败");
        }
    }

    /**
     * 数据处理
     *
     * @param list 列表
     */
    private void dataPro(List<ArticleEntity> list) {
        list.forEach(article -> {
            // 去除空 list null
            if (CollectionUtil.isNotEmpty(article.getTagList())) {
                List<TagEntity> tagEntityList = article.getTagList().parallelStream().filter(Objects::nonNull).toList();
                article.setTagList(tagEntityList);
            }
            if (CollectionUtil.isNotEmpty(article.getCateList())) {
                List<CateEntity> cateEntityList = article.getCateList().parallelStream().filter(Objects::nonNull).toList();
                article.setCateList(cateEntityList);
            }
            if (article.getIsEncrypt() == 1) {
                article.setDescription("该文章是加密的");
                article.setContent("该文章是加密的");
            }
            article.setPassword(null);
        });
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
    }
}
