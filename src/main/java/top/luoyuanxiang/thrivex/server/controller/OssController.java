//package top.luoyuanxiang.thrivex.server.controller;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.web.bind.annotation.*;
//import top.luoyuanxiang.thrivex.server.entity.Oss;
//import top.luoyuanxiang.thrivex.server.vo.Result;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 存储管理
// *
// * @author luoyuanxiang
// * @since 2025-09-12
// */
//@RestController
//@RequestMapping("/oss")
//public class OssController {
//
//    private final OssService ossService;
//
//    @PremName("oss:add")
//    @PostMapping
//    @ApiOperation("新增oss配置")
//    @ApiOperationSupport(author = "laifeng", order = 1)
//    public Result<String> add(@RequestBody Oss oss) {
//        ossService.saveOss(oss);
//        return Result.success();
//    }
//
//    @PremName("oss:del")
//    @DeleteMapping("/{id}")
//    @ApiOperation("删除oss配置")
//    @ApiOperationSupport(author = "laifeng", order = 2)
//    public Result<String> del(@PathVariable Integer id) {
//        Oss oss = ossService.getById(id);
//        if (oss == null) return Result.error("删除oss配置失败：该配置不存在");
//        if (oss.getIsEnable() == 1) return Result.error("删除oss配置失败：该配置正在使用中");
//        ossService.delOss(id);
//        return Result.success();
//    }
//
//    @PremName("oss:edit")
//    @PatchMapping
//    @ApiOperation("更新oss配置")
//    @ApiOperationSupport(author = "laifeng", order = 3)
//    public Result<String> update(@RequestBody Oss oss) {
//        ossService.updateOss(oss);
//        return Result.success();
//    }
//
//    @PremName("oss:info")
//    @GetMapping("/{id}")
//    @ApiOperation("获取oss配置")
//    @ApiOperationSupport(author = "laifeng", order = 4)
//    public Result<OssVo> get(@PathVariable Integer id) {
//        Oss oss = ossService.getById(id);
//        if (oss == null) {
//            return Result.error("获取oss配置失败：该配置不存在");
//        }
//        OssVo vo = new OssVo();
//        BeanUtils.copyProperties(oss, vo);
//        if ("local".equals(vo.getPlatform())) {
//            vo.setProjectPath(System.getProperty("user.dir"));
//        }
//        return Result.success(vo);
//    }
//
//    @PremName("oss:list")
//    @PostMapping("/list")
//    @ApiOperation("获取oss配置列表")
//    @ApiOperationSupport(author = "laifeng", order = 5)
//    public Result<Object> page() {
//        List<Oss> list = ossService.list();
//        return Result.success(list);
//    }
//
//    @PremName("oss:enable")
//    @PatchMapping("/enable/{id}")
//    @ApiOperation("启用oss配置")
//    @ApiOperationSupport(author = "laifeng", order = 6)
//    public Result enable(@PathVariable Integer id) {
//        ossService.enable(id);
//        return Result.success();
//    }
//
//    @PremName("oss:getEnableOss")
//    @GetMapping("/getEnableOss")
//    @ApiOperation("获取当前启用的oss配置")
//    @ApiOperationSupport(author = "laifeng", order = 8)
//    public Result<Oss> getEnableOss() {
//        return Result.success(ossService.getEnableOss());
//    }
//
//    @PremName("oss:getPlatform")
//    @GetMapping("/platform")
//    @ApiOperation("获取目前支持的oss平台")
//    @ApiOperationSupport(author = "宇阳", order = 9)
//    public Result<List<Map>> getPlatform() {
//        return Result.success(ossService.getPlatform());
//    }
//
//}
