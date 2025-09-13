//package top.luoyuanxiang.thrivex.server.controller;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import jakarta.annotation.Resource;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import top.luoyuanxiang.thrivex.server.entity.Article;
//import top.luoyuanxiang.thrivex.server.security.HasPermission;
//import top.luoyuanxiang.thrivex.server.service.IArticleService;
//import top.luoyuanxiang.thrivex.server.vo.Paging;
//import top.luoyuanxiang.thrivex.server.vo.Result;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
///**
// * 文章管理
// *
// * @author luoyuanxiang
// * @since 2025-09-12
// */
//@RestController
//@RequestMapping("/article")
//public class ArticleController {
//
//    @Resource
//    private IArticleService articleService;
//
//    /**
//     * 新增文章
//     *
//     * @param articledFormDTO 文章形式 DTO
//     * @return {@link Result }<{@link String }>
//     */
//    @HasPermission("article:add")
//    @PostMapping
//    public Result<String> add(@RequestBody ArticleFormDTO articledFormDTO) {
//        articleService.add(articledFormDTO);
//        return Result.success();
//    }
//
//    @HasPermission("article:del")
//    @DeleteMapping("/{id}/{is_del}")
//    @ApiOperation("删除文章")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 2)
//    public Result<String> del(@PathVariable Integer id, @PathVariable Integer is_del) {
//        articleService.del(id, is_del);
//        return Result.success();
//    }
//
//    @HasPermission("article:reduction")
//    @PatchMapping("/reduction/{id}")
//    @ApiOperation("还原被删除的文章")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 3)
//    public Result<String> reduction(@PathVariable Integer id) {
//        articleService.reduction(id);
//        return Result.success();
//    }
//
//    @HasPermission("article:del")
//    @DeleteMapping("/batch")
//    @ApiOperation("批量删除文章")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 4)
//    public Result batchDel(@RequestBody List<Integer> ids) {
//        articleService.delBatch(ids);
//        return Result.success();
//    }
//
//    @HasPermission("article:edit")
//    @PatchMapping
//    @ApiOperation("编辑文章")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 5)
//    public Result<String> edit(@RequestBody ArticleFormDTO articleFormDTO) {
//        articleService.edit(articleFormDTO);
//        return Result.success();
//    }
//
//    @GetMapping("/{id}")
//    @ApiOperation("获取文章")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 6)
//    public Result<Article> get(@PathVariable Integer id, @RequestParam(defaultValue = "") String password) {
//        password = !password.isEmpty() ? password : "";
//        Article data = articleService.get(id, password);
//        return Result.success(data);
//    }
//
//    @NoTokenRequired
//    @PostMapping("/list")
//    @ApiOperation("获取文章列表")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 7)
//    public Result<List<Article>> list(@RequestBody ArticleFillterVo filterVo, @RequestHeader(value = "Authorization", required = false) String token) {
//        List<Article> data = articleService.list(filterVo, token);
//        return Result.success(data);
//    }
//
//    @NoTokenRequired
//    @PostMapping("/paging")
//    @ApiOperation("分页查询文章列表")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 8)
//    public Result paging(@RequestBody ArticleFillterVo filterVo, PageVo pageVo, @RequestHeader(value = "Authorization", required = false) String token) {
//        Page<Article> list = articleService.paging(filterVo, pageVo, token);
//        Map<String, Object> result = Paging.filter(list);
//        return Result.success(result);
//    }
//
//    @GetMapping("/cate/{cate_id}")
//    @ApiOperation("获取指定分类的文章")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 9)
//    public Result getCateArticleList(@PathVariable Integer cate_id, PageVo pageVo) {
//        Page<Article> list = articleService.getCateArticleList(cate_id, pageVo);
//        Map<String, Object> result = Paging.filter(list);
//        return Result.success(result);
//    }
//
//    @GetMapping("/tag/{tag_id}")
//    @ApiOperation("获取指定标签的文章")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 10)
//    public Result getTagArticleList(@PathVariable Integer tag_id, PageVo pageVo) {
//        Page<Article> list = articleService.getTagArticleList(tag_id, pageVo);
//        Map<String, Object> result = Paging.filter(list);
//        return Result.success(result);
//    }
//
//    @GetMapping("/random")
//    @ApiOperation("随机获取文章数据")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 11)
//    public Result<List<Article>> getRandomArticles(@ApiParam(value = "默认随机获取5篇文章，可以通过count指定数量") @RequestParam(defaultValue = "5") Integer count) {
//        List<Article> data = articleService.getRandomArticles(count);
//        return Result.success(data);
//    }
//
//    @GetMapping("/hot")
//    @ApiOperation("获取热门文章数据")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 12)
//    public Result<List<Article>> getRecommendedArticles(@ApiParam(value = "默认浏览量最高的5篇文章，可以通过count指定数量") @RequestParam(defaultValue = "5") Integer count) {
//        List<Article> data = articleService.getRecommendedArticles(count);
//        return Result.success(data);
//    }
//
//    @GetMapping("/view/{article_id}")
//    @ApiOperation("递增文章浏览量")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 13)
//    public Result<String> recordView(@PathVariable Integer article_id) {
//        articleService.recordView(article_id);
//        return Result.success();
//    }
//
//    @PostMapping("/import")
//    @ApiOperation("批量导入文章")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 14)
//    public Result<String> importArticle(@RequestParam MultipartFile[] list) throws IOException {
//        articleService.importArticle(list);
//        return Result.success();
//    }
//
//    @PostMapping("/export")
//    @ApiOperation("批量导出文章")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 15)
//    public ResponseEntity<byte[]> exportArticle(@RequestBody List<Integer> ids) {
//        return articleService.exportArticle(ids);
//    }
//
//}
