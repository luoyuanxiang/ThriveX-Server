package liuyuyang.net.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import liuyuyang.net.common.execption.CustomException;
import liuyuyang.net.common.properties.BaiduProperties;
import liuyuyang.net.model.Baidu;
import liuyuyang.net.web.mapper.BaiduMapper;
import liuyuyang.net.web.service.BaiduService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@Transactional
public class BaiduServiceImpl implements BaiduService {
    @Resource
    private WebClient webClient;
    @Resource
    private BaiduProperties baiduProperties;
    @Resource
    private BaiduMapper baiduMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Baidu refreshAccessToken() {
        try {
            // 获取当前refresh token
            String refreshToken = getCurrentRefreshToken();

            if (!StringUtils.hasText(refreshToken)) throw new CustomException("未找到有效的refresh token");

            // 构建刷新token的URL
            String url = String.format(
                    "http://openapi.baidu.com/oauth/2.0/token?grant_type=refresh_token&refresh_token=%s&client_id=%s&client_secret=%s",
                    refreshToken,
                    baiduProperties.getClientKey(),
                    baiduProperties.getClientSecret()
            );

            log.info("开始刷新百度统计access token");

            // 调用百度API刷新token
            String response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            if (!StringUtils.hasText(response)) throw new CustomException("刷新token失败，响应为空");

            // 解析响应数据
            JsonNode jsonNode = objectMapper.readTree(response);

            // 检查是否有错误
            if (jsonNode.has("error")) throw new CustomException("刷新token失败: " + jsonNode.get("error_description").asText());

            // 提取token信息
            String newAccessToken = jsonNode.get("access_token").asText();
            String newRefreshToken = jsonNode.get("refresh_token").asText();
            int expiresIn = jsonNode.get("expires_in").asInt();

            // 计算过期时间
            LocalDateTime expiresTime = LocalDateTime.now().plusSeconds(expiresIn);

            // 保存到数据库
            Baidu baidu = new Baidu();
            baidu.setAccessToken(newAccessToken);
            baidu.setRefreshToken(newRefreshToken);
            baidu.setExpiresTime(expiresTime);
            baidu.setCreateTime(LocalDateTime.now());

            // 先删除旧记录，再插入新记录
            baiduMapper.delete(new QueryWrapper<>());
            baiduMapper.insert(baidu);

            log.info("百度统计access token刷新成功，过期时间: {}", expiresTime);
            return baidu;

        } catch (Exception e) {
            log.error("刷新百度统计access token失败", e);
            return null;
        }
    }

    // 获取当前有效的access token
    @Override
    public String getValidAccessToken() {
        // 检查是否需要刷新
        if (isTokenNeedRefresh()) {
            log.info("token即将过期，开始自动刷新");
            refreshAccessToken();
        }

        // 从数据库获取最新的token
        List<Baidu> list = baiduMapper.selectList(new QueryWrapper<Baidu>().orderByDesc("create_time").last("limit 1"));
        if (list.isEmpty()) {
            log.warn("数据库中未找到有效的access token");
            return null;
        }

        Baidu baidu = list.get(0);
        if (baidu.getExpiresTime().isBefore(LocalDateTime.now())) {
            log.warn("access token已过期，需要刷新");
            Baidu newBaidu = refreshAccessToken();
            return newBaidu != null ? newBaidu.getAccessToken() : null;
        }

        return baidu.getAccessToken();
    }

    // 检查token是否即将过期（提前1天刷新）
    @Override
    public boolean isTokenNeedRefresh() {
        List<Baidu> list = baiduMapper.selectList(new QueryWrapper<Baidu>().orderByDesc("create_time").last("limit 1"));
        if (list.isEmpty()) {
            return true; // 没有token时需要刷新
        }

        Baidu baidu = list.get(0);
        // 提前1天刷新token
        return baidu.getExpiresTime().isBefore(LocalDateTime.now().plusDays(1));
    }

    // 获取当前的refresh token
    private String getCurrentRefreshToken() {
        // 先从数据库获取
        List<Baidu> list = baiduMapper.selectList(new QueryWrapper<Baidu>().orderByDesc("create_time").last("limit 1"));
        if (!list.isEmpty() && StringUtils.hasText(list.get(0).getRefreshToken())) {
            return list.get(0).getRefreshToken();
        }

        // 如果数据库没有，使用配置文件中的初始refresh token
        return baiduProperties.getRefreshToken();
    }

    // 定时任务：每天凌晨2点检查并刷新token
    @Scheduled(cron = "0 0 2 * * ?")
    public void scheduledRefreshToken() {
        log.info("开始执行定时刷新百度统计token任务");
        if (isTokenNeedRefresh()) {
            refreshAccessToken();
        } else {
            log.info("token仍然有效，无需刷新");
        }
    }

    // 初始化token（首次运行时调用）
    public void initializeToken() {
        List<Baidu> list = baiduMapper.selectList(new QueryWrapper<>());
        if (list.isEmpty()) {
            log.info("首次运行，初始化百度统计token");
            refreshAccessToken();
        }
    }

    // 获取百度统计数据
    @Override
    public JsonNode getStatisData(String siteId, String startDate, String endDate) {
        String accessToken = getValidAccessToken();

        if (accessToken == null) throw new CustomException("无有效的access token");

        // 如果没有传入日期参数，则默认为当天
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        if (!StringUtils.hasText(startDate)) {
            startDate = today;
        }
        if (!StringUtils.hasText(endDate)) {
            endDate = today;
        }

        try {
            String url = String.format(
                    "https://openapi.baidu.com/rest/2.0/tongji/report/getData?access_token=%s&site_id=%s&start_date=%s&end_date=%s&metrics=pv_count,ip_count&method=overview/getTimeTrendRpt",
                    accessToken, siteId, startDate, endDate
            );

            String response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            if (response != null) {
                // 解析JSON字符串为JsonNode对象
                JsonNode jsonNode = objectMapper.readTree(response);
                return jsonNode;
            }
        } catch (Exception e) {
            log.error("获取百度统计时间趋势报表数据失败", e);
            return null;
        }

        return null;
    }
}