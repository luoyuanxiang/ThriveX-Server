package top.luoyuanxiang.thrivex.server.vo;

import lombok.Getter;
import lombok.Setter;
import top.luoyuanxiang.thrivex.server.entity.ArticleEntity;

/**
 * 文章查询
 *
 * @author luoyuanxiang
 */
@Getter
@Setter
public class ArticleQueryVO extends QueryCommonVO<ArticleEntity> {

    /**
     * 根据分类进行筛选
     */
    private Integer cateId;
    /**
     * 根据标签进行筛选
     */
    private Integer tagId;
    /**
     * 是否为草稿, 默认：0 | 草稿：1
     */
    private Integer isDraft = 0;
    /**
     * 是否为严格删除, 默认：0 | 严格删除：1
     */
    private Integer isDel = 0;
}
