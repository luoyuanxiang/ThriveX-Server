package top.luoyuanxiang.thrivex.server.security;

import cn.hutool.core.util.StrUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.luoyuanxiang.thrivex.server.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 实现 UserDetails 接口的用户详情类
 */
public record UserDetailsImpl(UserEntity user) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (StrUtil.isNotBlank(user.getPermissionsCode())) {
            for (String code : user.getPermissionsCode().split(",")) {
                authorities.add(new SimpleGrantedAuthority(code));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}