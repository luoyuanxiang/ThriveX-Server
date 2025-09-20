package top.luoyuanxiang.thrivex.server.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import top.luoyuanxiang.thrivex.server.entity.UserEntity;

import java.util.Collection;

/**
 * 登录用户详情
 */
@Getter
@Setter
public class SecurityUser extends User {

    private UserEntity userEntity;

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}