//package top.luoyuanxiang.thrivex.server.controller;
//
//import jakarta.annotation.Resource;
//import org.springframework.web.bind.annotation.*;
//import top.luoyuanxiang.thrivex.server.entity.RouteRole;
//import top.luoyuanxiang.thrivex.server.vo.Result;
//
//import java.util.List;
//
///**
// * 路由管理
// *
// * @author luoyuanxiang
// * @since 2025-09-12
// */
//@RestController
//@RequestMapping("/route")
//public class RouteController {
//
//    @Resource
//    private RouteService routeService;
//    @Resource
//    private RouteRoleService routeRoleService;
//
//    @PremName("route:add")
//    @PostMapping
//    @ApiOperation("新增路由")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 1)
//    public Result<String> add(@RequestBody Route route) {
//        routeService.save(route);
//
//        // 每次新增路由后，自动分配给管理员角色
//        RouteRole routeRole = new RouteRole();
//        routeRole.setRouteId(route.getId());
//        routeRole.setRoleId(1);
//
//        routeRoleService.save(routeRole);
//
//        return Result.success();
//    }
//
//    @PremName("route:del")
//    @DeleteMapping("/{id}")
//    @ApiOperation("删除路由")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 2)
//    public Result<String> del(@PathVariable Integer id) {
//        Route data = routeService.getById(id);
//        if (data == null) return Result.error("该数据不存在");
//        routeService.removeById(id);
//        return Result.success();
//    }
//
//    @PremName("route:del")
//    @DeleteMapping("/batch")
//    @ApiOperation("批量删除路由")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 3)
//    public Result batchDel(@RequestBody List<Integer> ids) {
//        routeService.removeByIds(ids);
//        return Result.success();
//    }
//
//    @PremName("route:edit")
//    @PatchMapping
//    @ApiOperation("编辑路由")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 4)
//    public Result<String> edit(@RequestBody Route route) {
//        routeService.updateById(route);
//        return Result.success();
//    }
//
//    @PremName("route:info")
//    @GetMapping("/{id}")
//    @ApiOperation("获取路由")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 5)
//    public Result<Route> get(@PathVariable Integer id) {
//        Route data = routeService.getById(id);
//        return Result.success(data);
//    }
//
//    @PremName("route:list")
//    @GetMapping
//    @ApiOperation("获取路由列表")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 6)
//    public Result<List<Route>> list() {
//        List<Route> data = routeService.list();
//        return Result.success(data);
//    }
//
//}
