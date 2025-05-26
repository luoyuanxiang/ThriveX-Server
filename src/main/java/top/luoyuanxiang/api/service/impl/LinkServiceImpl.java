package top.luoyuanxiang.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.api.entity.Link;
import top.luoyuanxiang.api.execption.CustomException;
import top.luoyuanxiang.api.mapper.LinkMapper;
import top.luoyuanxiang.api.service.ILinkService;
import top.luoyuanxiang.api.service.ILinkTypeService;
import top.luoyuanxiang.api.utils.EmailUtils;
import top.luoyuanxiang.api.utils.ThreadUserUtil;

/**
 * <p>
 * 友链 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements ILinkService {

    @Resource
    private ILinkTypeService linkTypeService;
    @Resource
    private EmailUtils emailUtils;

    @Override
    public void add(Link link, boolean isWeb) {
        // 前端用户手动提交
        if (isWeb) {
            // 添加之前先判断所选的网站类型是否为当前用户可选的
            Integer isAdmin = linkTypeService.getById(link.getTypeId()).getIsAdmin();
            if (isAdmin == 1) throw new CustomException(400, "该类型需要管理员权限才能添加");
            link.insert();

            // 邮件提醒
            emailUtils.send(null, "您有新的友联等待审核", link.toString());

            return;
        }

        // 如果是超级管理员在添加时候不需要审核，直接显示
        if (ThreadUserUtil.isAdmin()) {
            link.setAuditStatus(1);
            link.insert();
        }
    }
}
