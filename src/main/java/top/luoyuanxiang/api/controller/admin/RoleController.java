package top.luoyuanxiang.api.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.PremName;
import top.luoyuanxiang.api.entity.Permission;
import top.luoyuanxiang.api.entity.Role;
import top.luoyuanxiang.api.entity.Route;
import top.luoyuanxiang.api.service.IRoleService;
import top.luoyuanxiang.api.utils.Result;
import top.luoyuanxiang.api.vo.role.BindRouteAndPermission;

import java.util.List;

/**
 * 角色
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/admin/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    /**
     * 新增角色
     *
     * @param role 角色
     * @return {@link Result }<{@link ? }>
     */
    @PremName("role:add")
    @PostMapping
    public Result<?> add(@RequestBody Role role) {
        boolean res = roleService.save(role);
        return res ? Result.success() : Result.error();
    }

    /**
     * 删除角色
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("role:del")
    @DeleteMapping("/{id}")
    public Result<?> del(@PathVariable Integer id) {
        Role data = roleService.getById(id);
        if (data == null) return Result.error("该数据不存在");

        boolean res = roleService.removeById(id);
        return res ? Result.success() : Result.error();
    }

    /**
     * 批量删除角色
     *
     * @param ids IDS
     * @return {@link Result }<{@link ? }>
     */
    @PremName("role:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        boolean res = roleService.removeByIds(ids);
        return res ? Result.success() : Result.error();
    }

    /**
     * 编辑角色
     *
     * @param role 角色
     * @return {@link Result }<{@link ? }>
     */
    @PremName("role:edit")
    @PatchMapping
    public Result<?> edit(@RequestBody Role role) {
        boolean res = roleService.updateById(role);
        return res ? Result.success() : Result.error();
    }

    /**
     * 获取角色
     *
     * @param id 身份证
     * @return {@link Result }<{@link Role }>
     */
    @PremName("role:info")
    @GetMapping("/{id}")
    public Result<Role> get(@PathVariable Integer id) {
        Role data = roleService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取角色列表
     *
     * @return {@link Result }<{@link List }<{@link Role }>>
     */
    @PremName("role:list")
    @GetMapping
    public Result<List<Role>> list() {
        List<Role> data = roleService.list();
        return Result.success(data);
    }

    /**
     * 获取指定角色的路由列表
     *
     * @param id 身份证
     * @return {@link Result }<{@link List }<{@link Route }>>
     */
    @GetMapping("/route/{id}")
    public Result<List<Route>> getRouteList(@PathVariable Integer id) {
        List<Route> list = roleService.getRouteList(id);
        return Result.success(list);
    }

    /**
     * 获取指定角色的权限列表
     *
     * @param id 身份证
     * @return {@link Result }<{@link List }<{@link Permission }>>
     */
    @GetMapping("/permission/{id}")
    public Result<List<Permission>> getPermissionList(@PathVariable Integer id) {
        List<Permission> list = roleService.getPermissionList(id);
        return Result.success(list);
    }

    /**
     * 分配角色权限
     *
     * @param id   身份证
     * @param data 数据
     * @return {@link Result }<{@link ? }>
     */
    @PremName("role:bindingRoute")
    @PatchMapping("/bindingRoute/{id}")
    public Result<?> bindingRoute(@PathVariable Integer id, @RequestBody BindRouteAndPermission data) {
        roleService.binding(id, data);
        return Result.success();
    }

}
