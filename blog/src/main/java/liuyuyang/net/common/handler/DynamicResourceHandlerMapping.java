package liuyuyang.net.common.handler;

import org.springframework.core.Ordered;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义映射
 *
 * @author luoyuanxiang
 */
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
     * @param urlPattern   URL 模式
     * @param resourcePath 资源路径
     */
    public void addMapping(String urlPattern, String resourcePath) {
        resourcePath = resourcePath + "/" + urlPattern;
        resourcePath = resourcePath.replaceAll("\\\\", "/");
        resourcePath = resourcePath.replaceAll("//", "/");
        urlPattern = "/static/" + urlPattern;
        if (urlPattern.endsWith("/") && !urlPattern.endsWith("**")) {
            urlPattern = urlPattern + "**";
        }
        urlPattern = urlPattern.replaceAll("//", "/");
        ResourceHttpRequestHandler handler = new ResourceHttpRequestHandler();
        handler.setLocations(Collections.singletonList(new FileSystemResource(resourcePath)));
        handler.setResourceResolvers(Collections.singletonList(new PathResourceResolver()));
        try {
            handler.afterPropertiesSet();
        } catch (Exception e) {
            throw new RuntimeException("初始化资源处理器失败", e);
        }
        handlerMap.put(urlPattern, handler);
    }

    /**
     * 删除映射
     *
     * @param urlPattern URL 模式
     */
    public void removeMapping(String urlPattern) {
        handlerMap.remove(urlPattern);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
