package top.luoyuanxiang.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.luoyuanxiang.api.entity.ArticleTag;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    /**
     * 按标签 ID 查找
     *
     * @param tagId 标签 ID
     * @return {@link List }<{@link ArticleTag }>
     */
    @Select("select ta.article_id, ta.tag_id, t.name from article_tag ta left join tag t on t.id = ta.tag_id where ta.tag_id = #{tagId}")
    List<ArticleTag> findByTagId(@Param("tagId") Integer tagId);
}
