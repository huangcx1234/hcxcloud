package com.jiurong.hcx.common.util;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author soyeajr
 * @date 2019-2-26
 * @Description
 */
public class EqualsUtils {
	/**
	 * 比较两个对象是否相等
	 *
	 * @param obj1 obj1
	 * @param obj2 obj2
	 * @return true or false
	 */
	public static boolean equals(Object obj1, Object obj2) {
		return obj1 == null ? obj2 == null : obj1.equals(obj2);
	}

	/**
	 * 比较两个对象不等
	 *
	 * @param obj1 obj1
	 * @param obj2 obj2
	 * @return 比较结果
	 */
	public static boolean notEquals(Object obj1, Object obj2) {
		return !equals(obj1, obj2);
	}

	/**
	 * 深度比较两个对象内部的属性是否相同
	 *
	 * @param obj1             对象1
	 * @param obj2             对象2
	 * @param tClass           class
	 * @param ignoreProperties 忽略的字段
	 * @return 对比结果
	 */
	public static <T> boolean equalsDepth(T obj1, T obj2, Class<T> tClass, String... ignoreProperties) throws Exception {
		if (obj1 == obj2) {
			return true;
		}
		List<String> ignorePropertyList = new ArrayList<String>();
		if (ignoreProperties != null && ignoreProperties.length != 0) {
			ignorePropertyList = Arrays.asList(ignoreProperties);
		}
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(tClass);
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			//跳过指定不需要比较的字段
			if (ignorePropertyList.contains(propertyDescriptor.getName())) {
				continue;
			}
			Method method = propertyDescriptor.getReadMethod();
			if (!Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
				method.setAccessible(true);
			}
			Object value1 = method.invoke(obj1);
			Object value2 = method.invoke(obj2);
			if (EqualsUtils.notEquals(value1, value2)) {
				return false;
			}
		}
		return true;
	}
}
