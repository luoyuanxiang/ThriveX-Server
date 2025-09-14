package top.luoyuanxiang.thrivex.server.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.entity.RouteEntity;
import top.luoyuanxiang.thrivex.server.entity.RouteRoleEntity;
import top.luoyuanxiang.thrivex.server.security.HasPermission;
import top.luoyuanxiang.thrivex.server.service.IRouteRoleService;
import top.luoyuanxiang.thrivex.server.service.IRouteService;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;

/**
 * 路由管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/route")
public class RouteController {

    @Resource
    private IRouteService routeService;
    @Resource
    private IRouteRoleService routeRoleService;

    /**
     * 新增路由
     *
     * @param route 路线
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("route:add")
    @PostMapping
    public Result<String> add(@RequestBody RouteEntity route) {
        routeService.save(route);

        // 每次新增路由后，自动分配给管理员角色
        RouteRoleEntity routeRole = new RouteRoleEntity();
        routeRole.setRouteId(route.getId());
        routeRole.setRoleId(1);

        routeRoleService.save(routeRole);

        return Result.success();
    }

    /**
     * 删除路由
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("route:del")
    @DeleteMapping("/{id}")
    public Result<String> del(@PathVariable Integer id) {
        RouteEntity data = routeService.getById(id);
        if (data == null) return Result.error("该数据不存在");
        routeService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除路由
     *
     * @param ids 身份证
     * @return {@link Result }
     */
    @HasPermission("route:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        routeService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 编辑路由
     *
     * @param route 路线
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("route:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody RouteEntity route) {
        routeService.updateById(route);
        return Result.success();
    }

    /**
     * 获取路由
     *
     * @param id id
     * @return {@link Result }<{@link RouteEntity }>
     */
    @HasPermission("route:info")
    @GetMapping("/{id}")
    public Result<RouteEntity> get(@PathVariable Integer id) {
        RouteEntity data = routeService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取路由列表
     *
     * @return {@link Result }<{@link List }<{@link RouteEntity }>>
     */
    @HasPermission("route:list")
    @GetMapping
    public Result<List<RouteEntity>> list() {
        List<RouteEntity> data = routeService.list();
        return Result.success(data);
    }

}
