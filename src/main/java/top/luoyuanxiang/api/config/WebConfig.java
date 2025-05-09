package top.luoyuanxiang.api.config;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.luoyuanxiang.api.handler.DynamicResourceHandlerMapping;
import top.luoyuanxiang.api.interceptor.JwtTokenAdminInterceptor;
import top.luoyuanxiang.api.service.IOssService;
import top.luoyuanxiang.api.utils.OssUtils;

import java.util.Objects;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;
    @Resource
    private IOssService ossService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截指定请求
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/api/admin/**")
                .excludePathPatterns("/api/admin/user/login");
    }

    @Autowired
    public void initializeMappings(DynamicResourceHandlerMapping handlerMapping) {
        ossService.list()
                .parallelStream()
                .filter(s -> Objects.equals(s.getPlatform(), OssUtils.DEFAULT_PLATFORM))
                .forEach(handlerMapping::addMapping);
    }
}
