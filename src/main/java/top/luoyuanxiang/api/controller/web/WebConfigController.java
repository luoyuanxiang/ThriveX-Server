package top.luoyuanxiang.api.controller.web;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.PremName;
import top.luoyuanxiang.api.service.IConfigService;
import top.luoyuanxiang.api.utils.Result;

import java.util.Map;

/**
 * 配置管理
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/web/config")
public class WebConfigController {

    @Resource
    private IConfigService configService;

    /**
     * 列表
     *
     * @param group 群
     * @return {@link Result }<{@link Map }<{@link String }, {@link String }>>
     */
    @GetMapping("/list/{group}")
    public Result<Map<String, Object>> groupByMap(@PathVariable("group") String group) {
        return Result.success(configService.groupByMap(group));
    }

    /**
     * 修改项目配置
     *
     * @param group  群
     * @param config 配置
     * @return {@link Result }<{@link ? }>
     */
    @PremName("config:edit")
    @PatchMapping("/{group}")
    public Result<?> edit(@PathVariable("group") String group, @RequestBody Map<String, String> config) {
        configService.edit(group, config);
        return Result.success();
    }

}
