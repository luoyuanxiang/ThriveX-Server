package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.luoyuanxiang.thrivex.server.entity.WallEntity;
import top.luoyuanxiang.thrivex.server.mapper.WallMapper;
import top.luoyuanxiang.thrivex.server.service.IWallService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.vo.WallQueryVO;

import java.util.List;

/**
 * <p>
 * 留言墙 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class WallServiceImpl extends ServiceImpl<WallMapper, WallEntity> implements IWallService {

    @Override
    public void add(WallEntity wall) {
        wall.insert();
        // todo 发送邮件给管理员
    }

    @Override
    public List<WallEntity> list(WallQueryVO filterVo) {
        return baseMapper.list(filterVo);
    }

    @Override
    public Page<WallEntity> paging(Page<WallEntity> page, WallQueryVO filterVo) {
        return baseMapper.list(page, filterVo);
    }

    @Override
    public void updateChoice(Integer id) {
        WallEntity wall = getById(id);
        if (wall == null) throw new RuntimeException("没有这条留言");

        // 如果是精选则取消，否则设置
        if (wall.getIsChoice() == 0) {
            wall.setIsChoice(1);
        } else {
            wall.setIsChoice(0);
        }

        wall.updateById();
    }
}
