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
@TableName("route_role")
public class RouteRoleEntity extends Model<RouteRoleEntity> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 路由id
     */
    @TableField("route_id")
    private Integer routeId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Integer roleId;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
