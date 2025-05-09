package top.luoyuanxiang.api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.luoyuanxiang.api.base.BaseEntity;


/**
 * <p>
 * 足迹
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
public class Footprint extends BaseEntity<Footprint> {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 地址
     */
    private String address;

    /**
     * 坐标纬度
     */
    private String position;

    /**
     * 图片
     */
    private String images;
}
