package top.luoyuanxiang.api.service.impl;

import top.luoyuanxiang.api.entity.Tag;
import top.luoyuanxiang.api.mapper.TagMapper;
import top.luoyuanxiang.api.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 标签 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    @Override
    public List<Tag> staticArticleCount() {
        return baseMapper.staticArticleCount();
    }
}
