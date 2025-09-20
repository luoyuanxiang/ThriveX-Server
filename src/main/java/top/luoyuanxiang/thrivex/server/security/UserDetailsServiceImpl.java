package top.luoyuanxiang.thrivex.server.security;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.entity.UserEntity;
import top.luoyuanxiang.thrivex.server.service.IUserService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 实现 UserDetailsService 接口的用户详情服务类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private IUserService userService;

    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        UserEntity userEntity = userService.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户不存在：" + username);
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (StrUtil.isNotBlank(userEntity.getPermissionsCode())) {
            for (String code : userEntity.getPermissionsCode().split(",")) {
                authorities.add(new SimpleGrantedAuthority(code));
            }
        }
        SecurityUser securityUser = new SecurityUser(userEntity.getUsername(), userEntity.getPassword(), authorities);
        securityUser.setUserEntity(userEntity);
        // 返回 UserDetails 实现类
        return securityUser;
    }
}