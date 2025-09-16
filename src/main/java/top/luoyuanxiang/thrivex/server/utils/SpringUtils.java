package top.luoyuanxiang.thrivex.server.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author luoyuanxiang@date 2025/09/16
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return clazz == null ? null : applicationContext.getBean(clazz);
    }
}
