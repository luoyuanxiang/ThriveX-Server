package top.luoyuanxiang.api.controller.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.entity.AlbumCate;
import top.luoyuanxiang.api.entity.AlbumImage;
import top.luoyuanxiang.api.service.IAlbumCateService;
import top.luoyuanxiang.api.utils.Result;

/**
 * 相册管理
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/web/album/cate")
public class WebAlbumCateController {

    @Resource
    private IAlbumCateService albumCateService;

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
