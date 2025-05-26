package top.luoyuanxiang.api.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.luoyuanxiang.api.entity.User;
import top.luoyuanxiang.api.entity.UserToken;
import top.luoyuanxiang.api.execption.CustomException;
import top.luoyuanxiang.api.mapper.UserTokenMapper;
import top.luoyuanxiang.api.properties.JwtProperties;
import top.luoyuanxiang.api.service.IUserService;
import top.luoyuanxiang.api.utils.JwtUtils;
import top.luoyuanxiang.api.utils.ThreadUserUtil;

import java.util.List;
import java.util.Objects;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private UserTokenMapper userTokenMapper;
    @Resource
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) {

        // 后端请求全部需要校验
        // 从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getTokenName());

        // 如果是预检请求，直接放行
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        if (Objects.isNull(token)) {
            throw new CustomException(401, "无效或过期的token" + request.getRequestURI());
        }

        // 校验令牌
        try {
            // 处理Authorization的Bearer
            if (token.startsWith("Bearer ")) token = token.substring(7);
            LambdaQueryWrapper<UserToken> userTokenLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userTokenLambdaQueryWrapper.eq(UserToken::getToken, token);
            List<UserToken> userTokens = userTokenMapper.selectList(userTokenLambdaQueryWrapper);
            // 如果跟之前的token相匹配则进一步判断token是否有效
            if (userTokens != null && !userTokens.isEmpty()) {
                Claims claims = JwtUtils.parseJWT(jwtProperties.getSecretKey(), token);
                Integer id = claims.get("id", Integer.class);
                String mark = claims.get("role", String.class);
                User user = userService.getById(id);
                // 设置登录用户信息
                ThreadUserUtil.setUser(user);
                ThreadUserUtil.setIsAdmin(Objects.equals("admin", mark));
                return true;
            } else {
                throw new CustomException(401, "该账号已在另一台设备登录");
            }
        } catch (Exception ex) {
            System.out.println("校验失败：" + ex);
            // 校验失败，响应401状态码
            response.setStatus(401);
            String message = ex.getMessage() != null ? ex.getMessage() : "无效或过期的token";
            throw new CustomException(401, message);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 删除登录用户信息
        ThreadUserUtil.remove();
        ThreadUserUtil.removeIsAdmin();
    }
}
