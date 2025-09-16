package top.luoyuanxiang.thrivex.server.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import top.luoyuanxiang.thrivex.server.exception.CustomException;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证和授权异常处理器
 */
@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public Result<Object> customException(CustomException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理用户名或密码错误异常
     */
    @ExceptionHandler(BadCredentialsException.class)
    public Result<Map<String, Object>> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("error", "Unauthorized");
        body.put("message", "用户名或密码错误");
        body.put("path", request.getDescription(false).replace("uri=", ""));
        return new Result<>(HttpStatus.UNAUTHORIZED.value(), "用户名或密码错误", body);
    }

    /**
     * 处理用户不存在异常
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public Result<Map<String, Object>> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("error", "Unauthorized");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new Result<>(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), body);
    }

    /**
     * 处理其他认证异常
     */
    @ExceptionHandler(Exception.class)
    public Result<Map<String, Object>> handleOtherException(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new Result<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), body);
    }
}