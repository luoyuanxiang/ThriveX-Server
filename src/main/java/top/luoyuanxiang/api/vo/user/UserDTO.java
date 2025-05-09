package top.luoyuanxiang.api.vo.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Integer id;
    private LocalDateTime createTime;
    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 角色 ID
     */
    private String roleId;
}
