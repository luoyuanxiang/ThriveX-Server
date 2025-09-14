package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import freemarker.template.Configuration;
import jakarta.annotation.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.entity.EmailLogsEntity;
import top.luoyuanxiang.thrivex.server.entity.EmailServerConfigEntity;
import top.luoyuanxiang.thrivex.server.entity.EmailTemplateEntity;
import top.luoyuanxiang.thrivex.server.mapper.EmailTemplateMapper;
import top.luoyuanxiang.thrivex.server.service.IEmailTemplateService;

import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Resource
    private Configuration freemarkerConfig;

    private static final Pattern PATTERN = Pattern.compile("\\{(.+?)}");

    /**
     * 创建JavaMailSender
     *
     * @param config 邮件服务器配置
     * @return {@link JavaMailSender }
     */
    private JavaMailSender createJavaMailSender(EmailServerConfigEntity config) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(config.getHost());
        mailSender.setPort(config.getPort());
        mailSender.setUsername(config.getUsername());
        mailSender.setPassword(config.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", config.getTlsEnable() != null ? config.getTlsEnable() : false);
        props.put("mail.smtp.ssl.enable", config.getSslEnable() != null ? config.getSslEnable() : false);
        props.put("mail.debug", "false"); // 调试模式
        if (config.getExt() != null) {
            props.putAll(config.getExt());
        }
        return mailSender;
    }

    /**
     * 处理模板
     *
     * @param template 模板
     * @param values   参数值
     * @return {@link String }
     */
    public static String processTemplate(String template, Map<String, String> values) {
        Matcher matcher = PATTERN.matcher(template);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String key = matcher.group(1);
            // 如果没有对应值则保留原占位符
            String value = values.getOrDefault(key, matcher.group());
            matcher.appendReplacement(sb, value);
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    /**
     * 记录邮件发送日志
     *
     * @param configId     配置 ID
     * @param templateId   模板 ID
     * @param recipient    收件人
     * @param subject      主题
     * @param content      内容
     * @param status       地位
     * @param errorMessage 错误信息
     */
    private void logEmailSend(Integer configId, Integer templateId, String recipient, String subject, String content, boolean status, String errorMessage) {
        EmailLogsEntity log = new EmailLogsEntity();
        log.setRecipient(recipient);
        log.setSubject(subject);
        log.setContent(content);
        log.setStatus(status);
        log.setErrorMessage(errorMessage);
        log.setEmailConfigId(configId);
        log.setEmailTemplateId(templateId);
        log.insert();
    }
}
