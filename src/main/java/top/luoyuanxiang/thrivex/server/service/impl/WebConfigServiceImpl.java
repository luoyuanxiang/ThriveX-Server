package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.entity.WebConfigEntity;
import top.luoyuanxiang.thrivex.server.mapper.WebConfigMapper;
import top.luoyuanxiang.thrivex.server.service.IWebConfigService;

import java.util.Map;

/**
 * <p>
 * 网站配置 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class WebConfigServiceImpl extends ServiceImpl<WebConfigMapper, WebConfigEntity> implements IWebConfigService {

    @Override
    public WebConfigEntity getByName(String name) {
        return lambdaQuery()
                .eq(WebConfigEntity::getName, name)
                .one();
    }

    @Override
    public boolean updateJsonValue(Integer id, Map<String, Object> jsonValue) {
        WebConfigEntity webConfig = this.getById(id);
        if (webConfig != null) {
            webConfig.setValue(jsonValue);
            return this.updateById(webConfig);
        }
        return false;
    }
}
