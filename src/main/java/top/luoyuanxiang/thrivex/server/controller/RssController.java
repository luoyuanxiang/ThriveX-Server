//package top.luoyuanxiang.thrivex.server.controller;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import jakarta.annotation.Resource;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import top.luoyuanxiang.thrivex.server.vo.Paging;
//import top.luoyuanxiang.thrivex.server.vo.Result;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 订阅管理
// *
// * @author luoyuanxiang
// */
//@RestController
//@RequestMapping("/rss")
//public class RssController {
//
//    @Resource
//    private RssService rssService;
//
//    @NoTokenRequired
//    @GetMapping("/list")
//    @ApiOperation("获取订阅内容")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 1)
//    public Result<List<Rss>> list() {
//        List<Rss> list = rssService.list();
//        return Result.success(list);
//    }
//
//    @NoTokenRequired
//    @PostMapping("/paging")
//    @ApiOperation("分页查询订阅内容")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 2)
//    public Result paging(PageVo pageVo) {
//        Page<Rss> data = rssService.paging(pageVo);
//        Map<String, Object> result = Paging.filter(data);
//        return Result.success(result);
//    }
//}
