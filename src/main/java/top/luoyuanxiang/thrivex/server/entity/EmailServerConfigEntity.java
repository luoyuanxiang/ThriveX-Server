package top.luoyuanxiang.thrivex.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 邮件服务器配置表
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-13
 */
@Getter
@Setter
@ToString
@TableName(value = "email_server_config", autoResultMap = true)
public class EmailServerConfigEntity extends Model<EmailServerConfigEntity> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 配置名称
     */
    @TableField("name")
    private String name;

    /**
     * 发送者邮箱地址
     */
    @TableField("username")
    private String username;

    /**
     * 密码/授权码
     */
    @TableField("password")
    private String password;

    /**
     * SMTP服务器地址
     */
    @TableField("host")
    private String host;

    /**
     * SMTP服务器端口
     */
    @TableField("port")
    private Integer port;

    /**
     * 是否启用SSL
     */
    @TableField("ssl_enable")
    private Boolean sslEnable;

    /**
     * 是否启用TLS
     */
    @TableField("tls_enable")
    private Boolean tlsEnable;

    /**
     * 是否为默认配置
     */
    @TableField("is_default")
    private Boolean isDefault;

    /**
     * 状态：1-启用，0-禁用
     */
    @TableField("enabled")
    private Boolean enabled;

    /**
     * 扩展配置
     */
    @TableField(value = "ext", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> ext;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
