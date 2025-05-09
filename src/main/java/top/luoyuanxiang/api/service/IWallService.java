package top.luoyuanxiang.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.luoyuanxiang.api.entity.Wall;
import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.api.entity.WallCate;

import java.util.List;

/**
 * <p>
 * 留言墙 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
public interface IWallService extends IService<Wall> {

    /**
     * 添加
     *
     * @param wall 墙
     */
    void add(Wall wall);

    /**
     * 获取
     *
     * @param id 身份证
     * @return {@link Wall }
     */
    Wall get(Integer id);

    /**
     * 获取指定分类中所有留言
     *
     * @param cateId Cate ID
     * @param page   页
     * @return {@link Page }<{@link Wall }>
     */
    Page<Wall> getCateWallList(Integer cateId, Page<Wall> page);

    /**
     * 获取留言分类列表
     *
     * @return {@link List }<{@link WallCate }>
     */
    List<WallCate> getCateList();

    /**
     * 设置与取消精选留言
     *
     * @param id 身份证
     */
    void updateChoice(Integer id);
}
