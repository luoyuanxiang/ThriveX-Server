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
@TableName("article")
public class ArticleEntity extends Model<ArticleEntity> {

    /**
     * 文章ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章标题
     */
    @TableField("title")
    private String title;

    /**
     * 文章介绍
     */
    @TableField("description")
    private String description;

    /**
     * 文章主要内容
     */
    @TableField("content")
    private String content;

    /**
     * 文章封面
     */
    @TableField("cover")
    private String cover;

    /**
     * 文章浏览量
     */
    @TableField("view")
    private Integer view;

    /**
     * 评论数量
     */
    @TableField("comment")
    private Integer comment;

    /**
     * 文章创建时间
     */
    @TableField("create_time")
    private String createTime;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
