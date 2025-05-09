package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <p>
 * 角色
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
public class Role extends Model<Role> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色标识
     */
    private String mark;

    /**
     * 角色描述
     */
    private String description;
}
