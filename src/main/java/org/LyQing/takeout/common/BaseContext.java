package org.LyQing.takeout.common;

/**
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
 *
 * @author yjxx_2022
 * @date 2023/05/20
 */
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置当前id
     *
     * @param id id
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * 得到当前id
     *
     * @return {@link Long}
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }

}
