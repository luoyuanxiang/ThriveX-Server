package top.luoyuanxiang.thrivex.server.service;

import top.luoyuanxiang.thrivex.server.entity.EmailServerConfigEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 邮件服务器配置表 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-13
 */
public interface IEmailServerConfigService extends IService<EmailServerConfigEntity> {

    /**
     * 测试配置
     *
     * @param id id
     * @return boolean
     */
    boolean testConfig(Integer id);
}
