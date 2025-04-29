package liuyuyang.net.web.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import liuyuyang.net.common.utils.Result;
import liuyuyang.net.model.BaiduStatistics;
import liuyuyang.net.web.service.BaiduStatisticsService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * 百度统计
 *
 * @author luoyuanxiang
 */
@RestController
@RequestMapping("/baidu/statistics")
public class BaiduStatisticsController {

    @Resource
    private BaiduStatisticsService baiduStatisticsService;

    /**
     * 获取
     *
     * @return {@link Result }<{@link BaiduStatistics }>
     */
    @GetMapping
    public Result<BaiduStatistics> get() {
        BaiduStatistics one = baiduStatisticsService.getById(1);
        return Result.success(one);
    }

    /**
     * 获取
     *
     * @return {@link Result }<{@link BaiduStatistics }>
     */
    @PutMapping
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
