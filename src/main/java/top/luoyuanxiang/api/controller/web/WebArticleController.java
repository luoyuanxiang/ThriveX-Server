package top.luoyuanxiang.api.controller.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.luoyuanxiang.api.entity.Article;
import top.luoyuanxiang.api.service.IArticleService;
import top.luoyuanxiang.api.utils.Result;
import top.luoyuanxiang.api.vo.article.ArticleFillterVo;

import java.io.IOException;
import java.util.List;

/**
 * 文章管理
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/web/article")
public class WebArticleController {

    @Resource
    private IArticleService articleService;

    /**
     * 获取文章
     *
     * @param id       身份证
     * @param password 密码
     * @return {@link Result }<{@link Article }>
     */
    @GetMapping("/{id}")
    public Result<Article> get(@PathVariable Integer id,
                               @RequestParam(defaultValue = "") String password) {
        password = !password.isEmpty() ? password : "";
        Article data = articleService.get(id, password);
        return Result.success(data);
    }

    /**
     * 获取文章列表
     *
     * @param filterVo filter vo
     * @return {@link Result }<{@link List }<{@link Article }>>
     */
    @PostMapping("/list")
    public Result<List<Article>> list(@RequestBody ArticleFillterVo filterVo) {
        List<Article> data = articleService.list(filterVo);
        return Result.success(data);
    }

    /**
     * 分页查询文章列表
     *
     * @param filterVo filter vo
     * @return {@link Result }
     */
    @PostMapping("/paging")
    public Result<Page<Article>> paging(@RequestBody ArticleFillterVo filterVo, Page<Article> page) {
        filterVo.setWeb(true);
        articleService.paging(page, filterVo);
        return Result.success(page);
    }

    /**
     * 获取指定分类的文章
     *
     * @param cate_id Cate ID
     * @param page    页
     * @return {@link Result }<{@link Page }<{@link Article }>>
     */
    @GetMapping("/cate/{cate_id}")
    public Result<Page<Article>> getCateArticleList(@PathVariable Integer cate_id, Page<Article> page) {
        ArticleFillterVo filterVo = new ArticleFillterVo();
        filterVo.setWeb(true);
        filterVo.setCateId(cate_id);
        articleService.paging(page, filterVo);
        return Result.success(page);
    }

    /**
     * 获取指定标签的文章
     *
     * @param tag_id 标签 ID
     * @param page   页
     * @return {@link Result }<{@link Page }<{@link Article }>>
     */
    @GetMapping("/tag/{tag_id}")
    public Result<Page<Article>> getTagArticleList(@PathVariable Integer tag_id, Page<Article> page) {
        ArticleFillterVo filterVo = new ArticleFillterVo();
        filterVo.setTagId(tag_id);
        filterVo.setWeb(true);
        articleService.paging(page, filterVo);
        return Result.success(page);
    }

    /**
     * 随机获取文章数据
     *
     * @param count 计数
     * @return {@link Result }<{@link List }<{@link Article }>>
     */
    @GetMapping("/random")
    public Result<List<Article>> getRandomArticles(@RequestParam(defaultValue = "5") Integer count) {
        List<Article> data = articleService.getRandomArticles(count);
        return Result.success(data);
    }

    /**
     * 获取热门文章数据
     *
     * @param count 计数
     * @return {@link Result }<{@link List }<{@link Article }>>
     */
    @GetMapping("/hot")
    public Result<List<Article>> getRecommendedArticles(@RequestParam(defaultValue = "5") Integer count) {
        List<Article> data = articleService.getRecommendedArticles(count);
        return Result.success(data);
    }

    /**
     * 递增文章浏览量
     *
     * @param article_id 文章 ID
     * @return {@link Result }<{@link ? }>
     */
    @GetMapping("/view/{article_id}")
    public Result<?> recordView(@PathVariable Integer article_id) {
        articleService.recordView(article_id);
        return Result.success();
    }

    /**
     * 批量导入文章
     *
     * @param files 文件
     * @return {@link Result }<{@link String }>
     * @throws IOException io异常
     */
    @PostMapping("/import")
    public Result<?> importArticle(@RequestParam MultipartFile[] files) throws IOException {
        articleService.importArticle(files);
        return Result.success();
    }

    /**
     * 批量导出文章
     *
     * @param ids IDS
     * @return {@link ResponseEntity }<{@link byte[] }>
     */
    @PostMapping("/export")
    public ResponseEntity<byte[]> exportArticle(@RequestBody List<Integer> ids) {
        return articleService.exportArticle(ids);
    }

}
