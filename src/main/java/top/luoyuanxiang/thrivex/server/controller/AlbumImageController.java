//package top.luoyuanxiang.thrivex.server.controller;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import jakarta.annotation.Resource;
//import org.springframework.web.bind.annotation.*;
//import top.luoyuanxiang.thrivex.server.entity.AlbumImage;
//import top.luoyuanxiang.thrivex.server.security.HasPermission;
//import top.luoyuanxiang.thrivex.server.service.IAlbumImageService;
//import top.luoyuanxiang.thrivex.server.vo.Paging;
//import top.luoyuanxiang.thrivex.server.vo.Result;
//
//import java.util.List;
//import java.util.Map;
//
///**
// *
// * 照片管理
// *
// * @author luoyuanxiang
// * @since 2025-09-12
// */
//@RestController
//@RequestMapping("/album/image")
//public class AlbumImageController {
//
//    @Resource
//    private IAlbumImageService albumImageService;
//
//    @HasPermission("album_image:add")
//    @PostMapping
//    @ApiOperation("新增照片")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 1)
//    public Result<String> add(@RequestBody AlbumImageAddFormDTO albumImageAddFormDTO) {
//        albumImageService.add(albumImageAddFormDTO);
//        return Result.success();
//    }
//
//    @HasPermission("album_image:del")
//    @DeleteMapping("/{id}")
//    @ApiOperation("删除照片")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 2)
//    public Result<String> del(@PathVariable Integer id) {
//        albumImageService.del(id);
//        return Result.success();
//    }
//
//    @HasPermission("album_image:del")
//    @DeleteMapping("/batch")
//    @ApiOperation("批量删除照片")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 3)
//    public Result<String> batchDel(@RequestBody List<Integer> ids) {
//        albumImageService.batchDel(ids);
//        return Result.success();
//    }
//
//    @HasPermission("album_image:edit")
//    @PatchMapping
//    @ApiOperation("编辑照片")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 4)
//    public Result<String> edit(@RequestBody AlbumImage albumImage) {
//        albumImageService.edit(albumImage);
//        return Result.success();
//    }
//
//    @GetMapping("/{id}")
//    @ApiOperation("获取照片")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 5)
//    public Result<AlbumImage> get(@PathVariable Integer id) {
//        AlbumImage albumImage = albumImageService.get(id);
//        return Result.success(albumImage);
//    }
//
//    @NoTokenRequired
//    @PostMapping("/list")
//    @ApiOperation("获取照片列表")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 6)
//    public Result<List<AlbumImage>> list() {
//        List<AlbumImage> albumImages = albumImageService.list();
//        return Result.success(albumImages);
//    }
//
//    @NoTokenRequired
//    @PostMapping("/paging")
//    @ApiOperation("分页查询照片列表")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 7)
//    public Result paging(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
//        Page<AlbumImage> data = albumImageService.paging(page, size);
//        Map<String, Object> result = Paging.filter(data);
//        return Result.success(result);
//    }
//
//}
