package top.luoyuanxiang.thrivex.server.service;

import top.luoyuanxiang.thrivex.server.entity.FootprintEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.thrivex.server.vo.QueryCommonVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
public interface IFootprintService extends IService<FootprintEntity> {

    /**
     * 列表
     *
     * @param queryCommonVO 查询通用VO
     * @return {@link List }<{@link FootprintEntity }>
     */
    List<FootprintEntity> list(QueryCommonVO queryCommonVO);
}
