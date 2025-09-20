package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.entity.PageConfigEntity;
import top.luoyuanxiang.thrivex.server.mapper.PageConfigMapper;
import top.luoyuanxiang.thrivex.server.service.IPageConfigService;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class PageConfigServiceImpl extends ServiceImpl<PageConfigMapper, PageConfigEntity> implements IPageConfigService {

    @Override
    public PageConfigEntity getByName(String name) {
        return lambdaQuery()
                .eq(PageConfigEntity::getName, name)
                .one();
    }

    @Override
    public boolean updateJsonValue(Integer id, Map<String, Object> jsonValue) {
        PageConfigEntity pageConfigEntity = getById(id);
        pageConfigEntity.setValue(jsonValue);
        pageConfigEntity.updateById();
        return pageConfigEntity.updateById();
    }
}
