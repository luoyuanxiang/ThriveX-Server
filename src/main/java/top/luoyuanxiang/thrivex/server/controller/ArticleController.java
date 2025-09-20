package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.luoyuanxiang.thrivex.server.ann.NoAuth;
import top.luoyuanxiang.thrivex.server.entity.ArticleEntity;
import top.luoyuanxiang.thrivex.server.ann.HasPermission;
import top.luoyuanxiang.thrivex.server.service.IArticleService;
import top.luoyuanxiang.thrivex.server.vo.ArticleQueryVO;
import top.luoyuanxiang.thrivex.server.vo.Paging;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.io.IOException;
import java.util.List;

/**
 * 文章管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private IArticleService articleService;

    /**
     * 新增文章
     *
     * @param article 文章形式 DTO
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("article:add")
    @PostMapping
    public Result<String> add(@RequestBody ArticleEntity article) {
        articleService.add(article);
        return Result.success();
    }

    /**
     * 删除文章
     *
     * @param id     id
     * @param is_del 是德尔
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("article:del")
    @DeleteMapping("/{id}/{is_del}")
    public Result<String> del(@PathVariable Integer id, @PathVariable Integer is_del) {
        articleService.del(id, is_del);
        return Result.success();
    }

    /**
     * 还原被删除的文章
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("article:reduction")
    @PatchMapping("/reduction/{id}")
    public Result<String> reduction(@PathVariable Integer id) {
        articleService.reduction(id);
        return Result.success();
    }

    /**
     * 批量删除文章
     *
     * @param ids 身份证
     * @return {@link Result }
     */
    @HasPermission("article:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        articleService.delBatch(ids);
        return Result.success();
    }

    /**
     * 编辑文章
     *
     * @param article 文章形式 DTO
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("article:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody ArticleEntity article) {
        articleService.edit(article);
        return Result.success();
    }

    /**
     * 获取文章
     *
     * @param id       id
     * @param password 密码
     * @return {@link Result }<{@link ArticleEntity }>
     */
    @NoAuth
    @GetMapping("/{id}")
    public Result<ArticleEntity> get(@PathVariable Integer id, @RequestParam(defaultValue = "") String password) {
        password = !password.isEmpty() ? password : "";
        ArticleEntity data = articleService.get(id, password);
        return Result.success(data);
    }

    /**
     * 获取文章列表
     *
     * @param articleQueryVO 文章查询 vo
     * @return {@link Result }<{@link List }<{@link ArticleEntity }>>
     */
    @NoAuth
    @PostMapping("/list")
    public Result<List<ArticleEntity>> list(@RequestBody ArticleQueryVO articleQueryVO) {
        List<ArticleEntity> data = articleService.list(articleQueryVO);
        return Result.success(data);
    }

    /**
     * 分页查询文章列表
     *
     * @param articleQueryVO 文章查询 vo
     * @param page           页
     * @param size           大小
     * @return {@link Result }
     */
    @NoAuth
    @PostMapping("/paging")
    public Result<Paging<ArticleEntity>> paging(@RequestBody ArticleQueryVO articleQueryVO, Integer page, Integer size) {
        Page<ArticleEntity> list = articleService.paging(new Page<>(page, size), articleQueryVO);
        return Result.page(list);
    }

    /**
     * 获取指定分类的文章
     *
     * @param cateId 分类编号
     * @param page   页
     * @param size   大小
     * @return {@link Result }<{@link Paging }<{@link ArticleEntity }>>
     */
    @NoAuth
    @GetMapping("/cate/{cateId}")
    public Result<Paging<ArticleEntity>> getCateArticleList(@PathVariable Integer cateId, Integer page, Integer size) {
        ArticleQueryVO articleQueryVO = new ArticleQueryVO();
        articleQueryVO.setCateId(cateId);
        Page<ArticleEntity> list = articleService.paging(new Page<>(page, size), articleQueryVO);
        return Result.page(list);
    }

    /**
     * 获取指定标签的文章
     *
     * @param tagId 标记 ID
     * @param page  页
     * @param size  大小
     * @return {@link Result }<{@link Paging }<{@link ArticleEntity }>>
     */
    @NoAuth
    @GetMapping("/tag/{tagId}")
    public Result<Paging<ArticleEntity>> getTagArticleList(@PathVariable Integer tagId, Integer page, Integer size) {
        ArticleQueryVO articleQueryVO = new ArticleQueryVO();
        articleQueryVO.setTagId(tagId);
        Page<ArticleEntity> list = articleService.paging(new Page<>(page, size), articleQueryVO);
        return Result.page(list);
    }

    /**
     * 随机获取文章数据
     *
     * @param count 计数
     * @return {@link Result }<{@link List }<{@link ArticleEntity }>>
     */
    @NoAuth
    @GetMapping("/random")
    public Result<List<ArticleEntity>> getRandomArticles(@RequestParam(defaultValue = "5") Integer count) {
        List<ArticleEntity> data = articleService.getRandomArticles(count);
        return Result.success(data);
    }

    /**
     * 获取热门文章数据
     *
     * @param count 计数
     * @return {@link Result }<{@link List }<{@link ArticleEntity }>>
     */
    @NoAuth
    @GetMapping("/hot")
    public Result<List<ArticleEntity>> getRecommendedArticles(@RequestParam(defaultValue = "5") Integer count) {
        List<ArticleEntity> data = articleService.getRecommendedArticles(count);
        return Result.success(data);
    }

    /**
     * 递增文章浏览量
     *
     * @param articleId 文章 ID
     * @return {@link Result }<{@link String }>
     */
    @NoAuth
    @GetMapping("/view/{articleId}")
    public Result<String> recordView(@PathVariable Integer articleId) {
        articleService.recordView(articleId);
        return Result.success();
    }

    /**
     * 批量导入文章
     *
     * @param list 列表
     * @return {@link Result }<{@link String }>
     * @throws IOException ioexception
     */
    @PostMapping("/import")
    public Result<String> importArticle(@RequestParam MultipartFile[] list) throws IOException {
        articleService.importArticle(list);
        return Result.success();
    }

    /**
     * 批量导出文章
     *
     * @param ids 身份证
     * @return {@link ResponseEntity }<{@link byte[] }>
     */
    @PostMapping("/export")
    public ResponseEntity<byte[]> exportArticle(@RequestBody List<Integer> ids) {
        return articleService.exportArticle(ids);
    }

}
