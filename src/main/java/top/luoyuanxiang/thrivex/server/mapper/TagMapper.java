package top.luoyuanxiang.thrivex.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.luoyuanxiang.thrivex.server.entity.Tag;

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
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 统计每个标签下的文章数量
     *
     * @return {@link List }<{@link Tag }>
     */
    @Select("select t.id, t.name, count(at.article_id) as count from article a, tag t, article_tag at where a.id = at.article_id and t.id = at.tag_id group by t.id, t.name order by count desc")
    List<Tag> staticArticleCount();
}
