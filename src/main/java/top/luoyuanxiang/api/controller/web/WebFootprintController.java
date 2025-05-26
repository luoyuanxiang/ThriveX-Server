package top.luoyuanxiang.api.controller.web;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.luoyuanxiang.api.entity.Footprint;
import top.luoyuanxiang.api.service.IFootprintService;
import top.luoyuanxiang.api.utils.Result;

import java.util.List;

/**
 * 足迹管理
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/web/footprint")
public class WebFootprintController {

    @Resource
    private IFootprintService footprintService;

    /**
     * 获取足迹列表
     *
     * @return {@link Result }<{@link List }<{@link Footprint }>>
     */
    @PostMapping("/list")
    public Result<List<Footprint>> list() {
        List<Footprint> data = footprintService.list();
        return Result.success(data);
    }

}
