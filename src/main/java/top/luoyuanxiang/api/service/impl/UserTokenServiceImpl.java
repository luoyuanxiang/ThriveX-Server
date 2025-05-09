package top.luoyuanxiang.api.service.impl;

import top.luoyuanxiang.api.entity.UserToken;
import top.luoyuanxiang.api.mapper.UserTokenMapper;
import top.luoyuanxiang.api.service.IUserTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 token 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements IUserTokenService {

}
