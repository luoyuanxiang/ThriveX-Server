package top.luoyuanxiang.thrivex.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.luoyuanxiang.thrivex.server.entity.ArticleEntity;
import top.luoyuanxiang.thrivex.server.vo.ArticleQueryVO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Mapper
public interface ArticleMapper extends BaseMapper<ArticleEntity> {

    /**
     * 列表
     *
     * @param articleQueryVO 文章查询 vo
     * @return {@link List }<{@link ArticleEntity }>
     */
    List<ArticleEntity> list(@Param("vo") ArticleQueryVO articleQueryVO);

    /**
     * 列表
     *
     * @param page           页
     * @param articleQueryVO 文章查询 vo
     * @return {@link List }<{@link ArticleEntity }>
     */
    Page<ArticleEntity> list(Page<ArticleEntity> page,@Param("vo") ArticleQueryVO articleQueryVO);
}
