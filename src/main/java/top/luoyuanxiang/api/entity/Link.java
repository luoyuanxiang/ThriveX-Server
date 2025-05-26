package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.luoyuanxiang.api.base.BaseEntity;


/**
 * <p>
 * 友链
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
public class Link extends BaseEntity<Link> {

    /**
     * 网站标题
     */
    private String title;

    /**
     * 网站描述
     */
    private String description;

    /**
     * 网站邮箱
     */
    private String email;

    /**
     * 网站logo
     */
    private String image;

    /**
     * 网站链接
     */
    private String url;

    private String rss;

    /**
     * 友联顺序
     */
    @TableField("`order`")
    private Integer order;

    /**
     * 网站所绑定的类型
     */
    private Integer typeId;

    /**
     * 是否审核
     */
    private Integer auditStatus;

    @TableField(exist = false)
    private LinkType type;
}
