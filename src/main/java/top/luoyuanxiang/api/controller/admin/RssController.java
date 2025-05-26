package top.luoyuanxiang.api.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.luoyuanxiang.api.annotation.CheckRole;
import top.luoyuanxiang.api.annotation.NoTokenRequired;
import top.luoyuanxiang.api.entity.Rss;
import top.luoyuanxiang.api.service.IRssService;
import top.luoyuanxiang.api.utils.Result;

import java.util.List;

/**
 * 订阅管理
 *
 * @author luoyuanxiang
 */
@RestController
@RequestMapping("/api/admin/rss")
@CheckRole
public class RssController {
    @Resource
    private IRssService rssService;

    /**
     * 获取订阅内容
     *
     * @return {@link Result }<{@link List }<{@link Rss }>>
     */
    @NoTokenRequired
    @PostMapping("/list")
    public Result<List<Rss>> list() {
        List<Rss> list = rssService.list();
        return Result.success(list);
    }
}
