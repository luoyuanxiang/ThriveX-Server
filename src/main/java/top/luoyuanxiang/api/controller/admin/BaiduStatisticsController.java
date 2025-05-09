package top.luoyuanxiang.api.controller.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.luoyuanxiang.api.entity.BaiduStatistics;
import top.luoyuanxiang.api.service.IBaiduStatisticsService;
import top.luoyuanxiang.api.utils.Result;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * 百度统计
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/admin/baidu")
public class BaiduStatisticsController {

    @Resource
    private IBaiduStatisticsService baiduStatisticsService;

    /**
     * 获取
     *
     * @return {@link Result }<{@link BaiduStatistics }>
     */
    @GetMapping("/statistics")
    public Result<BaiduStatistics> get() {
        BaiduStatistics one = baiduStatisticsService.getById(1);
        return Result.success(one);
    }

    /**
     * 更新配置
     *
     * @return {@link Result }<{@link BaiduStatistics }>
     */
    @PutMapping("/statistics")
    public Result<?> edit(@RequestBody BaiduStatistics baiduStatistics) {
        baiduStatisticsService.updateById(baiduStatistics);
        return Result.success();
    }

    /**
     * 刷新
     */
    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void refresh() {
        Result<BaiduStatistics> baiduStatisticsResult = get();
        BaiduStatistics data = baiduStatisticsResult.getData();
        // 当前时间 - 更新时间 > 7200s 则更新token
        LocalDateTime now = LocalDateTime.now();
        long between = ChronoUnit.SECONDS.between(data.getUpdateTime(), now);
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://openapi.baidu.com/oauth/2.0/token?grant_type=refresh_token&refresh_token={REFRESH_TOKEN}&client_id={CLIENT_ID}&client_secret={CLIENT_SECRET}";
        url = url.replace("{REFRESH_TOKEN}", data.getRefreshToken())
                .replace("{CLIENT_ID}", data.getAppKey())
                .replace("{CLIENT_SECRET}", data.getSecretKey());
        if (between > 60 * 60 * 2) {
            // 更新token
            BaiduJson baiduJson = restTemplate.getForObject(url, BaiduJson.class);
            if (Objects.nonNull(baiduJson)) {
                data.setAccessToken(baiduJson.getAccessToken());
                data.setRefreshToken(baiduJson.getRefreshToken());
                data.setUpdateTime(now);
                baiduStatisticsService.updateById(data);
            }
        }
    }

    @Getter
    @Setter
    public static class BaiduJson implements Serializable {
        @JsonProperty("expires_in")
        private Integer expiresIn;
        @JsonProperty("refresh_token")
        private String refreshToken;
        @JsonProperty("access_token")
        private String accessToken;
        @JsonProperty("session_secret")
        private String sessionSecret;
        @JsonProperty("session_key")
        private String sessionKey;
        @JsonProperty("scope")
        private String scope;
    }

}
