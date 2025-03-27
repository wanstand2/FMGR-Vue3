package com.fmger.utils;

import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Collection;

import com.ruoyi.common.core.domain.BaseEntity;

public class ReflectUtils {

    public static Map<String, Object> getFields(Object obj) {
        Map<String, Object> map = new HashMap<>();
        List<Field> fields = new ArrayList<>();
        Class _c = obj.getClass();
        while(_c != null) {
        	fields.addAll(Arrays.asList(_c.getDeclaredFields()));
        	_c = _c.getSuperclass();
        }
        for(Field field:fields) {
            if(Modifier.isStatic(field.getModifiers())) {
            	continue;
            }
            field.setAccessible(true);
            try {
            	Class z = field.getType().asSubclass(BaseEntity.class);
            	continue;
            } catch(Exception e) {
            	
            }
            try {
            	Class z = field.getType().asSubclass(Collection.class);
            	continue;
            } catch(Exception e) {
            	
            }
            try {
                if(field.get(obj) == null) {
                	continue;
                }
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println("map=" + map);
        return map;
    }
}