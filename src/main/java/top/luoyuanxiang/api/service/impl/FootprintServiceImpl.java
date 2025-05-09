package top.luoyuanxiang.api.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.api.entity.Footprint;
import top.luoyuanxiang.api.mapper.FootprintMapper;
import top.luoyuanxiang.api.service.IFootprintService;
import top.luoyuanxiang.api.vo.FilterVo;

/**
 * <p>
 * 足迹 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Service
public class FootprintServiceImpl extends ServiceImpl<FootprintMapper, Footprint> implements IFootprintService {

    @Override
    public Page<Footprint> page(FilterVo filterVo, Page<Footprint> page) {
        LambdaQueryChainWrapper<Footprint> wrapper = lambdaQuery();
        if (filterVo.getKey() != null) {
            wrapper.like(Footprint::getAddress, filterVo.getKey());
        }
        if (filterVo.getStartDate() != null && filterVo.getEndDate() != null) {
            wrapper.between(Footprint::getCreateTime, filterVo.getStartDate(), filterVo.getEndDate());
        } else if (filterVo.getStartDate() != null) {
            wrapper.ge(Footprint::getCreateTime, filterVo.getStartDate());
        } else if (filterVo.getEndDate() != null) {
            wrapper.le(Footprint::getCreateTime, filterVo.getEndDate());
        }
        return wrapper.page(page);
    }
}
