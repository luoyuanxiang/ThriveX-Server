package top.luoyuanxiang.api.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.PremName;
import top.luoyuanxiang.api.entity.AlbumCate;
import top.luoyuanxiang.api.entity.AlbumImage;
import top.luoyuanxiang.api.service.IAlbumCateService;
import top.luoyuanxiang.api.utils.Result;

import java.util.List;

/**
 * 相册管理
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/admin/album/cate")
public class AlbumCateController {

    @Resource
    private IAlbumCateService albumCateService;

    /**
     * 新增相册
     *
     * @param albumCate 专辑 Cate
     * @return {@link Result }<{@link String }>
     */
    @PremName("album:add")
    @PostMapping
    public Result<?> add(@RequestBody AlbumCate albumCate) {
        albumCate.insert();
        return Result.success();
    }

    /**
     * 删除相册
     *
     * @param id 身份证
     * @return {@link Result }<{@link String }>
     */
    @PremName("album:del")
    @DeleteMapping("/{id}")
    public Result<?> del(@PathVariable Integer id) {
        albumCateService.del(id);
        return Result.success();
    }

    /**
     * 批量删除相册
     *
     * @param ids IDS
     * @return {@link Result }<{@link String }>
     */
    @PremName("album:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        albumCateService.batchDel(ids);
        return Result.success();
    }

    /**
     * 编辑相册
     *
     * @param albumCate 专辑 Cate
     * @return {@link Result }<{@link String }>
     */
    @PremName("album:edit")
    @PatchMapping
    public Result<?> edit(@RequestBody AlbumCate albumCate) {
        albumCateService.edit(albumCate);
        return Result.success();
    }

    /**
     * 获取相册
     *
     * @param id 身份证
     * @return {@link Result }<{@link AlbumCate }>
     */
    @GetMapping("/{id}")
    public Result<AlbumCate> get(@PathVariable Integer id) {
        AlbumCate albumCate = albumCateService.get(id);
        return Result.success(albumCate);
    }

    /**
     * 获取相册列表
     *
     * @return {@link Result }<{@link List }<{@link AlbumCate }>>
     */
    @PostMapping("/list")
    public Result<List<AlbumCate>> list() {
        List<AlbumCate> albumCates = albumCateService.list();
        return Result.success(albumCates);
    }

    /**
     * 分页查询相册列表
     *
     * @param page 页
     * @return {@link Result }
     */
    @PostMapping("/paging")
    public Result<Page<AlbumCate>> paging(Page<AlbumCate> page) {
        albumCateService.page(page);
        return Result.success(page);
    }

    /**
     * 获取指定相册中的所有照片
     *
     * @param id   身份证
     * @param page 页
     * @return {@link Result }
     */
    @GetMapping("/{id}/images")
    public Result<Page<AlbumImage>> getImagesByAlbumId(@PathVariable Integer id,
                                                       Page<AlbumImage> page) {
        albumCateService.getImagesByAlbumId(id, page);
        return Result.success(page);
    }

}
