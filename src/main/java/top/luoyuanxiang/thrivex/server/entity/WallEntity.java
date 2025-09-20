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
 * 留言墙
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Getter
@Setter
@ToString
@TableName("wall")
public class WallEntity extends Model<WallEntity> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 留言人名称
     */
    @TableField("name")
    private String name;

    /**
     * 留言内容
     */
    @TableField("content")
    private String content;

    /**
     * 留言墙颜色
     */
    @TableField("color")
    private String color;

    /**
     * 留言者邮箱
     */
    @TableField("email")
    private String email;

    @TableField("cate_id")
    private Integer cateId;

    /**
     * 是否审核
     */
    @TableField("audit_status")
    private Integer auditStatus;

    /**
     * 是否为精选留言
     */
    @TableField("is_choice")
    private Integer isChoice;

    /**
     * 发布时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 留言分类
     */
    @TableField(exist = false)
    private WallCateEntity cate = new WallCateEntity();

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
