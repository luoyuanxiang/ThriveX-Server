package top.luoyuanxiang.thrivex.server.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 *
 * @author luoyuanxiang
 */
@Getter
@Setter
public class CustomException extends RuntimeException {

    private Integer code;

    private String message;

    /**
     * 通过状态码和错误消息创建异常对象
     *
     * @param code    状态码
     * @param message 错误消息
     */
    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CustomException(String message) {
        super(message);
        this.code = 400;
        this.message = message;
    }
}
