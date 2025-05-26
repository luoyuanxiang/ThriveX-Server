package top.luoyuanxiang.api.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import top.luoyuanxiang.api.annotation.PremName;
import top.luoyuanxiang.api.utils.EmailUtils;
import top.luoyuanxiang.api.utils.Result;
import top.luoyuanxiang.api.vo.email.DismissEmailDTO;
import top.luoyuanxiang.api.vo.email.WallEmailDTO;


/**
 * 邮件管理
 *
 * @author luoyuanxiang
 */
@RestController
@RequestMapping("/api/admin/email")
@Transactional
public class EmailController {
    @Resource
    private EmailUtils emailUtils;
    @Resource
    private TemplateEngine templateEngine;

    /**
     * 驳回通知邮件
     *
     * @param email 电子邮件
     * @return {@link Result }<{@link ? }>
     */
    @PremName("email:dismiss")
    @PostMapping("/dismiss")
    public Result<?> dismiss(@RequestBody DismissEmailDTO email) {
        // 处理邮件模板
        Context context = new Context();
        context.setVariable("type", email.getType());
        context.setVariable("recipient", email.getRecipient());
        context.setVariable("time", email.getTime());
        context.setVariable("content", email.getContent());
        context.setVariable("url", email.getUrl());
        String template = templateEngine.process("dismiss_email", context);

        emailUtils.send(email.getTo() != null ? email.getTo() : null, email.getSubject(), template);

        return Result.success();
    }

    /**
     * 回复留言
     *
     * @param email 电子邮件
     * @return {@link Result }<{@link ? }>
     */
    @PremName("email:reply_wall")
    @PostMapping("/reply_wall")
    public Result<?> replyWall(@RequestBody WallEmailDTO email) {
        // 处理邮件模板
        Context context = new Context();
        context.setVariable("recipient", email.getRecipient());
        context.setVariable("time", email.getTime());
        context.setVariable("your_content", email.getYour_content());
        context.setVariable("reply_content", email.getReply_content());
        context.setVariable("url", email.getUrl());
        String template = templateEngine.process("wall_email", context);

        emailUtils.send(email.getTo() != null ? email.getTo() : null, "您有新的消息~", template);

        return Result.success();
    }
}