package top.luoyuanxiang.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.luoyuanxiang.api.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.api.vo.comment.CommentFilterVo;

import java.util.List;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 列表
     *
     * @param filterVo filter vo
     * @return {@link List }<{@link Comment }>
     */
    List<Comment> list(CommentFilterVo filterVo);

    /**
     * 获取
     *
     * @param id 身份证
     * @return {@link Comment }
     */
    Comment get(Integer id);

    /**
     * 分页
     *
     * @param filterVo filter vo
     * @param page     页
     */
    void paging(CommentFilterVo filterVo, Page<Comment> page);

    /**
     * 获取指定文章中所有评论
     *
     * @param articleId 文章 ID
     * @param page      页
     */
    void getArticleCommentList(Integer articleId, Page<Comment> page);

    /**
     * 添加评论
     *
     * @param comment 评论
     */
    void add(Comment comment);
}
