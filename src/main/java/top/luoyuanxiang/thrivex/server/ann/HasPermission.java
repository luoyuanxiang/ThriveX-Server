package top.luoyuanxiang.thrivex.server.ann;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限判断
 *
 * @author luoyuanxiang
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("@pms.hasPermission('{value}'.split(','))")
public @interface HasPermission {

    /**
     * 权限字符串
     *
     * @return {@link String[] }
     */
    String[] value();
}
