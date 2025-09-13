//package top.luoyuanxiang.thrivex.server.controller;
//
//import cn.hutool.core.bean.BeanUtil;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import jakarta.annotation.Resource;
//import org.springframework.web.bind.annotation.*;
//import top.luoyuanxiang.thrivex.server.entity.Comment;
//import top.luoyuanxiang.thrivex.server.service.ICommentService;
//import top.luoyuanxiang.thrivex.server.vo.Paging;
//import top.luoyuanxiang.thrivex.server.vo.Result;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 评论管理
// *
// * @author luoyuanxiang
// * @since 2025-09-12
// */
//@RestController
//@RequestMapping("/comment")
//public class CommentController {
//
//    @Resource
//    private ICommentService commentService;
//
//    /**
//     * 新增评论
//     *
//     * @param commentFormDTO 评论表 DTO
//     * @return {@link Result }<{@link String }>
//     * @throws Exception 例外
//     */
//    @PostMapping
//    @ApiOperation("")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 1)
//    public Result<String> add(@RequestBody CommentFormDTO commentFormDTO) throws Exception {
//        Comment comment =  BeanUtil.copyProperties(commentFormDTO, Comment.class);
//        commentService.add(comment);
//        return Result.success();
//    }
//
//    @PremName("comment:del")
//    @DeleteMapping("/{id}")
//    @ApiOperation("删除评论")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 2)
//    public Result<String> del(@PathVariable Integer id) {
//        Comment data = commentService.getById(id);
//        if (data == null) return Result.error("删除评论失败：该评论不存在");
//        commentService.removeById(id);
//        return Result.success();
//    }
//
//    @PremName("comment:del")
//    @DeleteMapping("/batch")
//    @ApiOperation("批量删除评论")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 3)
//    public Result batchDel(@RequestBody List<Integer> ids) {
//        commentService.removeByIds(ids);
//        return Result.success();
//    }
//
//    @PremName("comment:edit")
//    @PatchMapping
//    @ApiOperation("编辑评论")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 4)
//    public Result<String> edit(@RequestBody CommentFormDTO commentFormDTO) {
//        Comment comment =  BeanUtil.copyProperties(commentFormDTO, Comment.class);
//        commentService.updateById(comment);
//        return Result.success();
//    }
//
//    @GetMapping("/{id}")
//    @ApiOperation("获取评论")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 5)
//    public Result<Comment> get(@PathVariable Integer id) {
//        Comment data = commentService.get(id);
//        return Result.success(data);
//    }
//
//    @NoTokenRequired
//    @PostMapping("/list")
//    @ApiOperation("获取评论列表")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 6)
//    public Result<List<Comment>> list(@RequestBody CommentFilterVo filterVo) {
//        List<Comment> list = commentService.list(filterVo);
//        return Result.success(list);
//    }
//
//    @NoTokenRequired
//    @PostMapping("/paging")
//    @ApiOperation("分页查询评论列表")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 7)
//    public Result paging(@RequestBody CommentFilterVo filterVo, PageVo pageVo) {
//        Page<Comment> list = commentService.paging(filterVo, pageVo);
//        Map<String, Object> result = Paging.filter(list);
//        return Result.success(result);
//    }
//
//    @NoTokenRequired
//    @PostMapping("/article/{articleId}")
//    @ApiOperation("获取指定文章中所有评论")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 8)
//    public Result getArticleCommentList(@PathVariable Integer articleId, PageVo pageVo) {
//        Page<Comment> list = commentService.getArticleCommentList(articleId, pageVo);
//        Map<String, Object> result = Paging.filter(list);
//        return Result.success(result);
//    }
//
//    @PremName("comment:audit")
//    @PatchMapping("/audit/{id}")
//    @ApiOperation("审核指定评论")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 9)
//    public Result auditComment(@PathVariable Integer id) {
//        Comment data = commentService.getById(id);
//
//        if (data == null) throw new CustomException(400, "该评论不存在");
//
//        data.setAuditStatus(1);
//        commentService.updateById(data);
//        return Result.success();
//    }
//
//}
