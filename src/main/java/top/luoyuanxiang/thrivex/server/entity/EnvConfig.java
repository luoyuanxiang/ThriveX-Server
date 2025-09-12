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
 * 
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Getter
@Setter
@ToString
@TableName("env_config")
public class EnvConfig extends Model<EnvConfig> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    /**
     * 配置项
     */
    @TableField("value")
    private String value;

    /**
     * 配置备注
     */
    @TableField("notes")
    private String notes;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
