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
@TableName("route")
public class RouteEntity extends Model<RouteEntity> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 路由路径
     */
    @TableField("path")
    private String path;

    /**
     * 路由描述
     */
    @TableField("description")
    private String description;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
