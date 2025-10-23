package com.linfeng.spring1910.utils;

public class ThreadLocalUtil {
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    public static <T> T get(){
        return (T) THREAD_LOCAL.get();
    }
    public static void set(Object o){
        THREAD_LOCAL.set(o);
    }
    public static void remove(){
        THREAD_LOCAL.remove();
    }
}
