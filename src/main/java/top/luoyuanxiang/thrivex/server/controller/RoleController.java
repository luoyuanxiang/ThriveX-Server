package top.luoyuanxiang.thrivex.server.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.ann.NoAuth;
import top.luoyuanxiang.thrivex.server.entity.PermissionEntity;
import top.luoyuanxiang.thrivex.server.entity.RoleEntity;
import top.luoyuanxiang.thrivex.server.entity.RouteEntity;
import top.luoyuanxiang.thrivex.server.ann.HasPermission;
import top.luoyuanxiang.thrivex.server.service.IRoleService;
import top.luoyuanxiang.thrivex.server.vo.BindRouteAndPermissionVO;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.security.Permission;
import java.util.List;

/**
 * 角色管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    /**
     * 新增角色
     *
     * @param role 角色
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("role:add")
    @PostMapping
    public Result<String> add(@RequestBody RoleEntity role) {
        boolean res = roleService.save(role);
        return res ? Result.success() : Result.error();
    }

    /**
     * 删除角色
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("role:del")
    @DeleteMapping("/{id}")
    public Result<String> del(@PathVariable Integer id) {
        RoleEntity data = roleService.getById(id);
        if (data == null) return Result.error("该数据不存在");

        boolean res = roleService.removeById(id);
        return res ? Result.success() : Result.error();
    }

    /**
     * 批量删除角色
     *
     * @param ids 身份证
     * @return {@link Result }
     */
    @HasPermission("role:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        boolean res = roleService.removeByIds(ids);
        return res ? Result.success() : Result.error();
    }

    /**
     * 编辑角色
     *
     * @param role 角色
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("role:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody RoleEntity role) {
        boolean res = roleService.updateById(role);
        return res ? Result.success() : Result.error();
    }

    /**
     * 获取角色
     *
     * @param id id
     * @return {@link Result }<{@link RoleEntity }>
     */
    @HasPermission("role:info")
    @GetMapping("/{id}")
    public Result<RoleEntity> get(@PathVariable Integer id) {
        RoleEntity data = roleService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取角色列表
     *
     * @return {@link Result }<{@link List }<{@link RoleEntity }>>
     */
    @HasPermission("role:list")
    @GetMapping
    public Result<List<RoleEntity>> list() {
        List<RoleEntity> data = roleService.list();
        return Result.success(data);
    }

    /**
     * 获取指定角色的路由列表
     *
     * @param id id
     * @return {@link Result }<{@link List }<{@link RoleEntity }>>
     */
    @GetMapping("/route/{id}")
    public Result<List<RouteEntity>> getRouteList(@PathVariable Integer id) {
        List<RouteEntity> list = roleService.getRouteList(id);
        return Result.success(list);
    }

    /**
     * 获取指定角色的权限列表
     *
     * @param id id
     * @return {@link Result }<{@link List }<{@link Permission }>>
     */
    @NoAuth
    @GetMapping("/permission/{id}")
    public Result<List<PermissionEntity>> getPermissionList(@PathVariable Integer id) {
        List<PermissionEntity> list = roleService.getPermissionList(id);
        return Result.success(list);
    }

    /**
     * 分配角色权限
     *
     * @param id   id
     * @param data 数据
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("role:bindingRoute")
    @PatchMapping("/bindingRoute/{id}")
    public Result<String> bindingRoute(@PathVariable Integer id,
                                       @RequestBody BindRouteAndPermissionVO data) {
        roleService.binding(id, data);
        return Result.success();
    }

}
