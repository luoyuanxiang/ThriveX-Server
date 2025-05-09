package top.luoyuanxiang.api.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.PremName;
import top.luoyuanxiang.api.entity.Oss;
import top.luoyuanxiang.api.service.IOssService;
import top.luoyuanxiang.api.utils.Result;
import top.luoyuanxiang.api.vo.OssVO;

import java.util.List;
import java.util.Map;

/**
 * oss配置表
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/admin/oss")
public class OssController {

    @Resource
    private IOssService ossService;

    /**
     * 新增oss配置
     *
     * @param oss 开源软件
     * @return {@link Result }<{@link ? }>
     */
    @PremName("oss:add")
    @PostMapping
    public Result<?> add(@RequestBody Oss oss) {
        ossService.saveOss(oss);
        return Result.success();
    }

    /**
     * 删除oss配置
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("oss:del")
    @DeleteMapping("/{id}")
    public Result<?> del(@PathVariable Integer id) {
        Oss oss = ossService.getById(id);
        if (oss == null) return Result.error("删除oss配置失败：该配置不存在");
        if (oss.getIsEnable() == 1) return Result.error("删除oss配置失败：该配置正在使用中");
        ossService.delOss(id);
        return Result.success();
    }

    /**
     * 更新oss配置
     *
     * @param oss 开源软件
     * @return {@link Result }<{@link ? }>
     */
    @PremName("oss:edit")
    @PatchMapping
    public Result<?> update(@RequestBody Oss oss) {
        ossService.updateOss(oss);
        return Result.success();
    }

    /**
     * 获取oss配置
     *
     * @param id 身份证
     * @return {@link Result }<{@link OssVO }>
     */
    @PremName("oss:info")
    @GetMapping("/{id}")
    public Result<OssVO> get(@PathVariable Integer id) {
        Oss oss = ossService.getById(id);
        if (oss == null) {
            return Result.error("获取oss配置失败：该配置不存在");
        }
        OssVO vo = new OssVO();
        BeanUtils.copyProperties(oss, vo);
        return Result.success(vo);
    }

    /**
     * 获取oss配置列表
     *
     * @return {@link Result }<{@link Object }>
     */
    @PremName("oss:list")
    @PostMapping("/list")
    public Result<List<Oss>> page() {
        List<Oss> list = ossService.list();
        return Result.success(list);
    }

    /**
     * 启用oss配置
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("oss:enable")
    @PatchMapping("/enable/{id}")
    public Result<?> enable(@PathVariable Integer id) {
        ossService.enable(id);
        return Result.success();
    }

    /**
     * 获取当前启用的oss配置
     *
     * @return {@link Result }<{@link Oss }>
     */
    @PremName("oss:getEnableOss")
    @GetMapping("/getEnableOss")
    public Result<Oss> getEnableOss() {
        return Result.success(ossService.getEnableOss());
    }

    /**
     * 获取目前支持的oss平台
     *
     * @return {@link Result }<{@link List }<{@link Map }<{@link String }, {@link String }>>>
     */
    @PremName("oss:getPlatform")
    @GetMapping("/platform")
    public Result<List<Map<String, String>>> getPlatform() {
        return Result.success(ossService.getPlatform());
    }

}
