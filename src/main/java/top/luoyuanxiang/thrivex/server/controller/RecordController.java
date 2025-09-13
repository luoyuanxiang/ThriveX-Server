//package top.luoyuanxiang.thrivex.server.controller;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import jakarta.annotation.Resource;
//import org.springframework.web.bind.annotation.*;
//import top.luoyuanxiang.thrivex.server.vo.Paging;
//import top.luoyuanxiang.thrivex.server.vo.Result;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 说说管理
// *
// * @author luoyuanxiang
// * @since 2025-09-12
// */
//@RestController
//@RequestMapping("/record")
//public class RecordController {
//
//    @Resource
//    private RecordService recordService;
//
//    @PremName("record:add")
//    @PostMapping
//    @ApiOperation("新增说说")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 1)
//    public Result<String> add(@RequestBody Record record) {
//        recordService.save(record);
//        return Result.success();
//    }
//
//    @PremName("record:del")
//    @DeleteMapping("/{id}")
//    @ApiOperation("删除说说")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 2)
//    public Result<String> del(@PathVariable Integer id) {
//        recordService.removeById(id);
//        return Result.success();
//    }
//
//    @PremName("record:edit")
//    @PatchMapping
//    @ApiOperation("编辑说说")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 4)
//    public Result<String> edit(@RequestBody Record record) {
//        recordService.updateById(record);
//        return Result.success();
//    }
//
//    @GetMapping("/{id}")
//    @ApiOperation("获取说说")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 5)
//    public Result<Record> get(@PathVariable Integer id) {
//        Record data = recordService.getById(id);
//        return Result.success(data);
//    }
//
//    @NoTokenRequired
//    @PostMapping("/list")
//    @ApiOperation("获取说说列表")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 6)
//    public Result<List<Record>> list(@RequestBody FilterVo filterVo) {
//        List<Record> data = recordService.list(filterVo);
//        return Result.success(data);
//    }
//
//    @NoTokenRequired
//    @PostMapping("/paging")
//    @ApiOperation("分页查询说说列表")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 7)
//    public Result paging(@RequestBody FilterVo filterVo, PageVo pageVo) {
//        Page<Record> data = recordService.paging(filterVo, pageVo);
//        Map<String, Object> result = Paging.filter(data);
//        return Result.success(result);
//    }
//
//}
