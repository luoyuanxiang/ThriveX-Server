package top.luoyuanxiang.thrivex.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.thrivex.server.entity.PageConfigEntity;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
public interface IPageConfigService extends IService<PageConfigEntity> {

    /**
     * 根据名称获取页面配置
     *
     * @param name 名字
     * @return {@link PageConfigEntity }
     */
    PageConfigEntity getByName(String name);

    /**
     * 根据ID更新页面配置
     *
     * @param id        id
     * @param jsonValue json 值
     * @return boolean
     */
    boolean updateJsonValue(Integer id, Map<String, Object> jsonValue);
}
