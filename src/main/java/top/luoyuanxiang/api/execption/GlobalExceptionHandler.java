package top.luoyuanxiang.api.execption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.luoyuanxiang.api.utils.Result;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    // 处理自定义的异常
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public Result<Object> customException(CustomException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    // 处理所有异常
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Object> exception(Exception e) {
        log.error("异常：{}", e.getMessage(), e);
        return Result.error(e.getMessage());
    }
}
