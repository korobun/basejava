package com.lexkor.webapp;

import com.lexkor.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.get(r));
        field.set(r, "new uuid");

        Method method = r.getClass().getMethod("toString", null);
        System.out.println(method.invoke(r));
    }
}
