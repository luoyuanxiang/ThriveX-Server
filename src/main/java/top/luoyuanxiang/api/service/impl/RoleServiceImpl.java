package top.luoyuanxiang.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.api.entity.*;
import top.luoyuanxiang.api.mapper.RoleMapper;
import top.luoyuanxiang.api.mapper.RolePermissionMapper;
import top.luoyuanxiang.api.mapper.RouteRoleMapper;
import top.luoyuanxiang.api.service.IRoleService;
import top.luoyuanxiang.api.vo.role.BindRouteAndPermission;

import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RouteRoleMapper routeRoleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Permission> getPermissionList(Integer roleId) {
        return baseMapper.getPermissionList(roleId);
    }

    @Override
    public List<Route> getRouteList(Integer roleId) {
        return baseMapper.getRouteList(roleId);
    }

    @Override
    public void binding(Integer roleId, BindRouteAndPermission data) {
// 先删除当前角色绑定的所有路由和权限
        QueryWrapper<RouteRole> routeQueryWrapper = new QueryWrapper<>();
        routeQueryWrapper.eq("role_id", roleId);
        routeRoleMapper.delete(routeQueryWrapper);

        QueryWrapper<RolePermission> permissionQueryWrapper = new QueryWrapper<>();
        permissionQueryWrapper.eq("role_id", roleId);
        rolePermissionMapper.delete(permissionQueryWrapper);

        // 然后再重新给角色绑定路由和权限
        for (Integer routeId : data.getRoute_ids()) {
            RouteRole routeRole = new RouteRole();
            routeRole.setRoleId(roleId);
            routeRole.setRouteId(routeId);
            routeRoleMapper.insert(routeRole);
        }

        for (Integer permissionId : data.getPermission_ids()) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionMapper.insert(rolePermission);
        }
    }
}
