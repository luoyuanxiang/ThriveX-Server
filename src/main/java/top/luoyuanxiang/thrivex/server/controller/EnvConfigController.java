package top.luoyuanxiang.thrivex.server.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.ann.HasPermission;
import top.luoyuanxiang.thrivex.server.ann.NoAuth;
import top.luoyuanxiang.thrivex.server.entity.EnvConfigEntity;
import top.luoyuanxiang.thrivex.server.service.IEnvConfigService;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;
import java.util.Map;

/**
 * 环境配置管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/env_config")
public class EnvConfigController {

    @Resource
    private IEnvConfigService envConfigService;

    /**
     * 获取环境配置列表
     *
     * @return {@link Result }<{@link List }<{@link EnvConfigEntity }>>
     */
    @HasPermission("config")
    @GetMapping("/list")
    public Result<List<EnvConfigEntity>> list() {
        List<EnvConfigEntity> data = envConfigService.list();
        return Result.success("获取成功", data);
    }

    /**
     * 根据ID获取环境配置
     *
     * @param id id
     * @return {@link Result }<{@link EnvConfigEntity }>
     */
    @HasPermission("config")
    @GetMapping("/{id}")
    public Result<EnvConfigEntity> getById(@PathVariable Integer id) {
        EnvConfigEntity envConfigEntity = envConfigService.getById(id);
        return envConfigEntity != null ? Result.success("获取成功", envConfigEntity) : Result.error("配置不存在");
    }

    /**
     * 根据名称获取环境配置
     *
     * @param name 名字
     * @return {@link Result }<{@link EnvConfigEntity }>
     */
    @HasPermission("config")
    @GetMapping("/name/{name}")
    public Result<EnvConfigEntity> getByName(@PathVariable String name) {
        EnvConfigEntity envConfigEntity = envConfigService.getByName(name);
        return envConfigEntity != null ? Result.success("获取成功", envConfigEntity) : Result.error("配置不存在");
    }

    /**
     * 更新 JSON 值
     *
     * @param id        id
     * @param jsonValue json 值
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("config")
    @PatchMapping("/json/{id}")
    public Result<String> updateJsonValue(@PathVariable Integer id,
                                          @RequestBody Map<String, Object> jsonValue) {
        boolean success = envConfigService.updateJsonValue(id, jsonValue);
        return success ? Result.success("JSON配置更新成功") : Result.error("更新失败");
    }

    /**
     * 根据ID更新配置
     *
     * @param id        id
     * @param fieldName 字段名称
     * @param value     价值
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("config")
    @PatchMapping("/{id}/field/{fieldName}")
    public Result<String> updateJsonFieldValue(@PathVariable Integer id,
                                               @PathVariable String fieldName,
                                               @RequestBody Object value) {
        boolean success = envConfigService.updateJsonFieldValue(id, fieldName, value);
        return success ? Result.success() : Result.error();
    }

    /**
     * 获取高德地图配置
     *
     * @return {@link Result }<{@link Map }<{@link String }, {@link Object }>>
     */
    @NoAuth
    @GetMapping("/gaode_map")
    public Result<Map<String, Object>> getGaodeMapConfig() {
        EnvConfigEntity envConfigEntity = envConfigService.getByName("gaode_map");
        return Result.success(envConfigEntity.getValue());
    }
}
