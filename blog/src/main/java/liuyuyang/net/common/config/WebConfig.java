package liuyuyang.net.common.config;

import liuyuyang.net.common.handler.DynamicResourceHandlerMapping;
import liuyuyang.net.common.interceptor.JwtTokenAdminInterceptor;
import liuyuyang.net.common.utils.OssUtils;
import liuyuyang.net.web.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;
    @Resource
    private OssService ossService;

    private static final Set<String> EXCLUDED_PATHS = new HashSet<>(Arrays.asList(
            "/",
            "/doc.html",
            "/swagger-resources",
            "/webjars",
            "/v2/api-docs",
            "/swagger-ui.html"
    ));

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 指定统一API访问前缀
        configurer.addPathPrefix("/api", c -> {
            RequestMapping requestMapping = c.getAnnotation(RequestMapping.class);

            if (requestMapping != null && requestMapping.value().length > 0) {
                String path = requestMapping.value()[0];
                return !EXCLUDED_PATHS.contains(path);
            }
            return true;
        });
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截指定请求
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login");
    }

    @Autowired
    public void initializeMappings(DynamicResourceHandlerMapping handlerMapping) {
        ossService.list().parallelStream().filter(s -> Objects.equals(s.getPlatform(), OssUtils.DEFAULT_PLATFORM))
                .forEach(mapping ->
                handlerMapping.addMapping(mapping.getPathPatterns(), mapping.getStoragePath()));
    }
}
