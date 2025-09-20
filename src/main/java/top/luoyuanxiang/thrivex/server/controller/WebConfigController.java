package top.luoyuanxiang.thrivex.server.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.ann.NoAuth;
import top.luoyuanxiang.thrivex.server.entity.WebConfigEntity;
import top.luoyuanxiang.thrivex.server.service.IWebConfigService;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;
import java.util.Map;

/**
 * 网站配置管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/web_config")
public class WebConfigController {

    @Resource
    private IWebConfigService webConfigService;

    /**
     * 获取网站配置列表
     *
     * @return {@link Result }<{@link List }<{@link WebConfigEntity }>>
     */
    @GetMapping("/list")
    public Result<List<WebConfigEntity>> list() {
        List<WebConfigEntity> data = webConfigService.list();
        return Result.success("获取成功", data);
    }

    /**
     * 根据名称获取网站配置
     *
     * @param name 名字
     * @return {@link Result }<{@link WebConfigEntity }>
     */
    @NoAuth
    @GetMapping("/name/{name}")
    public Result<WebConfigEntity> getByName(@PathVariable String name) {
        WebConfigEntity webConfig = webConfigService.getByName(name);
        return webConfig != null ? Result.success("获取成功", webConfig) : Result.error("配置不存在");
    }

    /**
     * 根据ID获取网站配置
     *
     * @param id id
     * @return {@link Result }<{@link WebConfigEntity }>
     */
    @GetMapping("/{id}")
    public Result<WebConfigEntity> getById(@PathVariable Integer id) {
        WebConfigEntity webConfig = webConfigService.getById(id);
        return webConfig != null ? Result.success("获取成功", webConfig) : Result.error("配置不存在");
    }

    /**
     * 根据ID更新网站配置
     *
     * @param id        id
     * @param jsonValue json 值
     * @return {@link Result }<{@link String }>
     */
    @PatchMapping("/json/{id}")
    public Result<String> updateJsonValue(@PathVariable Integer id, @RequestBody Map<String, Object> jsonValue) {
        boolean success = webConfigService.updateJsonValue(id, jsonValue);
        return success ? Result.success() : Result.error();
    }

    /**
     * 根据名称更新网站配置
     *
     * @param name      名字
     * @param jsonValue json 值
     * @return {@link Result }<{@link String }>
     */
    @PatchMapping("/json/name/{name}")
    public Result<String> updateJsonValueByName(@PathVariable String name, @RequestBody Map<String, Object> jsonValue) {
        WebConfigEntity webConfig = webConfigService.getByName(name);
        if (webConfig == null) {
            return Result.error("配置不存在");
        }
        boolean success = webConfigService.updateJsonValue(webConfig.getId(), jsonValue);
        return success ? Result.success() : Result.error();
    }

}
