package liuyuyang.net.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedOriginPatterns("http://localhost:3000/", "https://thrive-x-blog-green.vercel.app")
                .allowedMethods("*") // 支持方法
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(36000);
    }
}