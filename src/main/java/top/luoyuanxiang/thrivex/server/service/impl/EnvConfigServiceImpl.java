package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.entity.EnvConfigEntity;
import top.luoyuanxiang.thrivex.server.mapper.EnvConfigMapper;
import top.luoyuanxiang.thrivex.server.service.IEnvConfigService;

import java.util.HashMap;
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
public class EnvConfigServiceImpl extends ServiceImpl<EnvConfigMapper, EnvConfigEntity> implements IEnvConfigService {

    @Override
    public EnvConfigEntity getByName(String name) {
        return lambdaQuery()
                .eq(EnvConfigEntity::getName, name)
                .one();
    }

    @Override
    public boolean updateJsonValue(Integer id, Map<String, Object> jsonValue) {
        EnvConfigEntity envConfigEntity = this.getById(id);
        if (envConfigEntity != null) {
            envConfigEntity.setValue(jsonValue);
            return this.updateById(envConfigEntity);
        }
        return false;
    }

    @Override
    public boolean updateJsonFieldValue(Integer id, String fieldName, Object value) {
        EnvConfigEntity envConfigEntity = this.getById(id);
        if (envConfigEntity != null) {
            Map<String, Object> jsonValue = envConfigEntity.getValue();
            if (jsonValue == null) {
                jsonValue = new HashMap<>();
            }
            jsonValue.put(fieldName, value);
            envConfigEntity.setValue(jsonValue);
            return this.updateById(envConfigEntity);
        }
        return false;
    }
}
