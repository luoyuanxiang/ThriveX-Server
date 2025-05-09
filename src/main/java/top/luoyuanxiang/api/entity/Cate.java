package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 分类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
public class Cate extends Model<Cate> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类图标
     */
    private String icon;

    /**
     * 分类链接
     */
    private String url;

    /**
     * 分类标识
     */
    private String mark;

    /**
     * 分类级别
     */
    private Integer level;

    /**
     * 分类顺序
     */
    @TableField("`order`")
    private Integer order;

    /**
     * 导航还是分类
     */
    private String type;

    @TableField(exist = false)
    private List<Cate> children = new ArrayList<>();
}
