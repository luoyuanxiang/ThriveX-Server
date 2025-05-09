package top.luoyuanxiang.api.vo.user;

import lombok.Data;

@Data
public class EditPassDTO {
    /**
     * 用户账号
     */
    private String username;
    /**
     * 旧密码
     */
    private String oldPassword;
    /**
     * 新密码
     */
    private String newPassword;
}
