package top.luoyuanxiang.api.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import top.luoyuanxiang.api.entity.Oss;
import top.luoyuanxiang.api.utils.OssUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义映射
 *
 * @author luoyuanxiang
 */
@Slf4j
@Component
public class DynamicResourceHandlerMapping extends AbstractHandlerMapping {

    private final Map<String, ResourceHttpRequestHandler> handlerMap = new ConcurrentHashMap<>();
    private final PathMatcher pathMatcher = new AntPathMatcher();
    @Override
    protected Object getHandlerInternal(HttpServletRequest request) {
        String lookupPath = getUrlPathHelper().getLookupPathForRequest(request);
        for (Map.Entry<String, ResourceHttpRequestHandler> entry : handlerMap.entrySet()) {
            String pattern = entry.getKey();
            if (pathMatcher.match(pattern, lookupPath)) {
                // 计算路径属性
                String pathWithinMapping = pathMatcher.extractPathWithinPattern(pattern, lookupPath);
                // 设置关键请求属性
                request.setAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, pathWithinMapping);
                request.setAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE, pattern);

                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * 添加映射
     *
     * @param oss 开源软件
     */
    public void addMapping(Oss oss) {
        if (oss == null || !Objects.equals(OssUtils.DEFAULT_PLATFORM, oss.getPlatform())) {
            return;
        }
        String pathPatterns = oss.getPathPatterns();
        String storagePath = oss.getStoragePath();
        String basePath = oss.getBasePath();
        String resourcePath = storagePath + basePath;
        ResourceHttpRequestHandler handler = new ResourceHttpRequestHandler();
        handler.setLocations(Collections.singletonList(new FileSystemResource(resourcePath)));
        handler.setResourceResolvers(Collections.singletonList(new PathResourceResolver()));
        try {
            handler.afterPropertiesSet();
        } catch (Exception e) {
            throw new RuntimeException("初始化资源处理器失败", e);
        }
        handlerMap.put(pathPatterns, handler);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
