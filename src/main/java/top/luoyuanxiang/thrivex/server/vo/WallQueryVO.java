package top.luoyuanxiang.thrivex.server.vo;

import lombok.Getter;
import lombok.Setter;
import top.luoyuanxiang.thrivex.server.entity.WallEntity;

/**
 * 留言查询
 *
 * @author luoyuanxiang
 */
@Getter
@Setter
public class WallQueryVO extends QueryCommonVO<WallEntity> {

    /**
     * 根据分类进行筛选
     */
    private Integer cateId;

    /**
     * 0表示获取待审核的留言 | 1表示获取审核通过的留言（默认）
     */
    private Integer status = 1;

    private Integer id;
}
