package top.luoyuanxiang.thrivex.server.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.luoyuanxiang.thrivex.server.security.UserDetailsImpl;

/**
 * 测试控制器，用于测试 Spring Security 配置
 */
@RestController
public class TestController {

    /**
     * 无需认证即可访问的接口（属于 /api/** 路径）
     */
    @GetMapping("/api/test/public")
    public String publicTest() {
        return "这是一个公开的接口，不需要认证即可访问";
    }

    /**
     * 需要认证才能访问的接口（不属于 /api/** 路径）
     */
    @GetMapping("/test/protected")
    public String protectedTest() {
        // 获取当前登录用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return "这是一个需要认证的接口，当前登录用户：" + userDetails.getUsername();
    }
}