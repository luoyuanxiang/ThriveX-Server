package top.luoyuanxiang.thrivex.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.luoyuanxiang.thrivex.server.entity.AlbumCate;
import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.thrivex.server.entity.AlbumImage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
public interface IAlbumCateService extends IService<AlbumCate> {

    /**
     * 分页
     *
     * @param page 页
     * @param size 大小
     * @return {@link Page }<{@link AlbumCate }>
     */
    Page<AlbumCate> paging(Integer page, Integer size);

    /**
     * 获取指定相册中的所有照片
     *
     * @param id   id
     * @param page 页
     * @param size 大小
     * @return {@link Page }<{@link AlbumImage }>
     */
    Page<AlbumImage> getImagesByAlbumId(Integer id, Integer page, Integer size);
}
