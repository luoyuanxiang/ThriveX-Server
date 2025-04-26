package liuyuyang.net.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:*", "https://*.luoyuanxiang.top", "https://luoyuanxiang.top")
                .allowedMethods("*") // 支持方法
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(36000);
    }
}