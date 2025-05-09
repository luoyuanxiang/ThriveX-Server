package top.luoyuanxiang.api.vo.user;

import lombok.Data;

@Data
public class UserInfoDTO {
    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户介绍
     */
    private String info;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 角色 ID
     */
    private String roleId;
}