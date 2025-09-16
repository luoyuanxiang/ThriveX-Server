package top.luoyuanxiang.thrivex.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨越配置
 *
 * @author luoyuanxiang
 */
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有路径都允许跨域
                .allowedOriginPatterns("*") // 允许所有域名
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的请求方法
                .allowedHeaders("*") // 允许所有请求头
                .allowCredentials(true) // 允许携带认证信息
                .maxAge(3600); // 预检请求的有效期
    }
}
