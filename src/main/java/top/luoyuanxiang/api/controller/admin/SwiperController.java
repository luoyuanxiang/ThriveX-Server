package top.luoyuanxiang.api.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.NoTokenRequired;
import top.luoyuanxiang.api.annotation.PremName;
import top.luoyuanxiang.api.entity.Swiper;
import top.luoyuanxiang.api.execption.CustomException;
import top.luoyuanxiang.api.service.ISwiperService;
import top.luoyuanxiang.api.utils.Result;

import java.util.List;

/**
 * 轮播图管理
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/admin/swiper")
public class SwiperController {

    @Resource
    private ISwiperService swiperService;

    /**
     * 新增轮播图
     *
     * @param swiper 刷卡
     * @return {@link Result }<{@link ? }>
     */
    @PremName("swiper:add")
    @PostMapping
    public Result<?> add(@RequestBody Swiper swiper) {
        try {
            boolean res = swiperService.save(swiper);
            return res ? Result.success() : Result.error();
        } catch (Exception e) {
            throw new CustomException(400, e.getMessage());
        }
    }

    /**
     * 删除轮播图
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("swiper:del")
    @DeleteMapping("/{id}")
    public Result<?> del(@PathVariable Integer id) {
        Swiper data = swiperService.getById(id);
        if (data == null) return Result.error("该数据不存在");

        boolean res = swiperService.removeById(id);

        return res ? Result.success() : Result.error();
    }

    /**
     * 批量删除轮播图
     *
     * @param ids IDS
     * @return {@link Result }<{@link ? }>
     */
    @PremName("swiper:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        boolean res = swiperService.removeByIds(ids);

        return res ? Result.success() : Result.error();
    }

    /**
     * 编辑轮播图
     *
     * @param swiper 刷卡
     * @return {@link Result }<{@link String }>
     */
    @PremName("swiper:edit")
    @PatchMapping
    public Result<?> edit(@RequestBody Swiper swiper) {
        try {
            boolean res = swiperService.updateById(swiper);

            return res ? Result.success() : Result.error();
        } catch (Exception e) {
            throw new CustomException(400, e.getMessage());
        }
    }

    /**
     * 获取轮播图
     *
     * @param id 身份证
     * @return {@link Result }<{@link Swiper }>
     */
    @GetMapping("/{id}")
    public Result<Swiper> get(@PathVariable Integer id) {
        Swiper data = swiperService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取轮播图列表
     *
     * @return {@link Result }<{@link List }<{@link Swiper }>>
     */
    @NoTokenRequired
    @PostMapping("/list")
    public Result<List<Swiper>> list() {
        List<Swiper> data = swiperService.list();
        return Result.success(data);
    }

    /**
     * 分页查询轮播图列表
     *
     * @param page 页
     * @return {@link Result }<{@link Page }<{@link Swiper }>>
     */
    @PostMapping("/paging")
    public Result<Page<Swiper>> paging(Page<Swiper> page) {
        Page<Swiper> data = swiperService.page(page);
        return Result.success(data);
    }

}
