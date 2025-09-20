package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.entity.*;
import top.luoyuanxiang.thrivex.server.mapper.RoleMapper;
import top.luoyuanxiang.thrivex.server.service.*;
import top.luoyuanxiang.thrivex.server.vo.BindRouteAndPermissionVO;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements IRoleService {

    @Resource
    private IRouteService routeService;
    @Resource
    private IPermissionService permissionService;
    @Resource
    private IRouteRoleService routeRoleService;
    @Resource
    private IRolePermissionService rolePermissionService;

    @Override
    public List<RouteEntity> getRouteList(Integer id) {
        if (id != 1) return baseMapper.getRouteList(id);
        return routeService.list();
    }

    @Override
    public List<PermissionEntity> getPermissionList(Integer id) {
        if (id != 1) return baseMapper.getPermissionList(id);
        return permissionService.list();
    }

    @Override
    public void binding(Integer id, BindRouteAndPermissionVO data) {

        if (id == 1) return;

        // 先删除当前角色绑定的所有路由和权限
        routeRoleService.lambdaUpdate()
                .eq(RouteRoleEntity::getRoleId, id)
                .remove();
        rolePermissionService.lambdaUpdate()
                .eq(RolePermissionEntity::getRoleId, id)
                .remove();
        List<RouteRoleEntity> routeRoleEntityList = data.routeIds().parallelStream()
                .map(routeId -> {
                    RouteRoleEntity routeRole = new RouteRoleEntity();
                    routeRole.setRoleId(id);
                    routeRole.setRouteId(routeId);
                    return routeRole;
                }).toList();
        routeRoleService.saveBatch(routeRoleEntityList);

        List<RolePermissionEntity> rolePermissionEntityList = data.permissionIds().parallelStream()
                .map(permissionId -> {
                    RolePermissionEntity rolePermission = new RolePermissionEntity();
                    rolePermission.setRoleId(id);
                    rolePermission.setPermissionId(permissionId);
                    return rolePermission;
                }).toList();
        rolePermissionService.saveBatch(rolePermissionEntityList);

    }
}
