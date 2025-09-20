package top.luoyuanxiang.thrivex.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.thrivex.server.entity.OssEntity;

/**
 * <p>
 * oss配置表 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
public interface IOssService extends IService<OssEntity> {

    /**
     * 新增 oss 配置
     *
     * @param oss OSS
     */
    void saveOss(OssEntity oss);

    /**
     * del oss
     *
     * @param id id
     */
    void delOss(Integer id);

    /**
     * 更新oss配置
     *
     * @param oss OSS
     */
    void updateOss(OssEntity oss);

    /**
     * 启用oss配置
     *
     * @param id id
     */
    void enable(Integer id);
}
