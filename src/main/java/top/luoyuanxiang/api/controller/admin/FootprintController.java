package top.luoyuanxiang.api.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.PremName;
import top.luoyuanxiang.api.entity.Footprint;
import top.luoyuanxiang.api.service.IFootprintService;
import top.luoyuanxiang.api.utils.Result;
import top.luoyuanxiang.api.vo.FilterVo;

import java.util.List;

/**
 * 足迹管理
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/admin/footprint")
public class FootprintController {

    @Resource
    private IFootprintService footprintService;

    /**
     * 新增足迹
     *
     * @param footprint 脚印
     * @return {@link Result }<{@link ? }>
     */
    @PremName("footprint:add")
    @PostMapping
    public Result<?> add(@RequestBody Footprint footprint) {
        footprintService.save(footprint);
        return Result.success();
    }

    /**
     * 删除足迹
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("footprint:del")
    @DeleteMapping("/{id}")
    public Result<?> del(@PathVariable Integer id) {
        footprintService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除足迹
     *
     * @param ids IDS
     * @return {@link Result }<{@link ? }>
     */
    @PremName("footprint:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        footprintService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 编辑足迹
     *
     * @param footprint 脚印
     * @return {@link Result }<{@link ? }>
     */
    @PremName("footprint:edit")
    @PatchMapping
    public Result<?> edit(@RequestBody Footprint footprint) {
        footprintService.updateById(footprint);
        return Result.success();
    }

    /**
     * 获取足迹
     *
     * @param id 身份证
     * @return {@link Result }<{@link Footprint }>
     */
    @GetMapping("/{id}")
    public Result<Footprint> get(@PathVariable Integer id) {
        Footprint data = footprintService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取足迹列表
     *
     * @return {@link Result }<{@link List }<{@link Footprint }>>
     */
    @PostMapping("/paging")
    public Result<Page<Footprint>> paging(@RequestBody FilterVo filterVo, Page<Footprint> page) {
        Page<Footprint> data = footprintService.page(filterVo, page);
        return Result.success(data);
    }

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
