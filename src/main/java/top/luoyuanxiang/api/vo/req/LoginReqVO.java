package top.luoyuanxiang.api.vo.req;

import java.io.Serializable;

/**
 * 登录信息
 *
 * @param username 用户名
 * @param password 密码
 * @author luoyuanxiang
 */
public record LoginReqVO(String username, String password) implements Serializable {
}
