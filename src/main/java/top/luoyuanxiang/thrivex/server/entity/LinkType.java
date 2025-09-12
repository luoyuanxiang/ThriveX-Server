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
 * 网站类型
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Getter
@Setter
@ToString
@TableName("link_type")
public class LinkType extends Model<LinkType> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型名称
     */
    @TableField("name")
    private String name;

    /**
     * 用户是否可选择
     */
    @TableField("is_admin")
    private Integer isAdmin;

    /**
     * 显示顺序
     */
    @TableField("order")
    private Integer order;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
