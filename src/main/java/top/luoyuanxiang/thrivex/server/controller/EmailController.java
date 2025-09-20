package top.luoyuanxiang.thrivex.server.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.luoyuanxiang.thrivex.server.ann.HasPermission;
import top.luoyuanxiang.thrivex.server.service.IEmailService;
import top.luoyuanxiang.thrivex.server.vo.DismissEmailVO;
import top.luoyuanxiang.thrivex.server.vo.Result;
import top.luoyuanxiang.thrivex.server.vo.WallEmailVO;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 邮件管理
 *
 * @author luoyuanxiang
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Resource
    private IEmailService emailService;

    /**
     * 驳回通知邮件
     *
     * @param email 电子邮件
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("email:dismiss")
    @PostMapping("/dismiss")
    public Result<String> dismiss(@RequestBody DismissEmailVO email) {
        if (Objects.equals("友链", email.getType())) {
            Map<String, Object> variables = new HashMap<>();
            variables.put("displayName", email.getRecipient());
            variables.put("review", false);
            variables.put("reviewReason", email.getContent());
            emailService.sendDualFormatEmail(email.getTo(), "友链自助提交成功通知", variables);
        }
        return Result.success();
    }

    /**
     * 回复留言
     *
     * @param email 电子邮件
     * @return {@link Result }
     */
    @HasPermission("email:reply_wall")
    @PostMapping("/reply_wall")
    public Result<String> replyWall(@RequestBody WallEmailVO email) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("replier", email.getRecipient());
        variables.put("commentSubjectUrl", email.getUrl());
        variables.put("commentSubjectTitle", email.getRecipient());
        variables.put("isQuoteReply", false);
        variables.put("commentContent", email.getReply_content());
        variables.put("content", email.getYour_content());
        emailService.sendDualFormatEmail(email.getTo(), "有人回复了我", variables);

        return Result.success();
    }
}
