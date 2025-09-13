package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.entity.AlbumImage;
import top.luoyuanxiang.thrivex.server.security.HasPermission;
import top.luoyuanxiang.thrivex.server.service.IAlbumImageService;
import top.luoyuanxiang.thrivex.server.vo.Paging;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;

/**
 *
 * 照片管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/album/image")
public class AlbumImageController {

    @Resource
    private IAlbumImageService albumImageService;

    /**
     * 新增照片
     *
     * @param albumImage 相册图片添加表单 DTO
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("album_image:add")
    @PostMapping
    public Result<String> add(@RequestBody AlbumImage albumImage) {
        albumImage.insert();
        return Result.success();
    }

    /**
     * 删除照片
     *
     * @param id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("album_image:del")
    @DeleteMapping("/{id}")
    public Result<String> del(@PathVariable Integer id) {
        albumImageService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除照片
     *
     * @param ids
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("album_image:del")
    @DeleteMapping("/batch")
    public Result<String> batchDel(@RequestBody List<Integer> ids) {
        albumImageService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 编辑照片
     *
     * @param albumImage 相册图片
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("album_image:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody AlbumImage albumImage) {
        albumImage.updateById();
        return Result.success();
    }

    /**
     * 获取照片
     *
     * @param id id
     * @return {@link Result }<{@link AlbumImage }>
     */
    @GetMapping("/{id}")
    public Result<AlbumImage> get(@PathVariable Integer id) {
        AlbumImage albumImage = albumImageService.getById(id);
        return Result.success(albumImage);
    }

    /**
     * 获取照片列表
     *
     * @return {@link Result }<{@link List }<{@link AlbumImage }>>
     */
    @PostMapping("/list")
    public Result<List<AlbumImage>> list() {
        List<AlbumImage> albumImages = albumImageService.list();
        return Result.success(albumImages);
    }

    /**
     * 分页查询照片列表
     *
     * @param page 页
     * @param size 大小
     * @return {@link Result }
     */
    @PostMapping("/paging")
    public Result<Paging<AlbumImage>> paging(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer size) {
        Page<AlbumImage> albumImagePage = albumImageService.page(new Page<>(page, size));
        return Result.page(albumImagePage);
    }

}
