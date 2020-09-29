package com.jiurong.hcx.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author soyeajr
 * @date 2019-2-26
 * @Description
 */
public class ConvertUtils {

	/**
	 * Object数据类型转为指定类型
	 *
	 * @param obj    Object
	 * @param tClass 指定类型的Class
	 * @param <T>    泛型
	 * @return 转换后的指定的类型值
	 */
	public static <T> T object2T(Object obj, Class<T> tClass) {
		if (obj == null) {
			return null;
		}
		if (tClass == Byte.class) {
			//如果传入的就是数字类型，则用此提高点效率
			if (obj instanceof Number) {
				return tClass.cast(((Number) obj).byteValue());
			}
			return tClass.cast(objToBigDecimal(obj).byteValue());
		}
		if (tClass == Short.class) {
			if (obj instanceof Number) {
				return tClass.cast(((Number) obj).shortValue());
			}
			return tClass.cast(objToBigDecimal(obj).shortValue());
		}
		if (tClass == Integer.class) {
			if (obj instanceof Number) {
				return tClass.cast(((Number) obj).intValue());
			}
			return tClass.cast(objToBigDecimal(obj).intValue());
		}
		if (tClass == Double.class) {
			if (obj instanceof Number) {
				return tClass.cast(((Number) obj).doubleValue());
			}
			return tClass.cast(objToBigDecimal(obj).doubleValue());
		}
		if (tClass == Long.class) {
			if (obj instanceof Number) {
				return tClass.cast(((Number) obj).longValue());
			}
			return tClass.cast(objToBigDecimal(obj).longValue());
		}
		if (tClass == Float.class) {
			if (obj instanceof Number) {
				return tClass.cast(((Number) obj).floatValue());
			}
			return tClass.cast(objToBigDecimal(obj).floatValue());
		}
		if (tClass == Boolean.class) {
			return tClass.cast(Boolean.valueOf(String.valueOf(obj)));
		}
		if (tClass == String.class) {
			return tClass.cast(String.valueOf(obj));
		}
		return tClass.cast(obj);
	}

	/**
	 * 重写函数，obj转为int
	 *
	 * @param obj obj
	 * @return int
	 */
	public static Integer object2Integer(Object obj) {
		return object2T(obj, Integer.class);
	}

	/**
	 * 重写函数，obj转为long
	 *
	 * @param obj obj
	 * @return long
	 */
	public static Long object2Long(Object obj) {
		return object2T(obj, Long.class);
	}

	/**
	 * 重写函数，obj转为double
	 *
	 * @param obj obj
	 * @return double
	 */
	public static Double object2Double(Object obj) {
		return object2T(obj, Double.class);
	}

	/**
	 * Object转为BigDecimal
	 *
	 * @param obj obj
	 * @return BigDecimal
	 */
	private static BigDecimal objToBigDecimal(Object obj) {
		String str = String.valueOf(obj);
		if (str.startsWith("0x")) {//十六进制支持
			return new BigDecimal(Long.parseLong(str.substring(2), 16));
		} else if (str.startsWith("0b")) {//二进制支持
			return new BigDecimal(Long.parseLong(str.substring(2), 2));
		}
		return new BigDecimal(str);
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] arrayObject2T(Object[] objects, Class<T> tClass) {
		if (objects == null) {
			return null;
		}
		T[] tArray = (T[]) Array.newInstance(tClass, objects.length);
		for (int i = 0; i < objects.length; i++) {
			tArray[i] = object2T(objects[i], tClass);
		}
		return tArray;
	}


	/**
	 * 转化bean到Map
	 *
	 * @param o           bean
	 * @param tClass      bean的类
	 * @param oldMap      原Map，如果map中属性存在，则覆盖
	 * @param emptyToNull 是否把没有数据的值置为null，不放入map
	 * @return
	 */
	public static Map<String, Object> objectToMap(Object o, Class tClass, Map oldMap, boolean emptyToNull) {
		oldMap = (oldMap == null ? new HashMap<String, Object>() : oldMap);
		BeanInfo info;
		try {
			info = Introspector.getBeanInfo(tClass);
			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
				Method reader = pd.getReadMethod();
				// 内容为null的过滤掉
				Object obj = reader.invoke(o);
				if (reader == null || obj == null) {
					continue;
				}
				if (emptyToNull && isEmptyObject(obj)) {
					continue;
				}
				// 默认继承Object类的属性，过滤掉
				if (pd.getName().equalsIgnoreCase("class")) {
					continue;
				}

				oldMap.put(pd.getName(), obj);
			}
		} catch (IntrospectionException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return oldMap;
	}

	/**
	 * 吧bean中有数据的值Map清空，objectToMap的反操作
	 *
	 * @param o           bean
	 * @param tClass      bean的类
	 * @param oldMap      原Map，如果map中属性存在，则覆盖
	 * @param emptyToNull 是否把没有数据的值置为null，不放入map
	 * @return
	 */
	public static Map<String, Object> objectRemoveFromMap(Object o, Class tClass, Map oldMap, boolean emptyToNull) {
		oldMap = (oldMap == null ? new HashMap<String, Object>() : oldMap);
		BeanInfo info;
		try {
			info = Introspector.getBeanInfo(tClass);
			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
				Method reader = pd.getReadMethod();
				// 内容为null的过滤掉
				Object obj = reader.invoke(o);
				if (reader == null || obj == null) {
					continue;
				}
				if (emptyToNull && isEmptyObject(obj)) {
					continue;
				}
				// 默认继承Object类的属性，过滤掉
				if (pd.getName().equalsIgnoreCase("class")) {
					continue;
				}
				oldMap.remove(pd.getName());
			}
		} catch (IntrospectionException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return oldMap;
	}


	/**
	 * 转化bean到Map,Map的key为 className.attr的形式
	 *
	 * @param o           bean
	 * @param tClass      bean的类
	 * @param oldMap      原Map，如果map中属性存在，则覆盖
	 * @param emptyToNull 是否把没有数据的值置为null，不放入map
	 * @return
	 */
	public static Map<String, Object> objectToMapWithClassNameOnKey(Object o, Class tClass, Map oldMap, boolean emptyToNull) {
		oldMap = (oldMap == null ? new HashMap<String, Object>() : oldMap);
		BeanInfo info;
		try {
			info = Introspector.getBeanInfo(tClass);
			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
				Method reader = pd.getReadMethod();
				// 内容为null的过滤掉
				Object obj = reader.invoke(o);
				if (reader == null || obj == null) {
					continue;
				}
				if (emptyToNull && isEmptyObject(obj)) {
					continue;
				}
				// 默认继承Object类的属性，过滤掉
				if (pd.getName().equalsIgnoreCase("class")) {
					continue;
				}//获取类名
				String lowerFirstClassName = MyStringUtils.lowerFirst(tClass.getName().substring(tClass.getName().lastIndexOf(".") + 1));
				oldMap.put(lowerFirstClassName + "." + pd.getName(), obj);
			}
		} catch (IntrospectionException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return oldMap;
	}

	/**
	 * 转化bean到Map
	 *
	 * @param o      bean
	 * @param tClass bean的类
	 * @return
	 */
	public static Map<String, Object> objectToMap(Object o, Class tClass) {
		return objectToMap(o, tClass, null, true);
	}


	/**
	 * 识别String和collection
	 *
	 * @param obj
	 * @return
	 */
	private static boolean isEmptyObject(Object obj) {
		if (obj instanceof String) {
			return EmptyUtils.isEmpty((String) obj);
		} else if (obj instanceof Collection) {
			return EmptyUtils.isEmpty((Collection) obj);
		}
		return EmptyUtils.isEmpty(obj);
	}

	public static void main(String... args) {
		Object obj = new Object();
		obj = "true";
		System.out.println(object2T(obj, Boolean.class));
		String[] strings = new String[]{"4343", "1212", "6789", "899"};
		System.out.println(Arrays.deepToString(arrayObject2T(strings,
				Integer.class)));
	}
}
