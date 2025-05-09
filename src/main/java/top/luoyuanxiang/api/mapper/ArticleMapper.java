package top.luoyuanxiang.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.luoyuanxiang.api.entity.Article;
import top.luoyuanxiang.api.vo.article.ArticleFillterVo;

import java.util.List;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 寻呼
     *
     * @param page     页
     * @param filterVo filter vo
     */
    Page<Article> paging(Page<Article> page, @Param("vo") ArticleFillterVo filterVo, @Param("isAdmin") boolean isAdmin);

    /**
     * 列表
     *
     * @param filterVo filter vo
     * @return {@link List }<{@link Article }>
     */
    List<Article> list(@Param("vo") ArticleFillterVo filterVo, @Param("isAdmin") boolean isAdmin);

    /**
     * 获取随机文章
     *
     * @return {@link List }<{@link Article }>
     */
    List<Article> getRandomArticles(@Param("count") Integer count);

}
