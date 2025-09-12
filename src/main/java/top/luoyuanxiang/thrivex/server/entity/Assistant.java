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
 * 助手管理
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Getter
@Setter
@ToString
@TableName("assistant")
public class Assistant extends Model<Assistant> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 助手名称
     */
    @TableField("name")
    private String name;

    /**
     * 密钥
     */
    @TableField("key")
    private String key;

    /**
     * API 地址
     */
    @TableField("url")
    private String url;

    /**
     * 模型
     */
    @TableField("model")
    private String model;

    /**
     * 是否被启用
     */
    @TableField("is_default")
    private Byte isDefault;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
