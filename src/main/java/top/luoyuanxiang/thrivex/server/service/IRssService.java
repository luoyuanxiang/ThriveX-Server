package top.luoyuanxiang.thrivex.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.luoyuanxiang.thrivex.server.entity.RssEntity;

import java.util.List;

/**
 * 订阅管理
 *
 * @author luoyuanxiang
 */
public interface IRssService {
    /**
     * 获取订阅内容
     *
     * @return {@link List }<{@link RssEntity }>
     */
    List<RssEntity> list();

    /**
     * 分页查询订阅内容
     *
     * @param page 页
     * @param size 大小
     * @return {@link Page }<{@link RssEntity }>
     */
    Page<RssEntity> paging(Integer page, Integer size);
}
