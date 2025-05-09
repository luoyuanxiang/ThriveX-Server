package top.luoyuanxiang.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.api.entity.AlbumImage;
import top.luoyuanxiang.api.execption.CustomException;
import top.luoyuanxiang.api.mapper.AlbumImageMapper;
import top.luoyuanxiang.api.service.IAlbumImageService;

/**
 * <p>
 * 相册 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Service
public class AlbumImageServiceImpl extends ServiceImpl<AlbumImageMapper, AlbumImage> implements IAlbumImageService {

    @Override
    public void del(Integer id) {
        AlbumImage albumImage = getById(id);
        if (albumImage == null) throw new CustomException(400, "该照片不存在");
        removeById(id);
    }

    @Override
    public void edit(AlbumImage albumImage) {
        AlbumImage existAlbumImage = getById(albumImage.getId());
        if (existAlbumImage == null) throw new CustomException(400, "该照片不存在");
        updateById(albumImage);
    }

    @Override
    public AlbumImage get(Integer id) {
        AlbumImage albumImage = getById(id);
        if (albumImage == null) throw new CustomException(400, "该照片不存在");
        return albumImage;
    }
}
