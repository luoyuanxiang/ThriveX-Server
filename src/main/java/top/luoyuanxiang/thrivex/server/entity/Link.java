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
@TableName("link")
public class Link extends Model<Link> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 网站标题
     */
    @TableField("title")
    private String title;

    /**
     * 网站描述
     */
    @TableField("description")
    private String description;

    /**
     * 网站邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 网站logo
     */
    @TableField("image")
    private String image;

    /**
     * 网站链接
     */
    @TableField("url")
    private String url;

    @TableField("rss")
    private String rss;

    /**
     * 友联顺序
     */
    @TableField("order")
    private Integer order;

    /**
     * 网站所绑定的类型
     */
    @TableField("type_id")
    private Integer typeId;

    /**
     * 是否审核
     */
    @TableField("audit_status")
    private Integer auditStatus;

    /**
     * 网站创建时间
     */
    @TableField("create_time")
    private String createTime;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
