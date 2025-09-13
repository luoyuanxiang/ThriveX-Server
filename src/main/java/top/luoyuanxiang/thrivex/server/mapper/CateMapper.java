package top.luoyuanxiang.thrivex.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.luoyuanxiang.thrivex.server.entity.Cate;
import top.luoyuanxiang.thrivex.server.vo.CateArticleCount;

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
public interface CateMapper extends BaseMapper<Cate> {

    @Select("select c.name AS name, count(*) as count from article a join article_cate ac on a.id = ac.article_id join cate c on c.id = ac.cate_id group by c.id")
    List<CateArticleCount> cateArticleCount();
}
