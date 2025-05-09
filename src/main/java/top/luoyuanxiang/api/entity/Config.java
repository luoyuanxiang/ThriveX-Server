package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <p>
 * 配置
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
public class Config {

    /**
     * 配置名称
     */
    private String name;

    /**
     * 配置值
     */
    private String value;

    /**
     * 配置分组
     */
    @TableField("`group`")
    private String group;

    /**
     * 备注
     */
    private String note;
}
