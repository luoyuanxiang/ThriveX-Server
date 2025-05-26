package top.luoyuanxiang.api.service;

import top.luoyuanxiang.api.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 友链 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
public interface ILinkService extends IService<Link> {

    /**
     * 新增友链
     *
     * @param link 链接
     */
    void add(Link link, boolean isWeb);
}
