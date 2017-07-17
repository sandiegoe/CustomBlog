package com.arex.blog.utils;

import java.lang.reflect.ParameterizedType;

public class GenericUtils {

	public static Class getActualClass(Class clazz) {
		ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
		return (Class) type.getActualTypeArguments()[0];
	}
}
