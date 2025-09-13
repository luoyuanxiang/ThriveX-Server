package top.luoyuanxiang.thrivex.server.security;

import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.entity.User;
import top.luoyuanxiang.thrivex.server.service.IUserService;

/**
 * 实现 UserDetailsService 接口的用户详情服务类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在：" + username);
        }
        // 返回 UserDetails 实现类
        return new UserDetailsImpl(user);
    }
}