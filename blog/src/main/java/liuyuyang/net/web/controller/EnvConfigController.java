package liuyuyang.net.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liuyuyang.net.common.annotation.CheckRole;
import liuyuyang.net.common.utils.Result;
import liuyuyang.net.model.EnvConfig;
import liuyuyang.net.web.service.EnvConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Api(tags = "环境配置管理")
@RestController
@RequestMapping("/env-config")
@CheckRole
public class EnvConfigController {

    @Resource
    private EnvConfigService envConfigService;

    @ApiOperation("根据ID获取环境配置")
    @GetMapping("/{id}")
    public Result<EnvConfig> getById(@PathVariable Integer id) {
        EnvConfig envConfig = envConfigService.getById(id);
        return envConfig != null ? Result.success("获取成功", envConfig) : Result.error("配置不存在");
    }

    @ApiOperation("根据名称获取环境配置")
    @GetMapping("/name/{name}")
    public Result<EnvConfig> getByName(@PathVariable String name) {
        EnvConfig envConfig = envConfigService.getByName(name);
        return envConfig != null ? Result.success("获取成功", envConfig) : Result.error("配置不存在");
    }

    @ApiOperation("保存或更新环境配置")
    @PostMapping
    public Result<String> saveOrUpdate(@RequestBody EnvConfig envConfig) {
        boolean success = envConfigService.saveOrUpdate(envConfig);
        return Result.status(success);
    }

    @ApiOperation("更新JSON配置值")
    @PutMapping("/{id}/json")
    public Result<String> updateJsonValue(@PathVariable Integer id, @RequestBody Map<String, Object> jsonValue) {
        boolean success = envConfigService.updateJsonValue(id, jsonValue);
        return success ? Result.success("JSON配置更新成功") : Result.error("更新失败");
    }

    @ApiOperation("获取JSON配置中的特定字段值")
    @GetMapping("/{id}/field/{fieldName}")
    public Result<Object> getJsonFieldValue(@PathVariable Integer id, @PathVariable String fieldName) {
        Object value = envConfigService.getJsonFieldValue(id, fieldName);
        return value != null ? Result.success("获取成功", value) : Result.error("字段不存在或配置为空");
    }

    @ApiOperation("更新JSON配置中的特定字段值")
    @PutMapping("/{id}/field/{fieldName}")
    public Result<String> updateJsonFieldValue(@PathVariable Integer id, 
                                               @PathVariable String fieldName, 
                                               @RequestBody Object value) {
        boolean success = envConfigService.updateJsonFieldValue(id, fieldName, value);
        return success ? Result.success("字段更新成功") : Result.error("更新失败");
    }
} 