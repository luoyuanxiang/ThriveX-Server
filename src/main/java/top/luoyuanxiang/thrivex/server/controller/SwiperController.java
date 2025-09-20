package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.ann.HasPermission;
import top.luoyuanxiang.thrivex.server.ann.NoAuth;
import top.luoyuanxiang.thrivex.server.entity.SwiperEntity;
import top.luoyuanxiang.thrivex.server.service.ISwiperService;
import top.luoyuanxiang.thrivex.server.vo.Paging;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;

/**
 * 轮播图管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/swiper")
public class SwiperController {

    @Resource
    private ISwiperService swiperService;

    /**
     * 新增轮播图
     *
     * @param swiperEntity 刷头
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("swiper:add")
    @PostMapping
    public Result<String> add(@RequestBody SwiperEntity swiperEntity) {
        boolean res = swiperService.save(swiperEntity);
        return res ? Result.success() : Result.error();
    }

    /**
     * 删除轮播图
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("swiper:del")
    @DeleteMapping("/{id}")
    public Result<String> del(@PathVariable Integer id) {
        SwiperEntity data = swiperService.getById(id);
        if (data == null) return Result.error("该数据不存在");

        boolean res = swiperService.removeById(id);

        return res ? Result.success() : Result.error();
    }

    /**
     * 批量删除轮播图
     *
     * @param ids 身份证
     * @return {@link Result }
     */
    @HasPermission("swiper:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        boolean res = swiperService.removeByIds(ids);

        return res ? Result.success() : Result.error();
    }

    /**
     * 编辑轮播图
     *
     * @param swiperEntity 刷头
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("swiper:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody SwiperEntity swiperEntity) {
        boolean res = swiperService.updateById(swiperEntity);

        return res ? Result.success() : Result.error();
    }

    /**
     * 获取轮播图
     *
     * @param id id
     * @return {@link Result }<{@link SwiperEntity }>
     */
    @GetMapping("/{id}")
    public Result<SwiperEntity> get(@PathVariable Integer id) {
        SwiperEntity data = swiperService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取轮播图列表
     *
     * @return {@link Result }<{@link List }<{@link SwiperEntity }>>
     */
    @NoAuth
    @PostMapping("/list")
    public Result<List<SwiperEntity>> list() {
        List<SwiperEntity> data = swiperService.list();
        return Result.success(data);
    }

    /**
     * 分页查询轮播图列表
     *
     * @param page 页
     * @param size 大小
     * @return {@link Result }
     */
    @PostMapping("/paging")
    public Result<Paging<SwiperEntity>> paging(@RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "5") Integer size) {
        Page<SwiperEntity> data = swiperService.page(new Page<>(page, size));
        return Result.page(data);
    }

}
