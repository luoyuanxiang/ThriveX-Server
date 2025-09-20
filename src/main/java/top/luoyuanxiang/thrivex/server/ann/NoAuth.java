package top.luoyuanxiang.thrivex.server.ann;

import java.lang.annotation.*;

/**
 * 无需认证token注解
 *
 * @author luoyuanxiang
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface NoAuth {
}
