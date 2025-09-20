package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.entity.UserTokenEntity;
import top.luoyuanxiang.thrivex.server.mapper.UserTokenMapper;
import top.luoyuanxiang.thrivex.server.service.IUserTokenService;

/**
 * <p>
 * 用户 token 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserTokenEntity> implements IUserTokenService {

}
