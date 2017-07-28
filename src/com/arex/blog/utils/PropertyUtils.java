package com.arex.blog.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.arex.blog.model.Blog;

public class PropertyUtils {

	public static void setPropertyValue(Object object, String property,
			String value, String defaultValue) {
		
		String methodName = "set" + property.substring(0, 1).toUpperCase() + property.substring(1);
		System.out.println(methodName);
		
		try {
			Method[] methods = Blog.class.getMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					if (value==null || "".equals(value)) {
						method.invoke(object, defaultValue);
					} else {
						method.invoke(object, value);
					}
				}
			}
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
