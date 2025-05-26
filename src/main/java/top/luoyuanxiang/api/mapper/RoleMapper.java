package top.luoyuanxiang.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.luoyuanxiang.api.entity.Permission;
import top.luoyuanxiang.api.entity.Role;
import top.luoyuanxiang.api.entity.Route;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 获取权限列表
     *
     * @param roleId 角色 ID
     * @return {@link List }<{@link Permission }>
     */
    @Select("select p.* from role r, permission p, role_permission rp where  r.id = rp.role_id and p.id = rp.permission_id and r.id = #{roleId}")
    List<Permission> getPermissionList(@Param("roleId") Integer roleId);

    /**
     * 获取路由列表
     *
     * @param roleId 角色 ID
     * @return {@link List }<{@link Route }>
     */
    @Select("select a.* from route a, role b, route_role c where c.route_id = a.id and c.role_id = b.id and b.id = #{roleId}")
    List<Route> getRouteList(@Param("roleId") Integer roleId);
}
