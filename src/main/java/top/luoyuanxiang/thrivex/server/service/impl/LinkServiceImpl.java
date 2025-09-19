package top.luoyuanxiang.thrivex.server.service.impl;

import cn.hutool.core.util.StrUtil;
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
import top.luoyuanxiang.thrivex.server.entity.LinkEntity;
import top.luoyuanxiang.thrivex.server.entity.LinkTypeEntity;
import top.luoyuanxiang.thrivex.server.entity.UserEntity;
import top.luoyuanxiang.thrivex.server.entity.WebConfigEntity;
import top.luoyuanxiang.thrivex.server.mapper.LinkMapper;
import top.luoyuanxiang.thrivex.server.service.*;
import top.luoyuanxiang.thrivex.server.vo.LinkQueryVO;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, LinkEntity> implements ILinkService {

    @Resource
    private ILinkTypeService linkTypeService;
    @Resource
    private IEmailService emailService;
    @Resource
    private IUserService userService;
    @Resource
    private IWebConfigService webConfigService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(LinkEntity linkEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 标准化URL格式（移除尾部斜杠，统一协议等）
        String normalizedUrl = normalizeUrl(linkEntity.getUrl());
        linkEntity.setUrl(normalizedUrl);
        long count = lambdaQuery()
                .eq(LinkEntity::getUrl, normalizedUrl)
                .count();
        if (count > 0) throw new RuntimeException("该网站已存在");
        // 前端用户手动提交
        if (Objects.isNull(authentication)) {
            // 添加之前先判断所选的网站类型是否为当前用户可选的
            LinkTypeEntity linkTypeEntity = linkTypeService.getById(linkEntity.getTypeId());
            Integer isAdmin = linkTypeEntity.getIsAdmin();
            if (isAdmin == 1) throw new RuntimeException("该类型需要管理员权限才能添加");
            linkEntity.insert();
            Map<String, Object> variables = new HashMap<>();
            variables.put("displayName", linkEntity.getTitle());
            // 邮件提醒
            if (StrUtil.isNotBlank(linkEntity.getEmail())) {
                emailService.sendDualFormatEmail(linkEntity.getEmail(), "友链自助提交审核通知", variables);
            }
            UserEntity user = userService.getById(1);
            WebConfigEntity web = webConfigService.getByName("web");
            Object o = web.getValue().get("url");
            variables.put("url", linkEntity.getUrl());
            variables.put("description", linkEntity.getDescription());
            variables.put("logo", linkEntity.getImage());
            variables.put("email", linkEntity.getEmail());
            variables.put("reviewUrl", o);
            emailService.sendDualFormatEmail(StrUtil.isNotBlank(user.getEmail()) ? user.getEmail() : "admin@luoyuanxiang.top", "友链自助提交通知管理员", variables);
            return;
        }
        // 如果没有设置 order 则放在最后
        if (linkEntity.getOrder() == null) {
            // 查询当前类型下的网站数量
            List<LinkEntity> linkEntities = lambdaQuery()
                    .eq(LinkEntity::getTypeId, linkEntity.getTypeId())
                    .list();
            linkEntity.setOrder(linkEntities.size() + 1);
        }
        // 判断权限
        // 如果是超级管理员在添加时候不需要审核，直接显示
        linkEntity.setAuditStatus(1);
        linkEntity.insert();
    }

    @Override
    public List<LinkEntity> list(LinkQueryVO linkQueryVO) {
        return baseMapper.list(linkQueryVO);
    }

    @Override
    public Page<LinkEntity> paging(Page<LinkEntity> page, LinkQueryVO filterVo) {
        return baseMapper.paging(page, filterVo);
    }

    @Override
    public LinkEntity fetchWebsiteInfo(String url) throws Exception {
        LinkEntity linkEntity = new LinkEntity();
        url = normalizeUrl(url);
        linkEntity.setUrl(url);
        // 使用Jsoup连接并解析网页
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .timeout(10000)
                .followRedirects(true)
                .get();
        // 获取标题
        String title = document.title();
        linkEntity.setTitle(title);
        // 获取描述
        Element metaDescription = document.select("meta[name=description]").first();
        if (metaDescription != null) {
            linkEntity.setDescription(metaDescription.attr("content"));
        }

        // 获取域名
        URL urlObj = new URL(url);
        // 获取favicon
        String faviconUrl = getFaviconUrl(document, urlObj);
        linkEntity.setImage(faviconUrl);

        return linkEntity;
    }

    @Override
    public boolean auditWeb(LinkEntity data) {
        data.setAuditStatus(1);
        data.updateById();
        if (StrUtil.isNotBlank(data.getEmail())) {
            Map<String, Object> variables = new HashMap<>();
            variables.put("displayName", data.getTitle());
            variables.put("review", true);
            emailService.sendDualFormatEmail(data.getEmail(), "友链自助提交成功通知", variables);
        }
        return true;
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
