package top.luoyuanxiang.thrivex.server.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.ann.HasPermission;
import top.luoyuanxiang.thrivex.server.entity.OssEntity;
import top.luoyuanxiang.thrivex.server.service.IOssService;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存储管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/oss")
public class OssController {

    @Resource
    private IOssService ossService;

    /**
     * 新增oss配置
     *
     * @param oss OSS
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("oss:add")
    @PostMapping
    public Result<String> add(@RequestBody OssEntity oss) {
        ossService.saveOss(oss);
        return Result.success();
    }

    /**
     * 删除oss配置
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("oss:del")
    @DeleteMapping("/{id}")
    public Result<String> del(@PathVariable Integer id) {
        OssEntity oss = ossService.getById(id);
        if (oss == null) return Result.error("删除oss配置失败：该配置不存在");
        if (oss.getIsEnable() == 1) return Result.error("删除oss配置失败：该配置正在使用中");
        ossService.delOss(id);
        return Result.success();
    }

    /**
     * 更新oss配置
     *
     * @param oss OSS
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("oss:edit")
    @PatchMapping
    public Result<String> update(@RequestBody OssEntity oss) {
        ossService.updateOss(oss);
        return Result.success();
    }

    /**
     * 获取oss配置
     *
     * @param id id
     * @return {@link Result }<{@link OssEntity }>
     */
    @HasPermission("oss:info")
    @GetMapping("/{id}")
    public Result<OssEntity> get(@PathVariable Integer id) {
        OssEntity oss = ossService.getById(id);
        if (oss == null) {
            return Result.error("获取oss配置失败：该配置不存在");
        }
        if ("local".equals(oss.getPlatform())) {
            oss.setProjectPath(System.getProperty("user.dir"));
        }
        return Result.success(oss);
    }

    /**
     * 获取oss配置列表
     *
     * @return {@link Result }<{@link Object }>
     */
    @HasPermission("oss:list")
    @PostMapping("/list")
    public Result<Object> list() {
        List<OssEntity> list = ossService.list();
        return Result.success(list);
    }

    /**
     * 启用oss配置
     *
     * @param id id
     * @return {@link Result }<{@link ? }>
     */
    @HasPermission("oss:enable")
    @PatchMapping("/enable/{id}")
    public Result<?> enable(@PathVariable Integer id) {
        ossService.enable(id);
        return Result.success();
    }

    /**
     * 获取当前启用的oss配置
     *
     * @return {@link Result }<{@link OssEntity }>
     */
    @HasPermission("oss:getEnableOss")
    @GetMapping("/getEnableOss")
    public Result<OssEntity> getEnableOss() {
        return Result.success(ossService.lambdaQuery().eq(OssEntity::getIsEnable, 1).one());
    }

    /**
     * 获取目前支持的oss平台
     *
     * @return {@link Result }<{@link List }<{@link Map }>>
     */
    @HasPermission("oss:getPlatform")
    @GetMapping("/platform")
    public Result<List<Map<String, String>>> getPlatform() {
        List<Map<String, String>> result = new ArrayList<>();
        String[] list = {"huawei", "aliyun", "qiniu", "tencent", "minio"};

        for (String item : list) {
            Map<String, String> data = new HashMap<>();
            data.put("name", platformName(item));
            data.put("value", item);
            result.add(data);
        }
        return Result.success(result);
    }

    public String platformName(String data) {
        return switch (data) {
            case "local" -> "本地存储";
            case "huawei" -> "华为云";
            case "aliyun" -> "阿里云";
            case "qiniu" -> "七牛云";
            case "tencent" -> "腾讯云";
            case "minio" -> "Minio";
            default -> data;
        };

    }

}
