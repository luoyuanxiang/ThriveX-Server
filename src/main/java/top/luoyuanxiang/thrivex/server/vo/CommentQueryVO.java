package top.luoyuanxiang.thrivex.server.vo;

import lombok.Getter;
import lombok.Setter;
import top.luoyuanxiang.thrivex.server.entity.CommentEntity;

/**
 * 评论查询
 *
 * @author luoyuanxiang
 */
@Getter
@Setter
public class CommentQueryVO extends QueryCommonVO<CommentEntity> {

    /**
     * 默认为树形结构，如果设置了list模式，则查询列表结构
     */
    private String pattern;

    /**
     * 0表示获取待审核的评论 | 1表示获取审核通过的评论（默认）
     */
    private Integer status = 1;

    /**
     * 根据内容关键词筛选
     */
    private String content;

    /**
     * 文章 ID
     */
    private Integer articleId;

    private Integer id;
}
