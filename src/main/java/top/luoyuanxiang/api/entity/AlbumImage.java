package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.luoyuanxiang.api.base.BaseEntity;


/**
 * <p>
 * 相册
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
@TableName("album_image")
public class AlbumImage extends BaseEntity<AlbumImage> {

    /**
     * 相册名称
     */
    private String name;

    /**
     * 相册介绍
     */
    private String description;

    /**
     * 相册地址
     */
    private String image;

    /**
     * 相册ID
     */
    private Integer cateId;
}
