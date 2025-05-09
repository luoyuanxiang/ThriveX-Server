package top.luoyuanxiang.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.api.entity.AlbumCate;
import top.luoyuanxiang.api.entity.AlbumImage;
import top.luoyuanxiang.api.execption.CustomException;
import top.luoyuanxiang.api.mapper.AlbumCateMapper;
import top.luoyuanxiang.api.mapper.AlbumImageMapper;
import top.luoyuanxiang.api.service.IAlbumCateService;

import java.util.List;

/**
 * <p>
 * 相册信息 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Service
public class AlbumCateServiceImpl extends ServiceImpl<AlbumCateMapper, AlbumCate> implements IAlbumCateService {

    @Resource
    private AlbumImageMapper albumImageMapper;

    @Override
    public void del(Integer id) {
        AlbumCate albumCate = baseMapper.selectById(id);
        if (albumCate == null) throw new CustomException(400, "该相册不存在");
        baseMapper.deleteById(id);
    }

    @Override
    public void batchDel(List<Integer> ids) {
        baseMapper.deleteByIds(ids);
    }

    @Override
    public void edit(AlbumCate albumCate) {
        AlbumCate existAlbumCate = baseMapper.selectById(albumCate.getId());
        if (existAlbumCate == null) throw new CustomException(400, "该相册不存在");
        albumCate.updateById();
    }

    @Override
    public AlbumCate get(Integer id) {
        AlbumCate albumCate = baseMapper.selectById(id);
        if (albumCate == null) throw new CustomException(400, "该相册不存在");
        return albumCate;
    }

    @Override
    public void getImagesByAlbumId(Integer id, Page<AlbumImage> page) {
        LambdaQueryWrapper<AlbumImage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AlbumImage::getCateId, id);
        lambdaQueryWrapper.orderByDesc(AlbumImage::getId);
        albumImageMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    public List<AlbumCate> list() {
        List<AlbumCate> albumCateList = baseMapper.selectList(null);
        albumCateList.forEach(albumCate -> {
            LambdaQueryWrapper<AlbumImage> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(AlbumImage::getCateId, albumCate.getId());
            wrapper.orderByDesc(AlbumImage::getId);
            Long i = albumImageMapper.selectCount(wrapper);
            albumCate.setCount(i);
        });
        return albumCateList;
    }
}
