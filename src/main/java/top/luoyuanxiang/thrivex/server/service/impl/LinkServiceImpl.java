package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.luoyuanxiang.thrivex.server.entity.Link;
import top.luoyuanxiang.thrivex.server.entity.LinkType;
import top.luoyuanxiang.thrivex.server.mapper.LinkMapper;
import top.luoyuanxiang.thrivex.server.service.ILinkService;
import top.luoyuanxiang.thrivex.server.service.ILinkTypeService;
import top.luoyuanxiang.thrivex.server.vo.LinkQueryVO;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements ILinkService {

    @Resource
    private ILinkTypeService linkTypeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Link link) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 标准化URL格式（移除尾部斜杠，统一协议等）
        String normalizedUrl = normalizeUrl(link.getUrl());
        link.setUrl(normalizedUrl);
        long count = lambdaQuery()
                .eq(Link::getUrl, normalizedUrl)
                .count();
        if (count > 0) throw new RuntimeException("该网站已存在");
        // 前端用户手动提交
        if (Objects.isNull(authentication)) {
            // 添加之前先判断所选的网站类型是否为当前用户可选的
            LinkType linkType = linkTypeService.getById(link.getTypeId());
            Integer isAdmin = linkType.getIsAdmin();
            if (isAdmin == 1) throw new RuntimeException("该类型需要管理员权限才能添加");
            link.insert();
            // 邮件提醒
//            emailUtils.send(null, "您有新的友联等待审核", link.toString());
            return;
        }
        // 如果没有设置 order 则放在最后
        if (link.getOrder() == null) {
            // 查询当前类型下的网站数量
            List<Link> links = lambdaQuery()
                    .eq(Link::getTypeId, link.getTypeId())
                    .list();
            link.setOrder(links.size() + 1);
        }
        // 判断权限
        // 如果是超级管理员在添加时候不需要审核，直接显示
        link.setAuditStatus(1);
        link.insert();
    }

    @Override
    public List<Link> list(LinkQueryVO linkQueryVO) {
        return baseMapper.list(linkQueryVO);
    }

    @Override
    public Page<Link> paging(Page<Link> page, LinkQueryVO filterVo) {
        return baseMapper.paging(page, filterVo);
    }

    @Override
    public Link fetchWebsiteInfo(String url) throws Exception {
        Link link = new Link();
        url = normalizeUrl(url);
        link.setUrl(url);
        // 使用Jsoup连接并解析网页
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .timeout(10000)
                .followRedirects(true)
                .get();
        // 获取标题
        String title = document.title();
        link.setTitle(title);
        // 获取描述
        Element metaDescription = document.select("meta[name=description]").first();
        if (metaDescription != null) {
            link.setDescription(metaDescription.attr("content"));
        }

        // 获取域名
        URL urlObj = new URL(url);
        // 获取favicon
        String faviconUrl = getFaviconUrl(document, urlObj);
        link.setImage(faviconUrl);

        return link;
    }

    /**
     * 获取网站图标 URL
     *
     * @param document 公文
     * @param baseUrl  基本网址
     * @return {@link String }
     */
    private String getFaviconUrl(Document document, URL baseUrl) {
        // 尝试从link标签获取favicon
        Element faviconLink = document.select("link[rel~=icon]").first();
        if (faviconLink != null) {
            String href = faviconLink.attr("href");
            return resolveUrl(baseUrl, href);
        }

        // 如果没有找到，尝试默认位置
        return baseUrl.getProtocol() + "://" + baseUrl.getHost() + "/favicon.ico";
    }

    private String resolveUrl(URL baseUrl, String href) {
        try {
            // 如果是绝对URL，直接返回
            if (href.startsWith("http://") || href.startsWith("https://")) {
                return href;
            }

            // 相对URL，使用基础URL解析
            return new URL(baseUrl, href).toString();
        } catch (MalformedURLException e) {
            return href; // 解析失败时返回原始值
        }
    }

    /**
     * 标准化URL格式
     * 统一处理协议、域名和路径格式
     */
    private String normalizeUrl(String url) {
        try {
            // 如果没有协议，默认添加http://
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }

            URL urlObj = new URL(url);
            String protocol = urlObj.getProtocol();
            String host = urlObj.getHost();
            int port = urlObj.getPort();
            String path = urlObj.getPath();

            // 构建标准化URL
            StringBuilder normalized = new StringBuilder();
            normalized.append(protocol).append("://").append(host);

            // 处理端口（使用默认端口时不显示）
            if (port != -1 &&
                    !((protocol.equals("http") && port == 80) ||
                            (protocol.equals("https") && port == 443))) {
                normalized.append(":").append(port);
            }

            // 处理路径（移除尾部斜杠）
            if (path != null && !path.isEmpty()) {
                normalized.append(path);
                if (path.endsWith("/")) {
                    normalized.setLength(normalized.length() - 1);
                }
            }

            return normalized.toString();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("无效的URL格式: " + url, e);
        }
    }
}
