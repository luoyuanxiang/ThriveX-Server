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
import java.time.LocalDateTime;

/**
 * <p>
 * 邮件日志
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-13
 */
@Getter
@Setter
@ToString
@TableName("email_logs")
public class EmailLogsEntity extends Model<EmailLogsEntity> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 收件人
     */
    @TableField("recipient")
    private String recipient;

    /**
     * 标题
     */
    @TableField("subject")
    private String subject;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 状态:0-失败，1-成功
     */
    @TableField("status")
    private Boolean status;

    /**
     * 错误日志
     */
    @TableField("error_message")
    private String errorMessage;

    /**
     * 邮件服务ID
     */
    @TableField("email_config_id")
    private Integer emailConfigId;

    /**
     * 邮件模板ID
     */
    @TableField("email_template_id")
    private Integer emailTemplateId;

    /**
     * 邮件发送时间
     */
    @TableField("sent_at")
    private LocalDateTime sentAt;

    /**
     * 邮件创建时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
