package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.luoyuanxiang.api.base.BaseEntity;


/**
 * <p>
 * 相册信息
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
@TableName("album_cate")
public class AlbumCate extends BaseEntity<AlbumCate> {

    /**
     * 相册名称
     */
    private String name;

    /**
     * 文章封面
     */
    private String cover;

    @TableField(exist = false)
    private Long count;
}
