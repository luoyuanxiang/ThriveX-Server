package liuyuyang.net.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;
import liuyuyang.net.common.utils.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 限流处理
 *
 * @author luoyuanxiang
 */
@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    @Resource
    private RateLimiter rateLimiter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (rateLimiter.tryAcquire()) {
            return true;
        } else {
            response.setStatus(429);
            Result<String> result = Result.error(429, "Too many requests!");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(result));
            return false;
        }
    }
}
