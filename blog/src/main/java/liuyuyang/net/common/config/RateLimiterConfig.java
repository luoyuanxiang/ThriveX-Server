package liuyuyang.net.common.config;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 限流配置
 *
 * @author luoyuanxiang
 */
@Configuration
public class RateLimiterConfig {

    @Bean
    public RateLimiter rateLimiter() {
        // 每秒允许的请求数
        return RateLimiter.create(20.0);
    }
}
