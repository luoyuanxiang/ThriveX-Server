package top.luoyuanxiang.thrivex.server.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.entity.PermissionEntity;
import top.luoyuanxiang.thrivex.server.ann.HasPermission;
import top.luoyuanxiang.thrivex.server.service.IPermissionService;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;

/**
 * 权限管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    private IPermissionService permissionService;

    /**
     * 新增权限
     *
     * @param permissionEntity 许可
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("permission:add")
    @PostMapping
    public Result<String> add(@RequestBody PermissionEntity permissionEntity) {
        boolean res = permissionService.save(permissionEntity);
        return res ? Result.success() : Result.error();
    }

    /**
     * 删除权限
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("permission:del")
    @DeleteMapping("/{id}")
    public Result<String> del(@PathVariable Integer id) {
        PermissionEntity data = permissionService.getById(id);
        if (data == null) return Result.error("该数据不存在");

        Boolean res = permissionService.removeById(id);
        return res ? Result.success() : Result.error();
    }

    /**
     * 批量删除权限
     *
     * @param ids 身份证
     * @return {@link Result }
     */
    @HasPermission("permission:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        boolean res = permissionService.removeByIds(ids);
        return res ? Result.success() : Result.error();
    }

    /**
     * 编辑权限
     *
     * @param permissionEntity 许可
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("permission:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody PermissionEntity permissionEntity) {
        boolean res = permissionService.updateById(permissionEntity);
        return res ? Result.success() : Result.error();
    }

    /**
     * 获取权限
     *
     * @param id id
     * @return {@link Result }<{@link PermissionEntity }>
     */
    @HasPermission("permission:info")
    @GetMapping("/{id}")
    public Result<PermissionEntity> get(@PathVariable Integer id) {
        PermissionEntity data = permissionService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取权限列表
     *
     * @return {@link Result }<{@link List }<{@link PermissionEntity }>>
     */
    @HasPermission("permission:list")
    @GetMapping
    public Result<List<PermissionEntity>> list() {
        List<PermissionEntity> data = permissionService.list();
        return Result.success(data);
    }

}
