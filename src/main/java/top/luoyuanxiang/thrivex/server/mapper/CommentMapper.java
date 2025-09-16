package top.luoyuanxiang.thrivex.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.luoyuanxiang.thrivex.server.entity.CommentEntity;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {

    /**
     * 获取评论列表
     *
     * @param aid 援助
     * @return {@link List }<{@link CommentEntity }>
     */
    @Select("select c.* from comment c, article a where c.article_id = a.id && c.article_id = #{aid}")
    List<CommentEntity> getCommentList(Integer aid);
}
