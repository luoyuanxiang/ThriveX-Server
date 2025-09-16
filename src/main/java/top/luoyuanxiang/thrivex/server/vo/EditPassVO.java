package top.luoyuanxiang.thrivex.server.vo;

/**
 * 用户密码修改
 *
 * @param oldUsername 旧用户账号
 * @param newUsername 新用户账号
 * @param oldPassword 旧用户密码
 * @param newPassword 新用户密码
 * @author luoyuanxiang
 */
public record EditPassVO(String oldUsername, String newUsername, String oldPassword, String newPassword) {
}
