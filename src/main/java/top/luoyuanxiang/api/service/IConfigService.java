package top.luoyuanxiang.api.service;

import top.luoyuanxiang.api.entity.Config;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 配置 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
public interface IConfigService extends IService<Config> {

    /**
     * 按地图分组
     *
     * @param group 群
     * @return {@link Map }<{@link String }, {@link String }>
     */
    Map<String, Object> groupByMap(String group);

    /**
     * 获取
     *
     * @param url 网址
     * @return {@link String }
     */
    <T> T get(String url, Class<T> tClass);

    /**
     * 编辑
     *
     * @param group  群
     * @param config 配置
     */
    void edit(String group, Map<String, String> config);
}
