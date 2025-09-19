package top.luoyuanxiang.thrivex.server.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.luoyuanxiang.thrivex.server.entity.*;
import top.luoyuanxiang.thrivex.server.mapper.CommentMapper;
import top.luoyuanxiang.thrivex.server.service.*;
import top.luoyuanxiang.thrivex.server.utils.AmapLocationUtil;
import top.luoyuanxiang.thrivex.server.vo.CommentQueryVO;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentEntity> implements ICommentService {

    @Resource
    private IWebConfigService configService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private IEnvConfigService envConfigService;
    @Resource
    private IArticleService articleService;
    @Resource
    private IEmailService emailService;
    @Resource
    private IUserService userService;


    @Override
    @Transactional
    public void add(CommentEntity comment) {

        String agent = request.getHeader("User-Agent");
        //解析agent字符串
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        //获取浏览器对象
        Browser browser = userAgent.getBrowser();
        //获取操作系统对象
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        comment.setUserAgent(agent);
        comment.setOs(operatingSystem.getName());
        comment.setBrowser(browser.getName());
        comment.setIp(getRealIpAddress());
        EnvConfigEntity envConfig = envConfigService.getByName("gaode_coordinate");
        Map<String, Object> value = envConfig.getValue();
        Map<String, String> map = AmapLocationUtil.getLocationByIp(getRealIpAddress(), value.get("key").toString());
        comment.setProvince(map.get("province"));
        comment.setCity(map.get("city"));
        comment.insert();

        // 文章标题
        String title = articleService.getById(comment.getArticleId()).getTitle();
        WebConfigEntity web = configService.getByName("web");
        Map<String, Object> webValue = web.getValue();
        // 获取url
        String url = (String) webValue.get("url");
        String path = String.format("%s/article/%d", url, comment.getArticleId());
        Map<String, Object> params = new HashMap<>();
        params.put("postTitle", title);
        params.put("postUrl", path);
        params.put("postOwner", webValue.get("title"));
        params.put("commenter", comment.getName());
        params.put("content", comment.getContent());
        UserEntity user = userService.getById(1);
        emailService.sendDualFormatEmail(StrUtil.isNotBlank(user.getEmail()) ? user.getEmail() : "admin@luoyuanxiang.top", "我的文章收到新评论", params);
//        CompletableFuture.runAsync(() -> emailService.sendDualFormatEmail("admin@luoyuanxiang.top", "我的文章收到新评论", params));

        // 判断是否还有上一条评论
        CommentEntity prev_comment;
        if (comment.getCommentId() != 0) {
            prev_comment = getById(comment.getCommentId());
            params.put("replier", comment.getName());
            params.put("commentSubjectUrl", path);
            params.put("commentSubjectTitle", title);
            params.put("isQuoteReply", false);
            params.put("commentContent", prev_comment.getCommentId());
            if (StrUtil.isNotBlank(prev_comment.getEmail())) {
                emailService.sendDualFormatEmail(prev_comment.getEmail(), "有人回复了我", params);
//                CompletableFuture.runAsync(() -> emailService.sendDualFormatEmail(prev_comment.getEmail(), "有人回复了我", params));
            }
        }
    }

    @Override
    public List<CommentEntity> list(CommentQueryVO commentQueryVO) {
        QueryWrapper<CommentEntity> wrapper = buildQuery(commentQueryVO);
        List<CommentEntity> list = list(wrapper);
        buildCommentArticleTitle(list);
        if (Objects.equals(commentQueryVO.getPattern(), "list")) return list;
        return buildCommentTree(list, 0);
    }

    @Override
    public Page<CommentEntity> paging(Page<CommentEntity> page, CommentQueryVO commentQueryVO) {
        QueryWrapper<CommentEntity> wrapper = buildQuery(commentQueryVO);
        page(page, wrapper);
        buildCommentArticleTitle(page.getRecords());
        return page;
    }

    /**
     * 构建评论文章标题
     *
     * @param list 列表
     */
    private void buildCommentArticleTitle(List<CommentEntity> list) {
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        List<Integer> ids = list.parallelStream().map(CommentEntity::getArticleId).toList();
        if (CollectionUtil.isEmpty(ids)) {
            return;
        }
        List<ArticleEntity> articleEntityList = articleService.listByIds(ids);
        list.parallelStream().forEach(commentEntity -> commentEntity.setArticleTitle(articleEntityList
                .stream().filter(articleEntity -> articleEntity.getId().equals(commentEntity.getArticleId()))
                        .map(ArticleEntity::getTitle)
                .findFirst().orElse("")));
    }

    /**
     * 构建查询
     *
     * @param commentQueryVO 评论查询 vo
     * @return {@link QueryWrapper }<{@link CommentEntity }>
     */
    private QueryWrapper<CommentEntity> buildQuery(CommentQueryVO commentQueryVO) {
        return commentQueryVO.buildQueryWrapper("name")
                .eq(commentQueryVO.getId() != null, "id", commentQueryVO.getId())
                .eq(commentQueryVO.getStatus() != null, "audit_status", commentQueryVO.getStatus())
                .like(StrUtil.isNotBlank(commentQueryVO.getContent()), "content", commentQueryVO.getContent())
                .eq(commentQueryVO.getArticleId() != null, "article_id", commentQueryVO.getArticleId());
    }


    /**
     * 递归构建评论列表
     *
     * @param list 列表
     * @param cid 父级id
     * @return {@link List }<{@link CommentEntity }>
     */
    private List<CommentEntity> buildCommentTree(List<CommentEntity> list, Integer cid) {
        List<CommentEntity> children = new ArrayList<>();

        for (CommentEntity data : list) {
            if (data.getCommentId().equals(cid)) {
                data.setChildren(buildCommentTree(list, data.getId()));
                children.add(data);
            }
        }
        return children;
    }

    /**
     * 获取用户真实IP地址
     * 考虑代理、负载均衡等场景
     */
    private String getRealIpAddress() {
        // 常用的代理IP请求头
        String[] headers = {
                "x-forwarded-for",    // 一般用于记录代理信息
                "Proxy-Client-IP",    // Apache代理常用
                "WL-Proxy-Client-IP", // WebLogic代理常用
                "HTTP_CLIENT_IP",     // 部分代理使用
                "HTTP_X_FORWARDED_FOR" // 部分代理使用
        };

        String ipAddress = null;
        for (String header : headers) {
            ipAddress = request.getHeader(header);
            if (isValidIp(ipAddress)) {
                break;
            }
        }

        // 如果以上请求头都没有获取到，则使用remoteAddr
        if (!isValidIp(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            // 处理本地地址情况
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                // 获取本机真实IP
                try {
                    InetAddress inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    // 无法获取本机IP时保持默认值
                }
            }
        }

        // 对于通过多个代理的情况，取第一个有效IP
        if (ipAddress != null && ipAddress.contains(",")) {
            String[] ips = ipAddress.split(",");
            for (String ip : ips) {
                if (isValidIp(ip.trim())) {
                    return ip.trim();
                }
            }
        }

        return ipAddress;
    }

    /**
     * 验证IP地址是否有效
     */
    private boolean isValidIp(String ip) {
        return ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip);
    }
}
