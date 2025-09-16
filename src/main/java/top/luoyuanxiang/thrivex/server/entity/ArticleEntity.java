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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /**
     * 该文章所绑定的分类ID
     */
    @TableField(exist = false)
    private List<Integer> cateIds;

    /**
     * 该文章所绑定的标签ID
     */
    @TableField(exist = false)
    private List<Integer> tagIds;

    /**
     * 文章配置项
     */
    @TableField(exist = false)
    private ArticleConfigEntity config;

    /**
     * 分类列表
     */
    @TableField(exist = false)
    private List<CateEntity> cateList = new ArrayList<>();

    /**
     * 标签列表
     */
    @TableField(exist = false)
    private List<TagEntity> tagList = new ArrayList<>();

    /**
     * 上一篇文章
     */
    @TableField(exist = false)
    private Map<String, Object> prev;
    /**
     * 下一篇文章
     */
    @TableField(exist = false)
    private Map<String, Object> next;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
