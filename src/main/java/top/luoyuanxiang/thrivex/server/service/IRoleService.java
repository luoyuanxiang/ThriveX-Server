package top.luoyuanxiang.thrivex.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.thrivex.server.entity.PermissionEntity;
import top.luoyuanxiang.thrivex.server.entity.RoleEntity;
import top.luoyuanxiang.thrivex.server.entity.RouteEntity;
import top.luoyuanxiang.thrivex.server.vo.BindRouteAndPermissionVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
public interface IRoleService extends IService<RoleEntity> {

    /**
     * 获取指定角色的路由列表
     *
     * @param id id
     * @return {@link List }<{@link RouteEntity }>
     */
    List<RouteEntity> getRouteList(Integer id);

    /**
     * 获取指定角色的权限列表
     *
     * @param id id
     * @return {@link List }<{@link PermissionEntity }>
     */
    List<PermissionEntity> getPermissionList(Integer id);

    /**
     * 分配角色权限
     *
     * @param id   id
     * @param data 数据
     */
    void binding(Integer id, BindRouteAndPermissionVO data);
}
