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
@TableName("album_image")
public class AlbumImage extends Model<AlbumImage> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 照片名称
     */
    @TableField("name")
    private String name;

    /**
     * 照片描述
     */
    @TableField("description")
    private String description;

    /**
     * 照片地址
     */
    @TableField("image")
    private String image;

    /**
     * 相册 ID
     */
    @TableField("cate_id")
    private Integer cateId;

    @TableField("create_time")
    private String createTime;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
