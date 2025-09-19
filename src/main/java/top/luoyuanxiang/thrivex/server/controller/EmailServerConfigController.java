package top.luoyuanxiang.thrivex.server.controller;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.entity.EmailServerConfigEntity;
import top.luoyuanxiang.thrivex.server.service.IEmailServerConfigService;
import top.luoyuanxiang.thrivex.server.service.IEmailService;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;
import java.util.Objects;

/**
 * 邮件服务器配置管理
 *
 * @author luoyuanxiang
 * @since 2025-09-13
 */
@RestController
@RequestMapping("/email/config")
public class EmailServerConfigController {

    @Resource
    private IEmailServerConfigService emailServerConfigService;
    @Resource
    private IEmailService emailService;

    /**
     * 列表
     *
     * @return {@link Result }<{@link List }<{@link EmailServerConfigEntity }>>
     */
    @GetMapping("/list")
    public Result<List<EmailServerConfigEntity>> list(String key) {
        List<EmailServerConfigEntity> list = emailServerConfigService.lambdaQuery()
                .like(StrUtil.isNotBlank(key), EmailServerConfigEntity::getName, key)
                .list();
        return Result.success(list);
    }

    /**
     * 根据ID获取邮件配置
     */
    @GetMapping("/{id}")
    public Result<EmailServerConfigEntity> getConfigById(@PathVariable Long id) {
        EmailServerConfigEntity config = emailServerConfigService.getById(id);
        return Result.success(config);
    }

    /**
     * 创建邮件配置
     */
    @PostMapping
    public Result<?> createConfig(@Valid @RequestBody EmailServerConfigEntity emailServerConfigEntity) {
        boolean exists = emailServerConfigService.lambdaQuery()
                .eq(EmailServerConfigEntity::getName, emailServerConfigEntity.getName())
                .exists();
        if (exists) {
            return Result.error("邮件配置已存在：" + emailServerConfigEntity.getName());
        }
        emailServerConfigEntity.insert();
        return Result.success();
    }

    /**
     * 更新邮件配置
     */
    @PutMapping
    public Result<?> updateConfig(@Valid @RequestBody EmailServerConfigEntity emailServerConfigEntity) {
        EmailServerConfigEntity byId = emailServerConfigService.getById(emailServerConfigEntity.getId());
        if (!Objects.equals(byId.getName(), emailServerConfigEntity.getName())) {
            boolean exists = emailServerConfigService.lambdaQuery()
                    .eq(EmailServerConfigEntity::getName, emailServerConfigEntity.getName())
                    .exists();
            if (exists) {
                return Result.error("邮件配置已存在：" + emailServerConfigEntity.getName());
            }
        }
        emailServerConfigEntity.updateById();
        return Result.success();
    }

    /**
     * 删除邮件配置
     */
    @DeleteMapping("/{id}")
    public Result<?> deleteConfig(@PathVariable Long id) {
        emailServerConfigService.removeById(id);
        return Result.success();
    }

    /**
     * 设置默认邮件配置
     */
    @PutMapping("setDefaultConfig/{id}")
    public Result<?> setDefaultConfig(@PathVariable Long id) {
        emailServerConfigService.lambdaUpdate()
                .set(EmailServerConfigEntity::getIsDefault, false)
                .update();
        emailServerConfigService.lambdaUpdate()
                .set(EmailServerConfigEntity::getIsDefault, true)
                .eq(EmailServerConfigEntity::getId, id)
                .update();
        return Result.success();
    }


    /**
     * 测试邮件配置是否有效
     */
    @PostMapping("/test/{id}")
    public Result<String> testConfig(@PathVariable Integer id) {
        boolean isValid = emailService.testConfig(id);
        return isValid ? Result.success("邮件配置测试成功") : Result.error("邮件配置测试失败，请检查配置信息");
    }

}
