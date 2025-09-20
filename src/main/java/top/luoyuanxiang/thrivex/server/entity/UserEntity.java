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
@TableName("user")
public class UserEntity extends Model<UserEntity> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 用户名称
     */
    @TableField("name")
    private String name;

    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 用户头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 用户介绍
     */
    @TableField("info")
    private String info;

    /**
     * 用户角色ID
     */
    @TableField("role_id")
    private String roleId;

    /**
     * 用户创建时间
     */
    @TableField("create_time")
    private String createTime;

    @TableField(exist = false)
    private RoleEntity role;

    @TableField(exist = false)
    private String permissionsCode;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
