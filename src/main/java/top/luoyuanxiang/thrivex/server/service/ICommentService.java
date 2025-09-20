package top.luoyuanxiang.thrivex.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.thrivex.server.entity.CommentEntity;
import top.luoyuanxiang.thrivex.server.vo.CommentQueryVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
public interface ICommentService extends IService<CommentEntity> {

    /**
     * 新增评论
     *
     * @param comment 评论
     */
    void add(CommentEntity comment);

    /**
     * 列表
     *
     * @param commentQueryVO 评论查询 vo
     * @return {@link List }<{@link CommentEntity }>
     */
    List<CommentEntity> list(CommentQueryVO commentQueryVO);

    /**
     * 分页查询评论列表
     *
     * @param page           页
     * @param commentQueryVO 评论查询 vo
     * @return {@link Page }<{@link CommentEntity }>
     */
    Page<CommentEntity> paging(Page<CommentEntity> page, CommentQueryVO commentQueryVO);
}
