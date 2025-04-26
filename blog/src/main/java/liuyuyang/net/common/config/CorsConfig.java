package liuyuyang.net.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3001", "http://localhost:5174", "https://luoyuanxiang.top", "https://admin.luoyuanxiang.top", "https://blog.luoyuanxiang.top")
                .allowedMethods("*") // 支持方法
                .allowedHeaders("*")
                .maxAge(36000);
    }
}