package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.ann.NoAuth;
import top.luoyuanxiang.thrivex.server.entity.RssEntity;
import top.luoyuanxiang.thrivex.server.service.IRssService;
import top.luoyuanxiang.thrivex.server.vo.Paging;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;

/**
 * 订阅管理
 *
 * @author luoyuanxiang
 */
@RestController
@RequestMapping("/rss")
public class RssController {

    @Resource
    private IRssService rssService;

    /**
     * 获取订阅内容
     *
     * @return {@link Result }<{@link List }<{@link RssEntity }>>
     */
    @NoAuth
    @GetMapping("/list")
    public Result<List<RssEntity>> list() {
        List<RssEntity> list = rssService.list();
        return Result.success(list);
    }

    /**
     * 分页查询订阅内容
     *
     * @param page 页
     * @param size 大小
     * @return {@link Result }<{@link Paging }<{@link RssEntity }>>
     */
    @PostMapping("/paging")
    public Result<Paging<RssEntity>> paging(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size) {
        Page<RssEntity> data = rssService.paging(page, size);
        return Result.page(data);
    }
}
