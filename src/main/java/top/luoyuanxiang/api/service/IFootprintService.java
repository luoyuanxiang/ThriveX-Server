package top.luoyuanxiang.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.luoyuanxiang.api.entity.Footprint;
import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.api.vo.FilterVo;

/**
 * <p>
 * 足迹 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
public interface IFootprintService extends IService<Footprint> {

    /**
     * 页
     *
     * @param filterVo filter vo
     * @param page     页
     * @return {@link Page }<{@link Footprint }>
     */
    Page<Footprint> page(FilterVo filterVo, Page<Footprint> page);

}
