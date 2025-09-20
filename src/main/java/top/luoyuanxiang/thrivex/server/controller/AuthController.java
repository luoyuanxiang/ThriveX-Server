package top.luoyuanxiang.thrivex.server.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.luoyuanxiang.thrivex.server.ann.NoAuth;
import top.luoyuanxiang.thrivex.server.entity.RoleEntity;
import top.luoyuanxiang.thrivex.server.entity.UserEntity;
import top.luoyuanxiang.thrivex.server.security.JwtUtils;
import top.luoyuanxiang.thrivex.server.security.SecurityUser;
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

    @Resource
    private HttpServletRequest request;

    /**
     * 用户登录接口
     */
    @NoAuth
    @PostMapping("/login")
    public Result<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        // 设置认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);
        userDetails.getUserEntity().setPassword("只有聪明的人才能看到密码");

        return Result.success(new JwtResponse(userDetails.getUserEntity(), userDetails.getUserEntity().getRole(), jwt));
    }

    /**
     * 检查
     */
    @GetMapping("/check")
    public Result<String> check() {
        try {
            String token = jwtUtils.parseJwt(request);
            boolean tokenExpired = jwtUtils.isTokenExpired(token);
            return tokenExpired ? Result.error(424, "token已过期") : Result.success();
        } catch (Exception e) {

        }
        return Result.error(424, "token 无效");
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
     * @param user  用户信息
     * @param role  角色信息
     * @param token JWT令牌
     * @author luoyuanxiang
     */
    public record JwtResponse(UserEntity user, RoleEntity role, String token) {

    }
}