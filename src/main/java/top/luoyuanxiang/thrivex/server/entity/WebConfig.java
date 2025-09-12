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
 * 网站配置
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Getter
@Setter
@ToString
@TableName("web_config")
public class WebConfig extends Model<WebConfig> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 配置项名称
     */
    @TableField("name")
    private String name;

    /**
     * 配置项值
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
