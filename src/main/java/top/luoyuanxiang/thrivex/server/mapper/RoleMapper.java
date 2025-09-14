package top.luoyuanxiang.thrivex.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.luoyuanxiang.thrivex.server.entity.PermissionEntity;
import top.luoyuanxiang.thrivex.server.entity.RoleEntity;
import top.luoyuanxiang.thrivex.server.entity.RouteEntity;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {

    /**
     * 查询指定角色的所有菜单
     *
     * @param id id
     * @return {@link List }<{@link RouteEntity }>
     */
    @Select("select a.* from route a, role b, route_role c where c.route_id = a.id and c.role_id = b.id and b.id = #{id}")
    List<RouteEntity> getRouteList(Integer id);


    /**
     * 查询指定角色的所有权限
     *
     * @param id id
     * @return {@link List }<{@link PermissionEntity }>
     */
    @Select("select p.* from role r, permission p, role_permission rp where  r.id = rp.role_id and p.id = rp.permission_id and r.id = #{id}")
    List<PermissionEntity> getPermissionList(Integer id);

}
