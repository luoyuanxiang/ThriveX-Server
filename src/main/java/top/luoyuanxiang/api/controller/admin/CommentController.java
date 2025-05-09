package top.luoyuanxiang.api.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.NoTokenRequired;
import top.luoyuanxiang.api.annotation.PremName;
import top.luoyuanxiang.api.entity.Comment;
import top.luoyuanxiang.api.execption.CustomException;
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
@RequestMapping("/api/admin/comment")
public class CommentController {

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
     * 删除评论
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("comment:del")
    @DeleteMapping("/{id}")
    public Result<?> del(@PathVariable Integer id) {
        Comment data = commentService.getById(id);
        if (data == null) return Result.error("删除评论失败：该评论不存在");
        commentService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除评论
     *
     * @param ids IDS
     * @return {@link Result }<{@link ? }>
     */
    @PremName("comment:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        commentService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 编辑评论
     *
     * @param comment 评论
     * @return {@link Result }<{@link ? }>
     */
    @PremName("comment:edit")
    @PatchMapping
    public Result<?> edit(@RequestBody Comment comment) {
        commentService.updateById(comment);
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

    /**
     * 审核指定评论
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("comment:audit")
    @PatchMapping("/audit/{id}")
    public Result<?> auditComment(@PathVariable Integer id) {
        Comment data = commentService.getById(id);

        if (data == null) throw new CustomException(400, "该评论不存在");

        data.setAuditStatus(1);
        commentService.updateById(data);
        return Result.success();
    }

}
