package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.luoyuanxiang.api.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 评论
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
public class Comment extends BaseEntity<Comment> {

    /**
     * 评论者名称
     */
    private String name;

    /**
     * 评论者头像
     */
    private String avatar;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论者邮箱
     */
    private String email;

    /**
     * 评论者网站
     */
    private String url;

    /**
     * 所属文章ID
     */
    private Integer articleId;

    /**
     * 二级评论
     */
    private Integer commentId;

    /**
     * 是否审核
     */
    private Integer auditStatus;

    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Comment> children = new ArrayList<>();

    /**
     * 文章标题
     */
    @TableField(exist = false)
    private String articleTitle;
}
