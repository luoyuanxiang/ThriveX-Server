package top.luoyuanxiang.thrivex.server.security.handle;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import top.luoyuanxiang.thrivex.server.ann.NoAuth;

import java.util.*;

/**
 * 无线认证token配置
 *
 * @author luoyuanxiang
 */
@Service
public class RequestMappingCollector implements BeanPostProcessor {

    private static final String PATTERN = "\\{(.*?)}";

    public static final String ASTERISK = "*";

    @Getter
    @Setter
    private List<String> permitAllUrls = new ArrayList<>();


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RequestMappingHandlerMapping handlerMapping) {
            Map<RequestMappingInfo, HandlerMethod> map = handlerMapping.getHandlerMethods();
            map.keySet().forEach(x -> {
                HandlerMethod handlerMethod = map.get(x);

                // 获取方法上边的注解 替代path variable 为 *
                NoAuth method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), NoAuth.class);
                Optional.ofNullable(method).ifPresent(inner -> Objects.requireNonNull(x.getPathPatternsCondition())
                        .getPatternValues().forEach(url -> permitAllUrls.add(url.replaceAll(PATTERN, ASTERISK))));

                // 获取类上边的注解, 替代path variable 为 *
                NoAuth controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), NoAuth.class);
                Optional.ofNullable(controller).ifPresent(inner -> Objects.requireNonNull(x.getPathPatternsCondition())
                        .getPatternValues().forEach(url -> permitAllUrls.add(url.replaceAll(PATTERN, ASTERISK))));
            });
        }
        return bean;
    }
}
