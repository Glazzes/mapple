package com.glaze.maple.core;

import java.lang.reflect.Proxy;

@SuppressWarnings(value = "unchecked")
public class Mappers {
    public static <T> T getMapper(Class<T> clazz){
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new MapleInvocationHandler()
        );
    }
}
