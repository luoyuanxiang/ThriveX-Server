package top.luoyuanxiang.thrivex.server.vo;

import java.util.List;

/**
 * 权限角色
 *
 * @param routeIds      路由id列表
 * @param permissionIds 权限id列表
 * @author luoyuanxiang
 */
public record BindRouteAndPermissionVO(List<Integer> routeIds, List<Integer> permissionIds) {
}
