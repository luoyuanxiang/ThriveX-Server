package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.luoyuanxiang.api.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 文章
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
public class Article extends BaseEntity<Article> {

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章介绍
     */
    private String description;

    /**
     * 文章主要内容
     */
    private String content;

    /**
     * 文章封面
     */
    private String cover;

    /**
     * 文章浏览量
     */
    private Integer view;

    /**
     * 评论数量
     */
    private Integer comment;

    /**
     * 该文章所绑定的分类ID
     */
    @TableField(exist = false)
    private List<Integer> cateIds;
    /**
     * 分类列表
     */
    @TableField(exist = false)
    private List<Cate> cateList = new ArrayList<>();

    /**
     * 该文章所绑定的标签ID
     */
    @TableField(exist = false)
    private List<Integer> tagIds;
    /**
     * 标签列表
     */
    @TableField(exist = false)
    private List<Tag> tagList = new ArrayList<>();

    /**
     * 文章配置项
     */
    @TableField(exist = false)
    private ArticleConfig config;

    @TableField(exist = false)
    private Map<String, Object> prev;
    @TableField(exist = false)
    private Map<String, Object> next;
}
