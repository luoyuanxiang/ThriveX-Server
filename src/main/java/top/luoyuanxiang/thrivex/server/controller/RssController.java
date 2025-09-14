package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.luoyuanxiang.thrivex.server.entity.RssEntity;
import top.luoyuanxiang.thrivex.server.service.IRssService;
import top.luoyuanxiang.thrivex.server.vo.Paging;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;
import java.util.Map;

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
    public Result<Paging<RssEntity>> paging(Integer page, Integer size) {
        Page<RssEntity> data = rssService.paging(page, size);
        return Result.page(data);
    }
}
