package top.luoyuanxiang.thrivex.server.service.impl;

import top.luoyuanxiang.thrivex.server.entity.EmailLogsEntity;
import top.luoyuanxiang.thrivex.server.mapper.EmailLogsMapper;
import top.luoyuanxiang.thrivex.server.service.IEmailLogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 邮件日志 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-13
 */
@Service
public class EmailLogsServiceImpl extends ServiceImpl<EmailLogsMapper, EmailLogsEntity> implements IEmailLogsService {

}
