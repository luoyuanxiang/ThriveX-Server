package top.luoyuanxiang.api.vo.article;

import lombok.Data;
import top.luoyuanxiang.api.vo.FilterVo;

@Data
public class ArticleFillterVo extends FilterVo {
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
