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
 * 角色权限
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Getter
@Setter
@ToString
@TableName("permission")
public class Permission extends Model<Permission> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限标识
     */
    @TableField("name")
    private String name;

    /**
     * 权限介绍
     */
    @TableField("description")
    private String description;

    /**
     * 权限分组
     */
    @TableField("group")
    private String group;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
