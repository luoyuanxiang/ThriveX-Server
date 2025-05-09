package top.luoyuanxiang.api.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.PremName;
import top.luoyuanxiang.api.entity.Permission;
import top.luoyuanxiang.api.service.IPermissionService;
import top.luoyuanxiang.api.utils.Result;

import java.util.List;

/**
 * 角色权限
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/admin/permission")
public class PermissionController {
    @Resource
    private IPermissionService permissionService;

    /**
     * 新增权限
     *
     * @param permission 许可
     * @return {@link Result }<{@link ? }>
     */
    @PremName("permission:add")
    @PostMapping
    public Result<?> add(@RequestBody Permission permission) {
        boolean res = permissionService.save(permission);
        return res ? Result.success() : Result.error();
    }

    /**
     * 删除权限
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("permission:del")
    @DeleteMapping("/{id}")
    public Result<?> del(@PathVariable Integer id) {
        Permission data = permissionService.getById(id);
        if (data == null) return Result.error("该数据不存在");

        boolean res = permissionService.removeById(id);
        return res ? Result.success() : Result.error();
    }

    /**
     * 批量删除权限
     *
     * @param ids IDS
     * @return {@link Result }<{@link ? }>
     */
    @PremName("permission:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        boolean res = permissionService.removeByIds(ids);
        return res ? Result.success() : Result.error();
    }

    /**
     * 编辑权限
     *
     * @param permission 许可
     * @return {@link Result }<{@link ? }>
     */
    @PremName("permission:edit")
    @PatchMapping
    public Result<?> edit(@RequestBody Permission permission) {
        boolean res = permissionService.updateById(permission);
        return res ? Result.success() : Result.error();
    }

    /**
     * 获取权限
     *
     * @param id 身份证
     * @return {@link Result }<{@link Permission }>
     */
    @PremName("permission:info")
    @GetMapping("/{id}")
    public Result<Permission> get(@PathVariable Integer id) {
        Permission data = permissionService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取权限列表
     *
     * @return {@link Result }<{@link List }<{@link Permission }>>
     */
    @PremName("permission:list")
    @GetMapping
    public Result<List<Permission>> list() {
        List<Permission> data = permissionService.list();
        return Result.success(data);
    }
}
