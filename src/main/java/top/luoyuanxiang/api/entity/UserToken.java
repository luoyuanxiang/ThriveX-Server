package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <p>
 * 用户 token
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
@TableName("user_token")
public class UserToken extends Model<UserToken> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户 ID
     */
    private Integer uid;

    /**
     * 用户token
     */
    private String token;
}
