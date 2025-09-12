package top.luoyuanxiang.thrivex.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Getter
@Setter
@ToString
@TableName("comment")
public class Comment extends Model<Comment> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 评论者名称
     */
    @TableField("name")
    private String name;

    /**
     * 评论者头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 评论者邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 评论者网站
     */
    @TableField("url")
    private String url;

    /**
     * 所属文章ID
     */
    @TableField("article_id")
    private Integer articleId;

    /**
     * 二级评论
     */
    @TableField("comment_id")
    private Integer commentId;

    /**
     * 是否审核
     */
    @TableField("audit_status")
    private Integer auditStatus;

    @TableField("create_time")
    private String createTime;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
