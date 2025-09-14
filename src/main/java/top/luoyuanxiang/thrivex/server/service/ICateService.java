package top.luoyuanxiang.thrivex.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.luoyuanxiang.thrivex.server.entity.CateEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.thrivex.server.vo.CateArticleCount;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
public interface ICateService extends IService<CateEntity> {

    /**
     * 列表
     *
     * @param pattern 模式
     * @return {@link List }<{@link CateEntity }>
     */
    List<CateEntity> list(String pattern);

    /**
     * 构建 cate 树
     *
     * @param list  列表
     * @param lever 杠杆
     * @return {@link List }<{@link CateEntity }>
     */
    List<CateEntity> buildCateTree(List<CateEntity> list, Integer lever);

    /**
     * 分页查询分类列表
     *
     * @param page 页
     * @param size 大小
     * @return {@link Page }<{@link CateEntity }>
     */
    Page<CateEntity> paging(Integer page, Integer size);

    /**
     * 获取每个分类中的文章数量
     *
     * @return {@link List }<{@link CateArticleCount }>
     */
    List<CateArticleCount> cateArticleCount();
}
