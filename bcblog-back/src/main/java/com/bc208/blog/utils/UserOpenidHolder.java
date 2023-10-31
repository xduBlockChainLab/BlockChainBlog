package com.bc208.blog.utils;


/**
 * @author QingheLi
 */
public class UserOpenidHolder {
    private static final ThreadLocal<String> wxThreadLocal = new ThreadLocal<>();

    public static void saveOpenid(String openid){
        wxThreadLocal.set(openid);
    }

    public static String getOpenid(){
        return wxThreadLocal.get();
    }

    public static void removeUser(){
        wxThreadLocal.remove();
    }
}
