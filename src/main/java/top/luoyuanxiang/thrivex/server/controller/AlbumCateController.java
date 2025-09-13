package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.entity.AlbumCate;
import top.luoyuanxiang.thrivex.server.entity.AlbumImage;
import top.luoyuanxiang.thrivex.server.security.HasPermission;
import top.luoyuanxiang.thrivex.server.service.IAlbumCateService;
import top.luoyuanxiang.thrivex.server.vo.Paging;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;

/**
 * 相册管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/album/cate")
public class AlbumCateController {

    @Resource
    private IAlbumCateService albumCateService;

    /**
     *
     * 新增相册
     *
     * @param albumCate 相册
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("album_cate:add")
    @PostMapping
    public Result<String> add(@RequestBody AlbumCate albumCate) {
        albumCate.insert();
        return Result.success();
    }

    /**
     * 删除相册
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("album_cate:del")
    @DeleteMapping("/{id}")
    public Result<String> del(@PathVariable Integer id) {
        albumCateService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除相册
     *
     * @param ids 身份证
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("album_cate:del")
    @DeleteMapping("/batch")
    public Result<String> batchDel(@RequestBody List<Integer> ids) {
        albumCateService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 编辑相册
     *
     * @param albumCate 专辑凯特
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("album_cate:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody AlbumCate albumCate) {
        albumCateService.updateById(albumCate);
        return Result.success();
    }

    /**
     * 获取相册
     *
     * @param id id
     * @return {@link Result }<{@link AlbumCate }>
     */
    @PermitAll
    @GetMapping("/{id}")
    public Result<AlbumCate> get(@PathVariable Integer id) {
        AlbumCate albumCate = albumCateService.getById(id);
        if (albumCate == null) return Result.error("该相册不存在");
        return Result.success(albumCate);
    }

    /**
     * 获取相册列表
     *
     * @return {@link Result }<{@link List }<{@link AlbumCate }>>
     */
    @HasPermission("album_cate:edit")
    @PostMapping("/list")
    public Result<List<AlbumCate>> list() {
        List<AlbumCate> albumCates = albumCateService.list();
        return Result.success(albumCates);
    }

    /**
     * 分页查询相册列表
     *
     * @param page 页
     * @param size 大小
     * @return {@link Result }
     */
    @PostMapping("/paging")
    public Result<Paging<AlbumCate>> paging(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size) {
        Page<AlbumCate> result = albumCateService.paging(page, size);
        return Result.page(result);
    }

    /**
     * 获取指定相册中的所有照片
     *
     * @param id   id
     * @param page 页
     * @param size 大小
     * @return {@link Result }
     */
    @GetMapping("/{id}/images")
    public Result<Paging<AlbumImage>> getImagesByAlbumId(@PathVariable Integer id,
                                                         @RequestParam(defaultValue = "1") Integer page,
                                                         @RequestParam(defaultValue = "10") Integer size) {
        Page<AlbumImage> data = albumCateService.getImagesByAlbumId(id, page, size);
        return Result.page(data);
    }
}
