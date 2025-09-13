package top.luoyuanxiang.thrivex.server.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常返回
 *
 * @author luoyuanxiang
 */
@RestController
public class CustomErrorController implements ErrorController {

    // 自定义错误响应结构
    private Result<Map<String, Object>> createErrorResponse(HttpServletRequest request, HttpStatus status) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", System.currentTimeMillis());
        error.put("status", status.value());
        error.put("error", status.getReasonPhrase());

        // 获取错误信息（如异常消息）
        String message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        error.put("message", message != null && !message.isEmpty() ? message : "No error message available");

        // 请求路径
        error.put("path", request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));

        return new Result<>(status.value(), error.get("message").toString(), error);
    }

    // 重写/error端点
    @RequestMapping("/error")
    public ResponseEntity<Result<Map<String, Object>>> handleError(HttpServletRequest request) {
        // 获取状态码（默认500）
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus httpStatus = status != null
                ? HttpStatus.valueOf(Integer.parseInt(status.toString()))
                : HttpStatus.INTERNAL_SERVER_ERROR;

        // 返回自定义响应
        return new ResponseEntity<>(createErrorResponse(request, httpStatus), httpStatus);
    }
}
