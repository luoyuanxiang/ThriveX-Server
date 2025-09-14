package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.entity.EmailServerConfigEntity;
import top.luoyuanxiang.thrivex.server.mapper.EmailServerConfigMapper;
import top.luoyuanxiang.thrivex.server.service.IEmailServerConfigService;

import java.util.Properties;

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

    @Override
    public boolean testConfig(Integer id) {
        EmailServerConfigEntity config = getById(id);
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", config.getHost());
            props.put("mail.smtp.port", config.getPort());
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", config.getTlsEnable());
            props.put("mail.smtp.ssl.enable", config.getSslEnable());
            props.put("mail.smtp.timeout", "10000");

            if (config.getExt() != null) {
                props.putAll(config.getExt());
            }

            // 创建会话并验证连接
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(config.getUsername(), config.getPassword());
                }
            });
            // 验证连接
            session.getTransport("smtp").connect(config.getHost(), config.getPort(), config.getUsername(), config.getPassword());
            return true;
        } catch (Exception e) {
            log.error("测试邮件配置失败", e);
            return false;
        }
    }
}
