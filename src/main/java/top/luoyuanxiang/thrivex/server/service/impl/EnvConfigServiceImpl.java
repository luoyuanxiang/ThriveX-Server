package top.luoyuanxiang.thrivex.server.service.impl;

import top.luoyuanxiang.thrivex.server.entity.EnvConfig;
import top.luoyuanxiang.thrivex.server.mapper.EnvConfigMapper;
import top.luoyuanxiang.thrivex.server.service.IEnvConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class EnvConfigServiceImpl extends ServiceImpl<EnvConfigMapper, EnvConfig> implements IEnvConfigService {

    @Override
    public EnvConfig getByName(String name) {
        return lambdaQuery()
                .eq(EnvConfig::getName, name)
                .one();
    }

    @Override
    public boolean updateJsonValue(Integer id, Map<String, Object> jsonValue) {
        EnvConfig envConfig = this.getById(id);
        if (envConfig != null) {
            envConfig.setValue(jsonValue);
            return this.updateById(envConfig);
        }
        return false;
    }

    @Override
    public boolean updateJsonFieldValue(Integer id, String fieldName, Object value) {
        EnvConfig envConfig = this.getById(id);
        if (envConfig != null) {
            Map<String, Object> jsonValue = envConfig.getValue();
            if (jsonValue == null) {
                jsonValue = new HashMap<>();
            }
            jsonValue.put(fieldName, value);
            envConfig.setValue(jsonValue);
            return this.updateById(envConfig);
        }
        return false;
    }
}
