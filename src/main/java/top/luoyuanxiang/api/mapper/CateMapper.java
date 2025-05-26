package top.luoyuanxiang.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.luoyuanxiang.api.entity.Cate;
import top.luoyuanxiang.api.vo.CateArticleCountVO;

import java.util.List;

/**
 * <p>
 * 分类 Mapper 接口
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Mapper
public interface CateMapper extends BaseMapper<Cate> {

    @Select("select MIN(c.name) AS name, count(*) as count from article a join article_cate ac on a.id = ac.article_id join cate c on c.id = ac.cate_id group by c.id")
    List<CateArticleCountVO> cateArticleCount();
}
