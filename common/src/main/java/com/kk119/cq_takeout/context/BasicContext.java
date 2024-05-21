package com.kk119.cq_takeout.context;

public class BasicContext {
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrent(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrent() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }
}
