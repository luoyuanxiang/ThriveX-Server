package top.luoyuanxiang.api.service;

import top.luoyuanxiang.api.entity.Permission;
import top.luoyuanxiang.api.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.api.entity.Route;
import top.luoyuanxiang.api.vo.role.BindRouteAndPermission;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
public interface IRoleService extends IService<Role> {

    /**
     * 获取权限列表
     *
     * @param roleId 角色 ID
     * @return {@link List }<{@link Permission }>
     */
    List<Permission> getPermissionList(Integer roleId);

    /**
     * 获取路由列表
     *
     * @param roleId 角色 ID
     * @return {@link List }<{@link Route }>
     */
    List<Route> getRouteList(Integer roleId);

    /**
     * 分配角色权限
     *
     * @param roleId   就是ID
     * @param data 数据
     */
    void binding(Integer roleId, BindRouteAndPermission data);
}
