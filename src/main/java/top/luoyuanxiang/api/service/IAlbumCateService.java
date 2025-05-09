package top.luoyuanxiang.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.luoyuanxiang.api.entity.AlbumCate;
import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.api.entity.AlbumImage;

import java.util.List;

/**
 * <p>
 * 相册信息 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
public interface IAlbumCateService extends IService<AlbumCate> {

    /**
     * 删除
     *
     * @param id 身份证
     */
    void del(Integer id);

    /**
     * 批量删除
     *
     * @param ids IDS
     */
    void batchDel(List<Integer> ids);

    /**
     * 编辑
     *
     * @param albumCate 专辑 Cate
     */
    void edit(AlbumCate albumCate);

    /**
     * 获取
     *
     * @param id 身份证
     * @return {@link AlbumCate }
     */
    AlbumCate get(Integer id);

    /**
     * 按影集 ID 获取图像
     *
     * @param id 身份证
     */
    void getImagesByAlbumId(Integer id, Page<AlbumImage> page);
}
