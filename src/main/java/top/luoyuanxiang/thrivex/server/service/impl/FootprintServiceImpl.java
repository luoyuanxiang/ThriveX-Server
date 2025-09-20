package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.entity.FootprintEntity;
import top.luoyuanxiang.thrivex.server.mapper.FootprintMapper;
import top.luoyuanxiang.thrivex.server.service.IFootprintService;
import top.luoyuanxiang.thrivex.server.vo.QueryCommonVO;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class FootprintServiceImpl extends ServiceImpl<FootprintMapper, FootprintEntity> implements IFootprintService {

    @Override
    public List<FootprintEntity> list(QueryCommonVO queryCommonVO) {
        return list(queryCommonVO.buildQueryWrapper("address"));
    }
}
