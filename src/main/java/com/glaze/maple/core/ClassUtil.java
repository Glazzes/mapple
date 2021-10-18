package com.glaze.maple.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

class ClassUtil {
    public static Constructor<?> findSuitableConstructor(Class<?> clazz){
        Constructor<?> constructor = null;
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();

        for(Constructor<?> declaredConstructor : declaredConstructors){
            if(declaredConstructor.getParameterCount() == 0){
                constructor = declaredConstructor;
            }
        }

        return constructor;
    }

    public static boolean isMappingMethodSignatureValid(Method method){
        return method.getReturnType() != void.class
               && method.getReturnType() != Void.class
               && !method.getReturnType().isInterface()
               && !method.getReturnType().isPrimitive()
               && method.getParameterCount() == 1;
    }
}
