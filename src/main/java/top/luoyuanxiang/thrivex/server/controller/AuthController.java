package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.luoyuanxiang.thrivex.server.entity.Role;
import top.luoyuanxiang.thrivex.server.entity.User;
import top.luoyuanxiang.thrivex.server.security.JwtUtils;
import top.luoyuanxiang.thrivex.server.service.IUserService;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 认证控制器，处理用户登录和生成 token
 */
@RestController
public class AuthController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private IUserService userService;

    /**
     * 用户登录接口
     */
    @PostMapping("/login")
    public Result<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // 通过用户名获取用户和角色信息
        User user = userService.findByUsername(loginRequest.username());
        String permissionsCode = user.getPermissionsCode();
        Authentication authentication;
        if (StringUtils.isNotBlank(permissionsCode)) {
            List<SimpleGrantedAuthority> authorities = Arrays.stream(permissionsCode.split(","))
                    .map(SimpleGrantedAuthority::new)
                    .toList();
            // 尝试进行认证
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password(), authorities));
        } else {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        }
        // 设置认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);

        return Result.success(new JwtResponse(user, user.getRole(), jwt));
    }

    /**
     * 用户登录请求体
     *
     * @param username 用户名
     * @param password 密码
     * @author luoyuanxiang
     */
    public record LoginRequest(String username, String password) {
    }

    /**
     * 登录响应体
     *
     * @param user  用户信息
     * @param role  角色信息
     * @param token JWT令牌
     * @author luoyuanxiang
     */
    public record JwtResponse(User user, Role role, String token) {

    }
}