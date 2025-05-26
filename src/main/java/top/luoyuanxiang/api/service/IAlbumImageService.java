package top.luoyuanxiang.api.service;

import top.luoyuanxiang.api.entity.AlbumImage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 相册 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
public interface IAlbumImageService extends IService<AlbumImage> {

    /**
     * 删除照片
     *
     * @param id 身份证
     */
    void del(Integer id);

    /**
     * 编辑
     *
     * @param albumImage 专辑图片
     */
    void edit(AlbumImage albumImage);

    /**
     * 获取
     *
     * @param id 身份证
     * @return {@link AlbumImage }
     */
    AlbumImage get(Integer id);
}
