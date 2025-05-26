package top.luoyuanxiang.api.utils;

import top.luoyuanxiang.api.entity.User;

/**
 * 当前线程用户信息
 *
 * @author luoyuanxiang
 */
public class ThreadUserUtil {

    private static final ThreadLocal<User> userThreadLocal = ThreadLocal.withInitial(User::new);

    private static final ThreadLocal<Boolean> isAdminThreadLocal = ThreadLocal.withInitial(() -> false);

    /**
     * 设置用户
     *
     * @param user 用户
     */
    public static void setUser(User user) {
        userThreadLocal.set(user);
    }

    /**
     * 获取用户
     *
     * @return {@link User }
     */
    public static User getUser() {
        return userThreadLocal.get();
    }

    /**
     * 删除
     */
    public static void remove() {
        userThreadLocal.remove();
    }


    public static void setIsAdmin(Boolean isAdmin) {
        isAdminThreadLocal.set(isAdmin);
    }

    public static boolean isAdmin() {
        return isAdminThreadLocal.get();
    }

    public static void removeIsAdmin() {
        isAdminThreadLocal.remove();
    }
}
