package top.luoyuanxiang.thrivex.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.luoyuanxiang.thrivex.server.entity.LinkEntity;
import top.luoyuanxiang.thrivex.server.vo.LinkQueryVO;

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
public interface LinkMapper extends BaseMapper<LinkEntity> {

    /**
     * 列表
     *
     * @param linkQueryVO 链接查询 vo
     * @return {@link List }<{@link LinkEntity }>
     */
    List<LinkEntity> list(@Param("vo") LinkQueryVO linkQueryVO);

    /**
     * 分页
     *
     * @param page     页
     * @param filterVo 过滤 VO
     * @return {@link Page }<{@link LinkEntity }>
     */
    Page<LinkEntity> paging(Page<LinkEntity> page, @Param("vo") LinkQueryVO filterVo);
}
