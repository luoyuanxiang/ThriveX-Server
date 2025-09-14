package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.entity.EmailTemplateEntity;
import top.luoyuanxiang.thrivex.server.service.IEmailTemplateService;
import top.luoyuanxiang.thrivex.server.vo.Paging;
import top.luoyuanxiang.thrivex.server.vo.Result;

/**
 * 邮件模板管理
 *
 * @author luoyuanxiang
 * @since 2025-09-13
 */
@RestController
@RequestMapping("/email/template")
public class EmailTemplateController {

    @Resource
    private IEmailTemplateService emailTemplateService;

    /**
     * 分页获取邮件模板
     */
    @GetMapping("/paging")
    public Result<Paging<EmailTemplateEntity>> paging(Integer page, Integer size) {
        Page<EmailTemplateEntity> templates = emailTemplateService.page(new Page<>(page, size));
        return Result.page(templates);
    }

    /**
     * 根据ID获取邮件模板
     */
    @GetMapping("/{id}")
    public Result<EmailTemplateEntity> getTemplateById(@PathVariable Integer id) {
        EmailTemplateEntity template = emailTemplateService.getById(id);
        return Result.success(template);
    }

    /**
     * 创建邮件模板
     */
    @PostMapping
    public Result<?> createTemplate(@Valid @RequestBody EmailTemplateEntity template) {
        template.insert();
        return Result.success();
    }

    /**
     * 更新邮件模板
     */
    @PutMapping
    public Result<?> updateTemplate(@Valid @RequestBody EmailTemplateEntity emailTemplateEntity) {
        emailTemplateEntity.updateById();
        return Result.success();
    }

    /**
     * 删除邮件模板
     */
    @DeleteMapping("/{id}")
    public Result<?> deleteTemplate(@PathVariable Long id) {
        emailTemplateService.removeById(id);
        return Result.success();
    }

}
