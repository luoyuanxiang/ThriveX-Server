package top.luoyuanxiang.thrivex.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 用户 token
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Getter
@Setter
@ToString
@TableName("user_token")
public class UserTokenEntity extends Model<UserTokenEntity> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户 ID
     */
    @TableField("uid")
    private Integer uid;

    /**
     * 用户token
     */
    @TableField("token")
    private String token;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
