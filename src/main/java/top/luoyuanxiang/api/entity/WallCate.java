package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <p>
 * 留言分类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
@TableName("wall_cate")
public class WallCate extends Model<WallCate> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类标识
     */
    private String mark;

    /**
     * 分类顺序
     */
    @TableField("`order`")
    private Integer order;
}
