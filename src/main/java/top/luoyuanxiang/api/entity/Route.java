package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <p>
 * 路由
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
public class Route extends Model<Route> {
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 路由路径
     */
    private String path;

    /**
     * 路由描述
     */
    private String description;
}
