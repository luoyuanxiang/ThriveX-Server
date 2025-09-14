package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.entity.UserEntity;
import top.luoyuanxiang.thrivex.server.mapper.UserMapper;
import top.luoyuanxiang.thrivex.server.service.IUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {
    @Override
    public UserEntity findByUsername(String username) {
        return baseMapper.findByUsername(username);
    }
}
