package top.luoyuanxiang.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.api.entity.Wall;
import top.luoyuanxiang.api.entity.WallCate;
import top.luoyuanxiang.api.execption.CustomException;
import top.luoyuanxiang.api.mapper.WallCateMapper;
import top.luoyuanxiang.api.mapper.WallMapper;
import top.luoyuanxiang.api.service.IWallService;
import top.luoyuanxiang.api.utils.EmailUtils;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 留言墙 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Service
public class WallServiceImpl extends ServiceImpl<WallMapper, Wall> implements IWallService {
    @Resource
    private EmailUtils emailUtils;
    @Resource
    private WallCateMapper wallCateMapper;

    @Override
    public void add(Wall wall) {
        wall.insert();
        emailUtils.send(null, "您有新的留言等待审核", "");
    }

    @Override
    public Wall get(Integer id) {
        Wall data = baseMapper.selectById(id);
        if (data == null) throw new CustomException(400, "该留言不存在");
        data.setCate(wallCateMapper.selectById(data.getCateId()));
        return data;
    }

    @Override
    public Page<Wall> getCateWallList(Integer cateId, Page<Wall> page) {
        WallCate wallCate = wallCateMapper.selectById(cateId);

        QueryWrapper<Wall> queryWrapper = new QueryWrapper<>();
        if (!Objects.equals(wallCate.getMark(), "all")) {
            if (Objects.equals(wallCate.getMark(), "choice")) {
                queryWrapper.eq("is_choice", 1);
            } else {
                queryWrapper.eq("cate_id", cateId);
            }
        }

        queryWrapper.eq("audit_status", 1);
        queryWrapper.orderByDesc("create_time");

        baseMapper.selectPage(page, queryWrapper);

        List<Wall> list = page.getRecords();

        // 绑定数据
        for (Wall wall : list) {
            wall.setCate(wallCateMapper.selectById(wall.getCateId()));
        }

        // 分页处理
        return page;
    }

    @Override
    public List<WallCate> getCateList() {
        QueryWrapper<WallCate> queryWrapper = new QueryWrapper<>();
        return wallCateMapper.selectList(queryWrapper);
    }

    @Override
    public void updateChoice(Integer id) {
        Wall wall = baseMapper.selectById(id);
        if (wall == null) throw new CustomException("没有这条留言");

        // 如果是精选则取消，否则设置
        if (wall.getIsChoice() == 0) {
            wall.setIsChoice(1);
        } else {
            wall.setIsChoice(0);
        }

        baseMapper.updateById(wall);
    }
}
