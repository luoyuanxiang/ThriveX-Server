package top.luoyuanxiang.thrivex.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;
import top.luoyuanxiang.thrivex.server.entity.EnvConfigEntity;
import top.luoyuanxiang.thrivex.server.service.IEnvConfigService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

/**
 * Thrivex 服务器应用程序
 *
 * @author luoyuanxiang
 */
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class ThrivexServerApplication {

    @Resource
    private IEnvConfigService envConfigService;
    @Resource
    private WebClient webClient;

	public static void main(String[] args) {
		SpringApplication.run(ThrivexServerApplication.class, args);
	}

    /**
     * 更新百度统计
     *
     */
    @Scheduled(fixedRate = 241920000)
    public void updBaiduStatistics() throws Exception {
        EnvConfigEntity envConfig = envConfigService.getByName("baidu_statis");
        Map<String, Object> value = envConfig.getValue();
        // 构建URL
        String urlBuilder = "http://openapi.baidu.com/oauth/2.0/token" +
                "?grant_type=refresh_token" +
                "&refresh_token=" + value.get("refresh_token") +
                "&client_id=" + value.get("client_id") +
                "&client_secret=" + value.get("client_secret");
        // 发起请求
        String response = webClient.get()
                .uri(urlBuilder)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        if (Objects.nonNull(response)) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map map = objectMapper.readValue(response, Map.class);
            value.putAll(map);
            value.put("update_time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            envConfigService.updateJsonValue(envConfig.getId(), value);
        }
    }

}
