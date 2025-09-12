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
@TableName("swiper")
public class Swiper extends Model<Swiper> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("title")
    private String title;

    @TableField("description")
    private String description;

    @TableField("image")
    private String image;

    @TableField("url")
    private String url;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
