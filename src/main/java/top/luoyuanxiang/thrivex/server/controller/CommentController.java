package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.ann.NoAuth;
import top.luoyuanxiang.thrivex.server.entity.CommentEntity;
import top.luoyuanxiang.thrivex.server.ann.HasPermission;
import top.luoyuanxiang.thrivex.server.service.ICommentService;
import top.luoyuanxiang.thrivex.server.vo.CommentQueryVO;
import top.luoyuanxiang.thrivex.server.vo.Paging;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;

/**
 * 评论管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private ICommentService commentService;

    /**
     * 新增评论
     *
     * @param comment 评论表 DTO
     * @return {@link Result }<{@link String }>
     * @throws Exception 例外
     */
    @NoAuth
    @PostMapping
    public Result<String> add(@RequestBody CommentEntity comment) throws Exception {
        commentService.add(comment);
        return Result.success();
    }

    /**
     * 删除评论
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("comment:del")
    @DeleteMapping("/{id}")
    public Result<String> del(@PathVariable Integer id) {
        CommentEntity data = commentService.getById(id);
        if (data == null) return Result.error("删除评论失败：该评论不存在");
        commentService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除评论
     *
     * @param ids 身份证
     * @return {@link Result }
     */
    @HasPermission("comment:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        commentService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 编辑评论
     *
     * @param comment 评论
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("comment:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody CommentEntity comment) {
        commentService.updateById(comment);
        return Result.success();
    }

    /**
     * 获取评论
     *
     * @param id id
     * @return {@link Result }<{@link CommentEntity }>
     */
    @GetMapping("/{id}")
    public Result<CommentEntity> get(@PathVariable Integer id) {
        CommentQueryVO commentQueryVO = new CommentQueryVO();
        commentQueryVO.setStatus(null);
        commentQueryVO.setId(id);
        List<CommentEntity> list = commentService.list(commentQueryVO);
        if (list.isEmpty()) return Result.success("获取评论列表失败：没有评论");

        CommentEntity data = list.get(0);
        return Result.success(data);
    }

    /**
     * 获取评论列表
     *
     * @param commentQueryVO 评论查询 vo
     * @return {@link Result }<{@link List }<{@link CommentEntity }>>
     */
    @NoAuth
    @PostMapping("/list")
    public Result<List<CommentEntity>> list(@RequestBody CommentQueryVO commentQueryVO) {
        List<CommentEntity> list = commentService.list(commentQueryVO);
        return Result.success(list);
    }

    /**
     * 分页查询评论列表
     *
     * @param commentQueryVO 评论查询 vo
     * @param page           页
     * @param size           大小
     * @return {@link Result }<{@link Paging }<{@link CommentEntity }>>
     */
    @NoAuth
    @PostMapping("/paging")
    public Result<Paging<CommentEntity>> paging(@RequestBody CommentQueryVO commentQueryVO,
                                                @RequestParam(defaultValue = "1") Integer page,
                                                @RequestParam(defaultValue = "10") Integer size) {
        Page<CommentEntity> list = commentService.paging(new Page<>(page, size), commentQueryVO);
        return Result.page(list);
    }

    /**
     * 获取指定文章中所有评论
     *
     * @param articleId 文章 ID
     * @param page      页
     * @param size      大小
     * @return {@link Result }<{@link Paging }<{@link CommentEntity }>>
     */
    @NoAuth
    @PostMapping("/article/{articleId}")
    public Result<Paging<CommentEntity>> getArticleCommentList(@PathVariable Integer articleId,
                                                               @RequestParam(defaultValue = "1") Integer page,
                                                               @RequestParam(defaultValue = "10") Integer size) {
        CommentQueryVO commentQueryVO = new CommentQueryVO();
        commentQueryVO.setArticleId(articleId);
        Page<CommentEntity> list = commentService.paging(new Page<>(page, size), commentQueryVO);
        return Result.page(list);
    }

    /**
     * 审核指定评论
     *
     * @param id id
     * @return {@link Result }<{@link ? }>
     */
    @HasPermission("comment:audit")
    @PatchMapping("/audit/{id}")
    public Result<?> auditComment(@PathVariable Integer id) {
        CommentEntity data = commentService.getById(id);

        if (data == null) throw new RuntimeException("该评论不存在");

        data.setAuditStatus(1);
        commentService.updateById(data);
        return Result.success();
    }

}
