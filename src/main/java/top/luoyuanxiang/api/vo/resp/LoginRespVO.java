package top.luoyuanxiang.api.vo.resp;

import top.luoyuanxiang.api.entity.Role;
import top.luoyuanxiang.api.entity.User;

import java.io.Serializable;

/**
 * 登录成功
 *
 * @author luoyuanxiang
 */
public record LoginRespVO(String token, User user, Role role) implements Serializable {

}
