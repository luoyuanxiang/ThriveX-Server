package top.luoyuanxiang.thrivex.server.vo;

import lombok.Getter;
import lombok.Setter;
import top.luoyuanxiang.thrivex.server.entity.UserEntity;

/**
 * 用户查询
 *
 * @author luoyuanxiang
 */
@Getter
@Setter
public class UserQueryVO extends QueryCommonVO<UserEntity> {

    /**
     * 角色 ID
     */
    private Integer roleId;

    private Integer id;
}
