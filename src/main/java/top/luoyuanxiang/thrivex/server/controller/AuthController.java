package top.luoyuanxiang.thrivex.server.controller;

import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.luoyuanxiang.thrivex.server.entity.RoleEntity;
import top.luoyuanxiang.thrivex.server.entity.UserEntity;
import top.luoyuanxiang.thrivex.server.security.JwtUtils;
import top.luoyuanxiang.thrivex.server.security.UserDetailsImpl;
import top.luoyuanxiang.thrivex.server.service.IUserService;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.io.Serializable;

/**
 * 登录
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
    public Result<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        // 设置认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);

        return Result.success(new JwtResponse(userDetails.userEntity(), userDetails.userEntity().getRoleEntity(), jwt));
    }

    /**
     * 用户登录请求体
     *
     * @param username 用户名
     * @param password 密码
     * @author luoyuanxiang
     */
    public record LoginRequest(String username, String password) implements Serializable {
    }

    /**
     * 登录响应体
     *
     * @param userEntity  用户信息
     * @param roleEntity  角色信息
     * @param token JWT令牌
     * @author luoyuanxiang
     */
    public record JwtResponse(UserEntity userEntity, RoleEntity roleEntity, String token) {

    }
}