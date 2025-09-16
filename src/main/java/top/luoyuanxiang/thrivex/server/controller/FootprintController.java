package top.luoyuanxiang.thrivex.server.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.entity.FootprintEntity;
import top.luoyuanxiang.thrivex.server.security.HasPermission;
import top.luoyuanxiang.thrivex.server.service.IFootprintService;
import top.luoyuanxiang.thrivex.server.vo.QueryCommonVO;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;

/**
 * 足迹管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/footprint")
public class FootprintController {

    @Resource
    private IFootprintService footprintService;

    /**
     * 新增足迹
     *
     * @param footprintEntity 脚印
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("footprint:add")
    @PostMapping
    public Result<String> add(@RequestBody FootprintEntity footprintEntity) {
        footprintService.save(footprintEntity);
        return Result.success();
    }

    /**
     * 删除足迹
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("footprint:del")
    @DeleteMapping("/{id}")
    public Result<String> del(@PathVariable Integer id) {
        footprintService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除足迹
     *
     * @param ids 身份证
     * @return {@link Result }
     */
    @HasPermission("footprint:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        footprintService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 编辑足迹
     *
     * @param footprintEntity 脚印
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("footprint:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody FootprintEntity footprintEntity) {
        footprintService.updateById(footprintEntity);
        return Result.success();
    }

    /**
     * 获取足迹
     *
     * @param id id
     * @return {@link Result }<{@link FootprintEntity }>
     */
    @GetMapping("/{id}")
    public Result<FootprintEntity> get(@PathVariable Integer id) {
        FootprintEntity data = footprintService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取足迹列表
     *
     * @param queryCommonVO 查询VO
     * @return {@link Result }<{@link List }<{@link FootprintEntity }>>
     */
    @PostMapping("/list")
    public Result<List<FootprintEntity>> list(@RequestBody QueryCommonVO<FootprintEntity> queryCommonVO) {
        List<FootprintEntity> data = footprintService.list(queryCommonVO);
        return Result.success(data);
    }

}
