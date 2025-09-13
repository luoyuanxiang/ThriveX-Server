package top.luoyuanxiang.thrivex.server.service;

import top.luoyuanxiang.thrivex.server.entity.EnvConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
public interface IEnvConfigService extends IService<EnvConfig> {

    /**
     * 按名称获取
     *
     * @param name 名字
     * @return {@link EnvConfig }
     */
    EnvConfig getByName(String name);

    /**
     * 更新 JSON 值
     *
     * @param id        id
     * @param jsonValue json 值
     * @return boolean
     */
    boolean updateJsonValue(Integer id, Map<String, Object> jsonValue);

    /**
     * 更新 JSON 字段值
     *
     * @param id        id
     * @param fieldName 字段名称
     * @param value     价值
     * @return boolean
     */
    boolean updateJsonFieldValue(Integer id, String fieldName, Object value);
}
