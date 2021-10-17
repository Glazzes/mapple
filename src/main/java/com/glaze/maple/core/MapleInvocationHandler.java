package com.glaze.maple.core;

import com.glaze.maple.annotations.Mapping;
import com.glaze.maple.expcetion.ConstructorNotFoundException;
import com.glaze.maple.expcetion.FieldNotPresentException;
import com.glaze.maple.expcetion.InvalidMethodSignatureException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class MapleInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean isMethodValid = ClassUtil.isMappingMethodSignatureValid(method);
        if(!isMethodValid){
            String errorMessage = """
            A mapping method return type must be different for void.class or Void.class and must take only one parameter
            """;
            throw new InvalidMethodSignatureException(errorMessage);
        }

        Class<?> targetClassType = method.getReturnType();
        Constructor<?> targetConstructor = ClassUtil.findSuitableConstructor(targetClassType);
        if(targetConstructor == null){
            String errorMessage = targetClassType + " must have a no parameter constructors";
            throw new ConstructorNotFoundException(errorMessage);
        }

        Object sourceInstance = args[0];
        Object targetInstance = targetConstructor.newInstance();
        Mapping[] mappingAnnotations = method.getAnnotationsByType(Mapping.class);

        return this.performMapping(sourceInstance, targetInstance, mappingAnnotations);
    }

    private Object performMapping(Object source, Object target, Mapping[] mappingAnnotations){
        for(Mapping mapping : mappingAnnotations){
            Object sourceValue = getValueFromSource(source, mapping.source());
            Field targetField = this.getTargetField(target, mapping.target());

            try{
                targetField.set(target, sourceValue);
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }

        return target;
    }

    private Object getValueFromSource(Object source, String fieldName){
        try{
            Class<?> clazzType = source.getClass();
            Field currentField = clazzType.getDeclaredField(fieldName);
            currentField.setAccessible(true);

            return currentField.get(source);
        }catch (NoSuchFieldException | IllegalAccessException e){
            String errorMessage = String.format("""
            %s does not have a field named %s
            """, source.getClass(),fieldName);

            throw new FieldNotPresentException(errorMessage, e);
        }
    }

    public Field getTargetField(Object target, String fieldName){
        try{
            Class<?> clazzType = target.getClass();
            Field currentField = clazzType.getDeclaredField(fieldName);
            currentField.setAccessible(true);
            return currentField;
        }catch (NoSuchFieldException e){
            String errorMessage = String.format("""
            %s does not have a field named %s
            """, target.getClass(),fieldName);

            throw new FieldNotPresentException(errorMessage, e);
        }
    }

}
