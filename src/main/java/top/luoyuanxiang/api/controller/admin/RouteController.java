package top.luoyuanxiang.api.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.PremName;
import top.luoyuanxiang.api.entity.Route;
import top.luoyuanxiang.api.entity.RouteRole;
import top.luoyuanxiang.api.service.IRouteRoleService;
import top.luoyuanxiang.api.service.IRouteService;
import top.luoyuanxiang.api.utils.Result;

import java.util.List;

/**
 * 路由
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/admin/route")
public class RouteController {
    @Resource
    private IRouteService routeService;
    @Resource
    private IRouteRoleService routeRoleService;

    /**
     * 新增路由
     *
     * @param route 路线
     * @return {@link Result }<{@link ? }>
     */
    @PremName("route:add")
    @PostMapping
    public Result<?> add(@RequestBody Route route) {
        routeService.save(route);

        // 每次新增路由后，自动分配给管理员角色
        RouteRole routeRole = new RouteRole();
        routeRole.setRouteId(route.getId());
        routeRole.setRoleId(1);

        routeRoleService.save(routeRole);

        return Result.success();
    }

    /**
     * 删除路由
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("route:del")
    @DeleteMapping("/{id}")
    public Result<?> del(@PathVariable Integer id) {
        Route data = routeService.getById(id);
        if (data == null) return Result.error("该数据不存在");
        routeService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除路由
     *
     * @param ids IDS
     * @return {@link Result }<{@link ? }>
     */
    @PremName("route:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        routeService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 编辑路由
     *
     * @param route 路线
     * @return {@link Result }<{@link ? }>
     */
    @PremName("route:edit")
    @PatchMapping
    public Result<?> edit(@RequestBody Route route) {
        routeService.updateById(route);
        return Result.success();
    }

    /**
     * 获取路由
     *
     * @param id 身份证
     * @return {@link Result }<{@link Route }>
     */
    @PremName("route:info")
    @GetMapping("/{id}")
    public Result<Route> get(@PathVariable Integer id) {
        Route data = routeService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取路由列表
     *
     * @return {@link Result }<{@link List }<{@link Route }>>
     */
    @PremName("route:list")
    @GetMapping
    public Result<List<Route>> list() {
        List<Route> data = routeService.list();
        return Result.success(data);
    }
}
