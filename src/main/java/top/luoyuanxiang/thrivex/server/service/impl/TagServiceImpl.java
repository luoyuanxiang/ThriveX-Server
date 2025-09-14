package top.luoyuanxiang.thrivex.server.service.impl;

import top.luoyuanxiang.thrivex.server.entity.TagEntity;
import top.luoyuanxiang.thrivex.server.mapper.TagMapper;
import top.luoyuanxiang.thrivex.server.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, TagEntity> implements ITagService {

    @Override
    public List<TagEntity> staticArticleCount() {
        return baseMapper.staticArticleCount();
    }
}
