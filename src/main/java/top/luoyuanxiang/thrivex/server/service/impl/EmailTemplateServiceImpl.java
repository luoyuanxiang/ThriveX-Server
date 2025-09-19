package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.entity.EmailTemplateEntity;
import top.luoyuanxiang.thrivex.server.mapper.EmailTemplateMapper;
import top.luoyuanxiang.thrivex.server.service.IEmailTemplateService;

/**
 * <p>
 * 邮件模板表 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-13
 */
@Service
public class EmailTemplateServiceImpl extends ServiceImpl<EmailTemplateMapper, EmailTemplateEntity> implements IEmailTemplateService {

}
