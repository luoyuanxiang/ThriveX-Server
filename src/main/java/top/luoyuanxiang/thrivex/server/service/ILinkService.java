package top.luoyuanxiang.thrivex.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.thrivex.server.entity.LinkEntity;
import top.luoyuanxiang.thrivex.server.vo.LinkQueryVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
public interface ILinkService extends IService<LinkEntity> {

    /**
     * 网站添加
     *
     * @param linkEntity 链接
     */
    void add(LinkEntity linkEntity);

    /**
     * 列表
     *
     * @param linkQueryVO 链接查询 vo
     * @return {@link List }<{@link LinkEntity }>
     */
    List<LinkEntity> list(LinkQueryVO linkQueryVO);

    /**
     * 分页
     *
     * @param page     页
     * @param filterVo 过滤 VO
     * @return {@link Page }<{@link LinkEntity }>
     */
    Page<LinkEntity> paging(Page<LinkEntity> page, LinkQueryVO filterVo);

    /**
     * 获取网站信息
     *
     * @param url 网址
     * @return {@link LinkEntity }
     */
    LinkEntity fetchWebsiteInfo(String url) throws Exception;

    /**
     * 审核指定网站
     *
     * @param data 数据
     * @return boolean
     */
    boolean auditWeb(LinkEntity data);
}
