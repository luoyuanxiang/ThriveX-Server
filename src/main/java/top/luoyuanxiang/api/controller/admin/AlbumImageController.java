package top.luoyuanxiang.api.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.NoTokenRequired;
import top.luoyuanxiang.api.annotation.PremName;
import top.luoyuanxiang.api.entity.AlbumImage;
import top.luoyuanxiang.api.service.IAlbumImageService;
import top.luoyuanxiang.api.utils.Result;

import java.util.List;

/**
 * 相册图片管理
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/admin/album/image")
public class AlbumImageController {

    @Resource
    private IAlbumImageService albumImageService;

    /**
     * 新增照片
     *
     * @param albumImage 专辑图片
     * @return {@link Result }<{@link ? }>
     */
    @PremName("album:add")
    @PostMapping
    public Result<?> add(@RequestBody AlbumImage albumImage) {
        albumImage.insert();
        return Result.success();
    }

    /**
     * 删除照片
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("album:del")
    @DeleteMapping("/{id}")
    public Result<?> del(@PathVariable Integer id) {
        albumImageService.del(id);
        return Result.success();
    }

    /**
     * 批量删除照片
     *
     * @param ids IDS
     * @return {@link Result }<{@link ? }>
     */
    @PremName("album:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        albumImageService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 编辑照片
     *
     * @param albumImage 专辑图片
     * @return {@link Result }<{@link ? }>
     */
    @PremName("album:edit")
    @PatchMapping
    public Result<?> edit(@RequestBody AlbumImage albumImage) {
        albumImageService.edit(albumImage);
        return Result.success();
    }

    /**
     * 获取照片
     *
     * @param id 身份证
     * @return {@link Result }<{@link AlbumImage }>
     */
    @GetMapping("/{id}")
    public Result<AlbumImage> get(@PathVariable Integer id) {
        AlbumImage albumImage = albumImageService.get(id);
        return Result.success(albumImage);
    }

    /**
     * 获取照片列表
     *
     * @return {@link Result }<{@link List }<{@link AlbumImage }>>
     */
    @NoTokenRequired
    @PostMapping("/list")
    public Result<List<AlbumImage>> list() {
        List<AlbumImage> albumImages = albumImageService.list();
        return Result.success(albumImages);
    }

    /**
     * 分页查询照片列表
     *
     * @param page 页
     * @return {@link Result }<{@link Page }<{@link AlbumImage }>>
     */
    @NoTokenRequired
    @PostMapping("/paging")
    public Result<Page<AlbumImage>> paging(Page<AlbumImage> page) {
        albumImageService.page(page);
        return Result.success(page);
    }
}
