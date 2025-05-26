package top.luoyuanxiang.api.vo.comment;

import lombok.Data;
import top.luoyuanxiang.api.vo.FilterVo;

@Data
public class CommentFilterVo extends FilterVo {
    /**
     * 默认为树形结构，如果设置了list模式，则查询列表结构
     */
    private String pattern;

    /**
     * 0表示获取待审核的评论 | 1表示获取审核通过的评论（默认）
     */
    private Integer status = 1;

    /**
     * 根据内容关键词筛选
     */
    private String content;

    /**
     * 文章 ID
     */
    private Integer articleId;
}
