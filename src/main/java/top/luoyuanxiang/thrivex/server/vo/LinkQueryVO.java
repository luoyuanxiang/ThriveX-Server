package top.luoyuanxiang.thrivex.server.vo;

import lombok.Getter;
import lombok.Setter;
import top.luoyuanxiang.thrivex.server.entity.LinkEntity;

/**
 * 网站查询
 *
 * @author luoyuanxiang
 */
@Getter
@Setter
public class LinkQueryVO extends QueryCommonVO<LinkEntity> {

    /**
     * 0表示获取待审核的友联 | 1表示获取审核通过的友联（默认）
     */
    private Integer status;
}
