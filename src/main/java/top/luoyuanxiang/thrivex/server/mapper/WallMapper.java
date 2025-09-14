package top.luoyuanxiang.thrivex.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.luoyuanxiang.thrivex.server.entity.WallEntity;
import top.luoyuanxiang.thrivex.server.vo.WallQueryVO;

import java.util.List;

/**
 * <p>
 * 留言墙 Mapper 接口
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Mapper
public interface WallMapper extends BaseMapper<WallEntity> {

    /**
     * 获取留言列表
     *
     * @param filterVo 过滤 VO
     * @return {@link List }<{@link WallEntity }>
     */
    List<WallEntity> list(@Param("vo") WallQueryVO filterVo);

    /**
     * 获取留言列表
     *
     * @param filterVo 过滤 VO
     * @return {@link List }<{@link WallEntity }>
     */
    Page<WallEntity> list(Page<WallEntity> page, WallQueryVO filterVo);
}
