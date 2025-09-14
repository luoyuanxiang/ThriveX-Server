package top.luoyuanxiang.thrivex.server.service;

import top.luoyuanxiang.thrivex.server.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
public interface IUserService extends IService<UserEntity> {

    /**
     * 按用户名查找
     *
     * @param username 用户名
     * @return {@link UserEntity }
     */
    UserEntity findByUsername(String username);
}
