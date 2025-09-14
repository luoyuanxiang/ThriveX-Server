package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.entity.LinkEntity;
import top.luoyuanxiang.thrivex.server.entity.LinkTypeEntity;
import top.luoyuanxiang.thrivex.server.entity.RssEntity;
import top.luoyuanxiang.thrivex.server.service.ILinkService;
import top.luoyuanxiang.thrivex.server.service.ILinkTypeService;
import top.luoyuanxiang.thrivex.server.service.IRssService;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 订阅管理
 *
 * @author luoyuanxiang
 */
@Service
public class RssServiceImpl implements IRssService {
    @Resource
    private ILinkService linkService;
    @Resource
    private ILinkTypeService linkTypeService;


    // 线程池，用于并发获取RSS内容
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    // 类型缓存，避免重复查询数据库
    private final Map<Integer, String> typeCache = new ConcurrentHashMap<>();

    /**
     * 初始化方法，在Bean创建后自动执行
     * 预加载所有链接类型数据到内存缓存中
     */
    @PostConstruct
    public void init() {
        // 从数据库加载所有链接类型，并存入缓存
        List<LinkTypeEntity> list = linkTypeService.list();
        for (LinkTypeEntity linkTypeEntity : list) {
            typeCache.put(linkTypeEntity.getId(), linkTypeEntity.getName());
        }
    }

    @Cacheable(value = "rssCache", key = "'allFeeds'")
    @Override
    public List<RssEntity> list() {
        // 线程安全的列表，用于收集所有RSS条目
        List<RssEntity> rssList = Collections.synchronizedList(new ArrayList<>());

        // 从数据库获取所有链接
        List<LinkEntity> linkList = linkService.list();

        // 为每个有RSS地址的链接创建异步任务
        List<CompletableFuture<Void>> futures = linkList.stream()
                .filter(link -> link.getRss() != null)  // 过滤掉没有RSS地址的链接
                .map(link -> CompletableFuture.runAsync(() ->
                        processFeedWithTimeout(link, rssList), executorService))  // 异步处理每个RSS源
                .toList();

        // 等待所有异步任务完成
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // 按发布时间降序排序后返回
        return rssList.stream()
                .sorted(Comparator.comparingLong(r -> -Long.parseLong(r.getCreateTime())))
                .collect(Collectors.toList());
    }

    // 定时任务更新缓存
    @Scheduled(fixedRate = 3600000) // 每小时更新一次
    @CacheEvict(value = "rssCache", key = "'allFeeds'")
    public void evictCache() {
    }

    /**
     * 处理单个RSS源，带有超时控制
     *
     * @param link    包含RSS地址的链接对象
     * @param rssList 用于收集结果的列表
     */
    private void processFeedWithTimeout(LinkEntity link, List<RssEntity> rssList) {
        try {
            HttpURLConnection connection = (HttpURLConnection)
                    new URL(link.getRss()).openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(10000);

            try (InputStream input = connection.getInputStream()) {
                SyndFeed feed = new SyndFeedInput().build(new XmlReader(input));

                // 使用Stream处理并限制数量
                List<RssEntity> limitedItems = feed.getEntries().stream()
                        .sorted(Comparator.comparing(SyndEntry::getPublishedDate).reversed())
                        .limit(5)
                        .map(data -> {
                            RssEntity rss = new RssEntity();
                            rss.setImage(link.getImage());
                            rss.setEmail(link.getEmail());
                            rss.setType(typeCache.get(link.getTypeId()));
                            rss.setAuthor(data.getAuthor());
                            rss.setTitle(data.getTitle());
                            rss.setDescription(data.getDescription() != null ?
                                    data.getDescription().getValue() : "");
                            rss.setUrl(data.getLink());
                            rss.setCreateTime(String.valueOf(data.getPublishedDate().getTime()));
                            return rss;
                        })
                        .toList();

                rssList.addAll(limitedItems);
            }
        } catch (Exception e) {
            System.err.println("解析失败: " + link.getRss());
        }
    }

    @Override
    public Page<RssEntity> paging(Integer page, Integer size) {
        Page<RssEntity> pageVo = new Page<>(page, size);
        List<RssEntity> list = list();
        // 使用工具类进行分页
        int start = (page - 1) * size;
        int end = Math.min(start + size, list.size());
        List<RssEntity> pagedRecords = list.subList(start, end);

        pageVo.setRecords(pagedRecords);
        pageVo.setTotal(list.size());
        return pageVo;
    }

    /**
     * Bean销毁前的清理方法
     * 关闭线程池，释放资源
     */
    @PreDestroy
    public void shutdown() {
        executorService.shutdown();
    }
}
