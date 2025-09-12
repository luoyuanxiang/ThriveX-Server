package top.luoyuanxiang.thrivex.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import top.luoyuanxiang.thrivex.server.entity.AlbumCate;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Mapper
public interface AlbumCateMapper extends BaseMapper<AlbumCate> {

    /**
     * 分页
     *
     * @param objectPage 对象页面
     * @return {@link Page }<{@link AlbumCate }>
     */
    Page<AlbumCate> paging(Page<AlbumCate> objectPage);
}
