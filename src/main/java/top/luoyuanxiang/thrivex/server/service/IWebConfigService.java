package top.luoyuanxiang.thrivex.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.thrivex.server.entity.WebConfigEntity;

import java.util.Map;

/**
 * <p>
 * 网站配置 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
public interface IWebConfigService extends IService<WebConfigEntity> {

    /**
     * 根据名称获取网站配置
     *
     * @param name 名字
     * @return {@link WebConfigEntity }
     */
    WebConfigEntity getByName(String name);

    /**
     * 根据ID更新网站配置
     *
     * @param id        id
     * @param jsonValue json 值
     * @return boolean
     */
    boolean updateJsonValue(Integer id, Map<String, Object> jsonValue);
}
