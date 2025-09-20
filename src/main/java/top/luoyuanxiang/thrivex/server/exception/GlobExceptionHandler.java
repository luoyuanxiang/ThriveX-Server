package top.luoyuanxiang.thrivex.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证和授权异常处理器
 */
@RestControllerAdvice
public class GlobExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public Result<Object> customException(CustomException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 全局异常处理
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