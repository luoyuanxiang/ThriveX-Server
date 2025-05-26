package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <p>
 * 角色权限
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
public class Permission extends Model<Permission> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 权限标识
     */
    private String name;

    /**
     * 权限介绍
     */
    private String description;

    /**
     * 权限分组
     */
    private String group;
}
