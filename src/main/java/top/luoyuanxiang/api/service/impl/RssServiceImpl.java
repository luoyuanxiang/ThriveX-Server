package top.luoyuanxiang.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.api.entity.Link;
import top.luoyuanxiang.api.entity.LinkType;
import top.luoyuanxiang.api.entity.Rss;
import top.luoyuanxiang.api.mapper.LinkMapper;
import top.luoyuanxiang.api.mapper.LinkTypeMapper;
import top.luoyuanxiang.api.service.IRssService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * RSS
 *
 * @author luoyuanxiang
 */
@Service
public class RssServiceImpl implements IRssService {

    @Resource
    private LinkMapper linkMapper;
    @Resource
    private LinkTypeMapper linkTypeMapper;

    @Override
    public List<Rss> list() {
        List<Rss> rssList = new ArrayList<>();

        // 目标网站列表
        List<Link> linkList = linkMapper.selectList(null);
        List<String> feedUrls = linkList.stream().map(Link::getRss).toList();

        for (String feedUrl : feedUrls) {
            if (feedUrl == null) continue;

            QueryWrapper<Link> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("rss", feedUrl);
            Link link = linkMapper.selectOne(queryWrapper);

            try {
                // 创建一个URL对象
                URL url = new URL(feedUrl);
                // 为URL创建一个XmlReader
                XmlReader xmlReader = new XmlReader(url);
                // 创建一个SyndFeedInput对象
                SyndFeedInput input = new SyndFeedInput();
                // 从XmlReader构建SyndFeed对象
                SyndFeed feed = input.build(xmlReader);

                // 遍历提要中的条目
                for (SyndEntry data : feed.getEntries()) {
                    LinkType lt = linkTypeMapper.selectById(link.getTypeId());

                    Rss rss = getRss(data, link, lt);
                    rssList.add(rss);
                }
            } catch (Exception e) {
                System.err.println("解析失败: " + feedUrl);
            }
        }

        // 对rssList进行排序，根据createTime降序排序
        rssList.sort((o1, o2) -> {
            long time1 = Long.parseLong(o1.getCreateTime());
            long time2 = Long.parseLong(o2.getCreateTime());
            // 降序排序
            return Long.compare(time2, time1);
        });

        return rssList;
    }

    /**
     * 获取 RSS
     *
     * @param data 数据
     * @param link 链接
     * @param lt   中尉
     * @return {@link Rss }
     */
    private Rss getRss(SyndEntry data, Link link, LinkType lt) {
        Rss rss = new Rss();
        rss.setImage(link.getImage());
        rss.setEmail(link.getEmail());
        rss.setType(lt.getName());
        rss.setAuthor(data.getAuthor());
        rss.setTitle(data.getTitle());
        if (data.getDescription() != null) {
            rss.setDescription(data.getDescription().getValue());
        } else {
            rss.setDescription("");
        }

        rss.setUrl(data.getLink());
        rss.setCreateTime(String.valueOf(data.getPublishedDate().getTime()));
        return rss;
    }
}
