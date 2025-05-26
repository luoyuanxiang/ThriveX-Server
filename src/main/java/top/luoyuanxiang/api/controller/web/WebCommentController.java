package top.luoyuanxiang.api.controller.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.NoTokenRequired;
import top.luoyuanxiang.api.entity.Comment;
import top.luoyuanxiang.api.service.ICommentService;
import top.luoyuanxiang.api.utils.Result;
import top.luoyuanxiang.api.vo.comment.CommentFilterVo;

import java.util.List;

/**
 * 评论管理
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/web/comment")
public class WebCommentController {

    @Resource
    private ICommentService commentService;

    /**
     * 新增评论
     *
     * @param comment 评论
     * @return {@link Result }<{@link ? }>
     * @throws Exception 例外
     */
    @PostMapping
    public Result<?> add(@RequestBody Comment comment) throws Exception {
        commentService.add(comment);
        return Result.success();
    }

    /**
     * 获取评论
     *
     * @param id 身份证
     * @return {@link Result }<{@link Comment }>
     */
    @GetMapping("/{id}")
    public Result<Comment> get(@PathVariable Integer id) {
        Comment data = commentService.get(id);
        return Result.success(data);
    }

    /**
     * 获取评论列表
     *
     * @param filterVo filter vo
     * @return {@link Result }<{@link List }<{@link Comment }>>
     */
    @NoTokenRequired
    @PostMapping("/list")
    public Result<List<Comment>> list(@RequestBody CommentFilterVo filterVo) {
        List<Comment> list = commentService.list(filterVo);
        return Result.success(list);
    }

    /**
     * 分页查询评论列表
     *
     * @param filterVo filter vo
     * @param page     页
     * @return {@link Result }<{@link Page }<{@link Comment }>>
     */
    @PostMapping("/paging")
    public Result<Page<Comment>> paging(@RequestBody CommentFilterVo filterVo, Page<Comment> page) {
        commentService.paging(filterVo, page);
        return Result.success(page);
    }

    /**
     * 获取指定文章中所有评论
     *
     * @param articleId 文章 ID
     * @param page      页
     * @return {@link Result }<{@link Page }<{@link Comment }>>
     */
    @NoTokenRequired
    @PostMapping("/article/{articleId}")
    public Result<Page<Comment>> getArticleCommentList(@PathVariable Integer articleId, Page<Comment> page) {
        commentService.getArticleCommentList(articleId, page);
        return Result.success(page);
    }

}
