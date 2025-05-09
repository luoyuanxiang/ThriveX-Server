package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <p>
 * 角色和路由
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
@TableName("route_role")
public class RouteRole extends Model<RouteRole> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 路由id
     */
    private Integer routeId;

    /**
     * 角色id
     */
    private Integer roleId;
}
