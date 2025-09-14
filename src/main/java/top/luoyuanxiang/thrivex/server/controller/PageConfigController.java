package top.luoyuanxiang.thrivex.server.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.entity.PageConfigEntity;
import top.luoyuanxiang.thrivex.server.service.IPageConfigService;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;
import java.util.Map;

/**
 *页面配置管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/page-config")
public class PageConfigController {

    @Resource
    private IPageConfigService pageConfigService;

    /**
     * 获取页面配置列表
     *
     * @return {@link Result }<{@link List }<{@link PageConfigEntity }>>
     */
    @GetMapping("/list")
    public Result<List<PageConfigEntity>> list() {
        List<PageConfigEntity> data = pageConfigService.list();
        return Result.success("获取成功", data);
    }

    /**
     * 根据名称获取页面配置
     *
     * @param name 名字
     * @return {@link Result }<{@link PageConfigEntity }>
     */
    @GetMapping("/name/{name}")
    public Result<PageConfigEntity> getByName(@PathVariable String name) {
        PageConfigEntity pageConfigEntity = pageConfigService.getByName(name);
        return pageConfigEntity != null ? Result.success("获取成功", pageConfigEntity) : Result.error("配置不存在");
    }

    /**
     * 根据ID获取页面配置
     *
     * @param id id
     * @return {@link Result }<{@link PageConfigEntity }>
     */
    @GetMapping("/{id}")
    public Result<PageConfigEntity> getById(@PathVariable Integer id) {
        PageConfigEntity pageConfigEntity = pageConfigService.getById(id);
        return pageConfigEntity != null ? Result.success("获取成功", pageConfigEntity) : Result.error("配置不存在");
    }

    /**
     * 根据ID更新页面配置
     *
     * @param id        id
     * @param jsonValue json 值
     * @return {@link Result }<{@link String }>
     */
    @PatchMapping("/json/{id}")
    public Result<String> updateJsonValue(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> jsonValue) {
        boolean success = pageConfigService.updateJsonValue(id, jsonValue);
        return success ? Result.success() : Result.error();
    }

}
