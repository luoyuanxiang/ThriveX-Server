package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.entity.EmailServerConfigEntity;
import top.luoyuanxiang.thrivex.server.mapper.EmailServerConfigMapper;
import top.luoyuanxiang.thrivex.server.service.IEmailServerConfigService;

/**
 * <p>
 * 邮件服务器配置表 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-13
 */
@Slf4j
@Service
public class EmailServerConfigServiceImpl extends ServiceImpl<EmailServerConfigMapper, EmailServerConfigEntity> implements IEmailServerConfigService {
}
