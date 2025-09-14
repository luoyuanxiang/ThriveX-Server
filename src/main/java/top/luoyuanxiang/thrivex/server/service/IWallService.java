package top.luoyuanxiang.thrivex.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.luoyuanxiang.thrivex.server.entity.WallEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.thrivex.server.vo.WallQueryVO;

import java.util.List;

/**
 * <p>
 * 留言墙 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
public interface IWallService extends IService<WallEntity> {

    /**
     * 新增留言
     *
     * @param wall 墙
     */
    void add(WallEntity wall);

    /**
     * 获取留言列表
     *
     * @param filterVo 过滤 VO
     * @return {@link List }<{@link WallEntity }>
     */
    List<WallEntity> list(WallQueryVO filterVo);

    /**
     * 分页
     *
     * @param page     页
     * @param filterVo 过滤 VO
     * @return {@link Page }<{@link WallEntity }>
     */
    Page<WallEntity> paging(Page<WallEntity> page, WallQueryVO filterVo);

    /**
     * 设置与取消精选留言
     *
     * @param id id
     */
    void updateChoice(Integer id);
}
