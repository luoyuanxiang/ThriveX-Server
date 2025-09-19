package top.luoyuanxiang.thrivex.server.service;

import java.util.Map;

/**
 * 邮件服务
 *
 * @author luoyuanxiang
 */
public interface IEmailService {

    /**
     * 发送同时包含HTML和TEXT格式的邮件，两者都使用Thymeleaf模板和变量
     *
     * @param to           收件人邮箱
     * @param templateName 模板名称
     * @param variables    模板变量
     */
    void sendDualFormatEmail(String to, String templateName, Map<String, Object> variables);

    /**
     * 发送邮件
     *
     * @param params 参数
     * @return boolean
     */
    boolean sendMail(Map<String, Object> params);

    /**
     * 测试配置
     *
     * @param id id
     * @return boolean
     */
    boolean testConfig(Integer id);
}
