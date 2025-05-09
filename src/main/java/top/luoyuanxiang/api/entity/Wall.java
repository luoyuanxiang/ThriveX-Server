package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.luoyuanxiang.api.base.BaseEntity;


/**
 * <p>
 * 留言墙
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
public class Wall extends BaseEntity<Wall> {

    /**
     * 留言人名称
     */
    private String name;

    /**
     * 留言内容
     */
    private String content;

    /**
     * 留言墙颜色
     */
    private String color;

    /**
     * 留言者邮箱
     */
    private String email;

    private Integer cateId;

    /**
     * 是否审核
     */
    private Integer auditStatus;

    /**
     * 是否为精选留言
     */
    private Integer isChoice;

    @TableField(exist = false)
    private WallCate cate;
}
