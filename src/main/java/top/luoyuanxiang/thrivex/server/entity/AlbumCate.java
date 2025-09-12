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
@TableName("album_cate")
public class AlbumCate extends Model<AlbumCate> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 相册名称
     */
    @TableField("name")
    private String name;

    /**
     * 相册封面
     */
    @TableField("cover")
    private String cover;

    /**
     * 相册的照片数量
     */
    @TableField(exist = false)
    private Integer count;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
