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
@TableName("cate")
public class Cate extends Model<Cate> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    @TableField("name")
    private String name;

    /**
     * 分类图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 分类链接
     */
    @TableField("url")
    private String url;

    /**
     * 分类标识
     */
    @TableField("mark")
    private String mark;

    /**
     * 分类级别
     */
    @TableField("level")
    private Integer level;

    /**
     * 分类顺序
     */
    @TableField("order")
    private Integer order;

    /**
     * 导航还是分类
     */
    @TableField("type")
    private String type;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
