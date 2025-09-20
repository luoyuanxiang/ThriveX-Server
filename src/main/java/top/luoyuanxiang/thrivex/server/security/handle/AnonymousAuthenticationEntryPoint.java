package top.luoyuanxiang.thrivex.server.security.handle;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.io.IOException;

/**
 * 匿名用户访问处理
 *
 * @author luoyuanxiang
 */
@Slf4j
@Component
public class AnonymousAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("用户需要登录，访问[{}]失败，AuthenticationException={}", request.getRequestURI(), authException.getMessage(), authException);
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        Result<String> object = Result.error(424, "未登录，请登录访问");
        response.getWriter().print(JSONUtil.toJsonStr(object));
    }
}
