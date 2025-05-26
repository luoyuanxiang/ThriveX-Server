package top.luoyuanxiang.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.luoyuanxiang.api.entity.Comment;
import top.luoyuanxiang.api.vo.comment.CommentFilterVo;

import java.util.List;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 列表
     *
     * @param filterVo filter vo
     * @return {@link List }<{@link Comment }>
     */
    List<Comment> list(@Param("vo") CommentFilterVo filterVo);

    /**
     * 分页
     *
     * @param page     页
     * @param filterVo filter vo
     */
    Page<Comment> paging(Page<Comment> page, @Param("vo") CommentFilterVo filterVo);
}
