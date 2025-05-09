package top.luoyuanxiang.api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.luoyuanxiang.api.base.BaseEntity;


/**
 * <p>
 * 闪恋
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
public class Record extends BaseEntity<Record> {

    /**
     * 内容
     */
    private String content;

    /**
     * 图片
     */
    private String images;
}
