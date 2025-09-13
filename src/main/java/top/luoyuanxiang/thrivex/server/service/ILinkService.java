package top.luoyuanxiang.thrivex.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.luoyuanxiang.thrivex.server.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.thrivex.server.vo.LinkQueryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
public interface ILinkService extends IService<Link> {

    /**
     * 网站添加
     *
     * @param link 链接
     */
    void add(Link link);

    /**
     * 列表
     *
     * @param linkQueryVO 链接查询 vo
     * @return {@link List }<{@link Link }>
     */
    List<Link> list(LinkQueryVO linkQueryVO);

    /**
     * 分页
     *
     * @param page     页
     * @param filterVo 过滤 VO
     * @return {@link Page }<{@link Link }>
     */
    Page<Link> paging(Page<Link> page, LinkQueryVO filterVo);

    /**
     * 获取网站信息
     *
     * @param url 网址
     * @return {@link Link }
     */
    Link fetchWebsiteInfo(String url) throws Exception;
}
