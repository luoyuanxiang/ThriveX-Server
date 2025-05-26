package top.luoyuanxiang.api.controller.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.NoTokenRequired;
import top.luoyuanxiang.api.entity.Swiper;
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
@RequestMapping("/api/web/swiper")
public class WebSwiperController {

    @Resource
    private ISwiperService swiperService;

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
