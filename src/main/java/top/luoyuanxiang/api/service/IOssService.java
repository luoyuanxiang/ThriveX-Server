package top.luoyuanxiang.api.service;

import top.luoyuanxiang.api.entity.Oss;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * oss配置表 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
public interface IOssService extends IService<Oss> {

    /**
     * 保存 OSS
     *
     * @param oss 开源软件
     */
    void saveOss(Oss oss);

    /**
     * del oss
     *
     * @param id 身份证
     */
    void delOss(Integer id);

    /**
     * 更新 OSS
     *
     * @param oss 开源软件
     */
    void updateOss(Oss oss);

    /**
     * 开启
     *
     * @param id 身份证
     */
    void enable(Integer id);

    /**
     * 获取 启用 OSS
     *
     * @return {@link Oss }
     */
    Oss getEnableOss();

    /**
     * 获取平台
     *
     * @return {@link List }<{@link Map }<{@link String }, {@link String }>>
     */
    List<Map<String, String>> getPlatform();
}
