package liuyuyang.net.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns(
                        "http://localhost:[*]",
                        "https://luoyuanxiang.top",
                        "https://*.luoyuanxiang.top"
                )
                // 允许所有方法（GET、POST等）
                .allowedMethods("*")
                // 允许所有请求头
                .allowedHeaders("*")
                // 允许携带凭证（如cookies）
                .allowCredentials(true)
                // 预检请求缓存时间
                .maxAge(3600);
    }
}