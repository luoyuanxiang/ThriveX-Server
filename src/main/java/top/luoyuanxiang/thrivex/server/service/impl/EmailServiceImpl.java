package top.luoyuanxiang.thrivex.server.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.annotation.Resource;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.entity.*;
import top.luoyuanxiang.thrivex.server.service.*;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 邮件服务
 *
 * @author luoyuanxiang
 */
@Slf4j
@Service
public class EmailServiceImpl implements IEmailService {

    @Resource
    private IEmailServerConfigService emailServerConfigService;
    @Resource
    private IWebConfigService webConfigService;
    @Resource
    private Configuration freemarkerConfiguration;
    @Resource
    private IEmailTemplateService emailTemplateService;
    @Resource
    private IUserService userService;

    @Async
    @Override
    public void sendDualFormatEmail(String to, String templateName, Map<String, Object> variables) {
        Optional<EmailServerConfigEntity> emailServerConfigEntityOptional = emailServerConfigService.lambdaQuery()
                .eq(EmailServerConfigEntity::getIsDefault, true)
                .oneOpt();
        if (emailServerConfigEntityOptional.isEmpty()) {
            throw new RuntimeException("未找到默认的邮件服务器配置");
        }
        EmailServerConfigEntity config = emailServerConfigEntityOptional.get();
        Optional<EmailTemplateEntity> emailTemplateEntityOptional = emailTemplateService.lambdaQuery().eq(EmailTemplateEntity::getName, templateName)
                .oneOpt();
        if (emailTemplateEntityOptional.isEmpty()) {
            throw new RuntimeException("未找到邮件模板：" + templateName);
        }
        sendDualFormatEmail(to, emailTemplateEntityOptional.get(), config, variables);
    }

    @Override
    public boolean sendMail(Map<String, Object> params) {
        Object templateName = params.get("templateName");
        Object emailServerId = params.get("emailServerId");
        Object mail = params.get("mail");
        if (templateName != null && emailServerId != null && mail != null) {
            params.remove("templateName");
            params.remove("emailServerId");
            params.remove("mail");
            EmailServerConfigEntity config = emailServerConfigService.getById(Integer.parseInt(emailServerId + ""));
            if (Objects.isNull(config)) {
                throw new RuntimeException("未找到默认的邮件服务器配置");
            }
            Optional<EmailTemplateEntity> emailTemplateEntityOptional = emailTemplateService.lambdaQuery().eq(EmailTemplateEntity::getName, templateName)
                    .oneOpt();
            if (emailTemplateEntityOptional.isEmpty()) {
                throw new RuntimeException("未找到邮件模板：" + templateName);
            }
            sendDualFormatEmail(mail + "", emailTemplateEntityOptional.get(), config, params);
            return true;
        }
        return false;
    }

    @Override
    public boolean testConfig(Integer id) {
        EmailServerConfigEntity config = emailServerConfigService.getById(id);
        JavaMailSender javaMailSender = createJavaMailSender(config);
        testMailConnection(config, javaMailSender);
        return true;
    }

    /**
     * 发送双格式电子邮件
     *
     * @param to                  自
     * @param emailTemplateEntity 电子邮件模板实体
     * @param config              配置
     * @param variables           变量
     */
    private void sendDualFormatEmail(String to, EmailTemplateEntity emailTemplateEntity, EmailServerConfigEntity config, Map<String, Object> variables) {
        log.info("发送邮件：{}", to);
        WebConfigEntity web = webConfigService.getByName("web");
        Map<String, Object> value = web.getValue();
        Map<String, Object> site = new HashMap<>();
        UserEntity user = userService.getById(1);
        site.put("title", value.get("title"));
        site.put("subtitle", value.get("subhead"));
        site.put("logo", user.getAvatar());
        site.put("url", value.get("url"));
        variables.put("site", site);
        variables.put("createTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        JavaMailSender javaMailSender = createJavaMailSender(config);
        // 处理主题模板
        String subject;
        try {
            subject = processTemplate(emailTemplateEntity.getSubject(), variables);
        } catch (Exception e) {
            log.warn("处理主题模板失败，使用原始主题: {}", emailTemplateEntity.getSubject(), e);
            subject = emailTemplateEntity.getSubject();
        }
        final String finalSubject = subject;
        try {
            // 创建邮件消息
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            // 设置发件人、收件人、主题
            InternetAddress fromAddress = new InternetAddress(config.getUsername(), value.get("title") + "", "UTF-8");
            helper.setFrom(fromAddress);
            helper.setTo(to);
            helper.setSubject(finalSubject);

            // 渲染HTML模板
            String content;
            try {
                content = processTemplate(emailTemplateEntity.getContent(), variables);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("处理邮件内容模板失败: templateName={}, content={}", emailTemplateEntity.getName(), emailTemplateEntity.getContent(), e);
                throw new RuntimeException("处理邮件模板失败: " + emailTemplateEntity.getName(), e);
            }

            // 同时设置HTML和TEXT内容
            helper.setText(content, Objects.equals(emailTemplateEntity.getType(), "HTML"));
            // 发送邮件
            javaMailSender.send(mimeMessage);
            logEmailSend(config.getId(), emailTemplateEntity.getId(), to, finalSubject, content, true, null);
        } catch (Exception e) {
            e.printStackTrace();
            logEmailSend(config.getId(), emailTemplateEntity.getId(), to, finalSubject, null, false, e.getMessage());
            throw new RuntimeException("发送邮件失败", e);
        }
    }

    /**
     * 测试邮件连接
     *
     * @param config         配置
     * @param javaMailSender Java 邮件发件人
     */
    private void testMailConnection(EmailServerConfigEntity config, JavaMailSender javaMailSender) {
        // 测试连接
        try {
            ((JavaMailSenderImpl) javaMailSender).testConnection();
            log.info("邮件服务器连接成功: {}:{}", config.getHost(), config.getPort());
        } catch (Exception e) {
            log.error("邮件服务器连接失败: {}:{}, 错误: {}", config.getHost(), config.getPort(), e.getMessage(), e);
            throw new RuntimeException("邮件服务器连接失败: " + e.getMessage(), e);
        }
    }

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

        // 设置连接和读取超时时间
        mailSender.setProtocol("smtp");
        mailSender.setDefaultEncoding("UTF-8");
        Properties properties = mailSender.getJavaMailProperties();
        setProperties(config, properties);

        if (CollectionUtil.isNotEmpty(config.getExt())) {
            // 过滤掉可能导致冲突的属性
            Map<String, Object> extProps = new HashMap<>(config.getExt());
            extProps.remove("mail.smtp.ssl.enable");
            extProps.remove("mail.smtp.starttls.enable");
            properties.putAll(extProps);
        }

        return mailSender;
    }

    /**
     * 获取属性
     *
     * @param config 配置
     */
    private void setProperties(EmailServerConfigEntity config, Properties props) {
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.timeout", "60000"); // 15秒超时
        props.put("mail.smtp.connectiontimeout", "60000"); // 15秒连接超时
        props.put("mail.smtp.writetimeout", "60000"); // 15秒写入超时
        if (config.getSslEnable() != null && config.getSslEnable()) {
            props.put("mail.smtp.ssl.enable", true);
        }
        if (config.getTlsEnable() != null && config.getTlsEnable()) {
            props.put("mail.smtp.starttls.enable", true);
        }

        // 其他安全设置
        props.put("mail.smtp.ssl.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.socketFactory.fallback", "false");

        props.put("mail.debug", "true"); // 开启调试模式，便于排查问题
        props.put("mail.debug.auth", "true");
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
        log.setCreatedAt(LocalDateTime.now());
        log.setSentAt(LocalDateTime.now());
        log.insert();
    }

    /**
     * 处理Freemarker模板
     */
    private String processTemplate(String templateContent, Object dataModel) throws IOException, TemplateException {
        // 创建临时模板
        Template template = new Template("inlineTemplate", templateContent, freemarkerConfiguration);
        StringWriter writer = new StringWriter();
        template.process(dataModel, writer);
        return writer.toString();
    }
}
