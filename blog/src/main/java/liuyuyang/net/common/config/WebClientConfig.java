package liuyuyang.net.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Web 客户端配置
 *
 * @author luoyuanxiang
 */
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient deepSeekWebClient() {
        return WebClient.builder()
                .build();
    }
}    