package top.luoyuanxiang.thrivex.server.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.luoyuanxiang.thrivex.server.entity.User;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 实现 UserDetails 接口的用户详情类
 */
public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 这里简化处理，实际项目中应该从数据库中获取用户的角色和权限
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRoleId() != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoleId()));
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}