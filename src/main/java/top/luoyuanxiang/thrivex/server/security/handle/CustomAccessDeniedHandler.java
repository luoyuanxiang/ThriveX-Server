package top.luoyuanxiang.thrivex.server.security.handle;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.io.IOException;

/**
 * 授权异常处理
 *
 * @author luoyuanxiang
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result<Object> error = Result.error(403, "无权限访问");
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.getWriter().print(JSONUtil.toJsonStr(error));
    }
}
