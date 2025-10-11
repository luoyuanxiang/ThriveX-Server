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

    @Getter
    @Setter
    private Set<String> permitAllUrls = new LinkedHashSet<>();


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof RequestMappingHandlerMapping handlerMapping) {
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
            for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
                RequestMappingInfo info = entry.getKey();
                HandlerMethod method = entry.getValue();

                // 判断类或方法是否有 @NoAuth 注解
                boolean hasNoAuth = AnnotationUtils.findAnnotation(method.getBeanType(), NoAuth.class) != null
                        || AnnotationUtils.findAnnotation(method.getMethod(), NoAuth.class) != null;

                if (hasNoAuth) {
                    // 直接获取路径，无需替换 {id}（Spring Security 支持 {*} 匹配）
                    permitAllUrls.addAll(info.getPathPatternsCondition().getPatternValues());
                }
            }
        }
        return bean;
    }
}
