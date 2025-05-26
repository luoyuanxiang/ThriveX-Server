package top.luoyuanxiang.api.service;

import top.luoyuanxiang.api.entity.Cate;
import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.api.vo.CateArticleCountVO;

import java.util.List;

/**
 * <p>
 * 分类 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
public interface ICateService extends IService<Cate> {

    /**
     * 构建 Cate 树
     *
     * @param list  列表
     * @param lever 杠杆
     * @return {@link List }<{@link Cate }>
     */
    List<Cate> buildCateTree(List<Cate> list, Integer lever);

    /**
     * 删除分类
     *
     * @param id 身份证
     */
    void del(Integer id);

    /**
     * 判断是否存在二级分类
     *
     * @param id 身份证
     * @return {@link Boolean }
     */
    Boolean isExistTwoCate(Integer id);

    /**
     * 判断该分类中是否有文章
     *
     * @param id 身份证
     * @return {@link Boolean }
     */
    Boolean isCateArticleCount(Integer id);

    /**
     * 获取
     *
     * @param id 身份证
     * @return {@link Cate }
     */
    Cate get(Integer id);

    /**
     * 列表
     *
     * @param pattern 模式
     * @return {@link List }<{@link Cate }>
     */
    List<Cate> list(String pattern);

    /**
     * 获取每个分类中的文章数量
     *
     * @return {@link List }<{@link CateArticleCountVO }>
     */
    List<CateArticleCountVO> cateArticleCount();

}
