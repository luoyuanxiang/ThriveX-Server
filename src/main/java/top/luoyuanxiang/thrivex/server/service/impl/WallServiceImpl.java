package top.luoyuanxiang.thrivex.server.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.luoyuanxiang.thrivex.server.entity.UserEntity;
import top.luoyuanxiang.thrivex.server.entity.WallEntity;
import top.luoyuanxiang.thrivex.server.entity.WebConfigEntity;
import top.luoyuanxiang.thrivex.server.mapper.WallMapper;
import top.luoyuanxiang.thrivex.server.service.IEmailService;
import top.luoyuanxiang.thrivex.server.service.IUserService;
import top.luoyuanxiang.thrivex.server.service.IWallService;
import top.luoyuanxiang.thrivex.server.service.IWebConfigService;
import top.luoyuanxiang.thrivex.server.vo.WallQueryVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private IEmailService emailService;
    @Resource
    private IUserService userService;
    @Resource
    private IWebConfigService webConfigService;

    @Override
    @Transactional
    public void add(WallEntity wall) {
        wall.insert();
        Map<String, Object> variables = new HashMap<>();
        variables.put("commenter", wall.getName());
        variables.put("content", wall.getContent());
        UserEntity user = userService.getById(1);
        WebConfigEntity web = webConfigService.getByName("web");
        variables.put("momentUrl", web.getValue().get("url") + "/wall/all");
        emailService.sendDualFormatEmail(StrUtil.isNotBlank(user.getEmail()) ? user.getEmail() : "admin@luoyuanxiang.top", "", variables);
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
