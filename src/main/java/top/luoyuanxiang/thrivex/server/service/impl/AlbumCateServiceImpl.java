package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import top.luoyuanxiang.thrivex.server.entity.AlbumCate;
import top.luoyuanxiang.thrivex.server.entity.AlbumImage;
import top.luoyuanxiang.thrivex.server.mapper.AlbumCateMapper;
import top.luoyuanxiang.thrivex.server.service.IAlbumCateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.service.IAlbumImageService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class AlbumCateServiceImpl extends ServiceImpl<AlbumCateMapper, AlbumCate> implements IAlbumCateService {

    @Resource
    private IAlbumImageService albumImageService;

    @Override
    public Page<AlbumCate> paging(Integer page, Integer size) {
        return baseMapper.paging(new Page<>(page, size));
    }

    @Override
    public Page<AlbumImage> getImagesByAlbumId(Integer id, Integer page, Integer size) {
        return albumImageService.lambdaQuery()
                .eq(AlbumImage::getCateId, id)
                .page(new Page<>(page, size));
    }
}
