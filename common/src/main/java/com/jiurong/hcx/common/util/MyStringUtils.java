package com.jiurong.hcx.common.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author soyeajr
 * @date 2019-2-26
 * @Description
 */
public class MyStringUtils extends StringUtils {
    /**
     * Object实例转为字符串，若为null则返回"",否则调用String.valueOf(obj)
     *
     * @param obj Object实例
     * @return 字符串
     */
    public static String obj2Str(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }

    /**
     * 获取字符串的指定长度
     *
     * @param src    源字符串
     * @param length 指定的长度
     * @return 被截取后的
     */
    public static String getShortStr(String src, int length) {
        if (isEmpty(src)) {
            return "";
        }
        if (src.length() <= length) {
            return src;
        } else {
            return src.substring(0, length) + "...";
        }
    }

    /**
     * 将字符串的首字母大写
     *
     * @param str 源字符串
     * @return 首字母大写后的字符串
     */
    public static String upperFirst(String str) {
        String result = str;
        if (str != null) {
            result = str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return result;
    }

    /**
     * 将字符串的首字母小写
     *
     * @param str 源字符串
     * @return 首字母小写后的字符串
     */
    public static String lowerFirst(String str) {
        String result = str;
        if (str != null) {
            result = str.substring(0, 1).toLowerCase() + str.substring(1);
        }
        return result;
    }

    /**
     * 将单词中的大写字母替换成下划线加小写字母 例如: UserInfo -> user_info
     *
     * @param str 源字符
     * @return 替换后的字符
     */
    public static String replaceUpperToUnderline(String str) {
        char[] chars = str.toCharArray();
        String outs = "";
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isUpperCase(c)) {
                char lc = Character.toLowerCase(c);
                if (i == 0) {
                    if (chars.length > 1 && Character.isUpperCase(chars[i + 1])) {
                        outs += c;
                    } else {
                        outs += lc;
                    }
                } else if (i > 0 && i < chars.length - 1) {//UserFromUSAMember-->user_from_USA_member
                    // 只有该字母右边都是小写字母时才变小写，否则保持大写
                    if (Character.isLowerCase(chars[i + 1])) {
                        outs += '_';
                        outs += lc;
                    } else if (Character.isLowerCase(chars[i - 1])) {//前小写，后大写，则加前缀下划线
                        outs += '_';
                        outs += c;
                    } else {//前后都是大写，原样输出
                        outs += c;
                    }
                } else if (i == chars.length - 1) {
                    if (chars.length > 1 && Character.isUpperCase(chars[i - 1])) {
                        outs += c;
                    } else {
                        outs += lc;
                    }
                }
            } else {
                outs += c;
            }
        }
        return outs;
    }

    /**
     * 将单词中的下划线及后面的字母替换成大写字母 例如 archive_info -> ArchiveInfo
     *
     * @param str 原字符
     * @return 转换后的字符
     */
    public static String replaceUnderlineToUpper(String str) {
        char[] chars = str.toCharArray();
        String outs = "";
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '_') {
                if (i < chars.length - 1) {
                    char uc = Character.toUpperCase(chars[i + 1]);
                    outs += uc;
                }
            } else {
                if (i == 0 || chars[i - 1] != '_') {
                    outs += c;
                }
            }
        }
        return outs;
    }

    /**
     * 逗号隔开的字符串转化为List,clazz可为bean
     *
     * @param dotString 原字符串
     * @param clazz     转化为的类
     * @param fieldName 转化为的类
     * @return
     */
    public static <T> List<T> dotStringToList(String dotString, Class<T> clazz, String fieldName) {
        return Arrays.asList(splitedStringToArray(dotString, ",", clazz, fieldName));
    }

    /**
     * 字符串按照指定表达式分割成指定数据类型的List
     *
     * @param str       源字符串
     * @param separator 分隔符
     * @param tClass    目标数据类型
     * @param <T>       泛型类
     * @return 目标List, 不可能为null
     */
    public static <T> LinkedList<T> strSplitToList(String str, String separator, Class<T> tClass) {
        LinkedList<T> linkedList = new LinkedList<>();
        if (isNotEmpty(str)) {
            String[] array = str.split(separator);
            for (int i = 0; i < array.length; i++) {
                linkedList.add(ConvertUtils.object2T(array[i], tClass));
            }
        }
        return linkedList;
    }

    /**
     * 类似js的join
     *
     * @param collection 列表或数组
     * @param separator  分隔符
     * @param clazz      列表类型，如果不是基本类型，则可指定field获取bean中的数据
     * @param field
     * @return
     */
    public static <T> String strJoin(Collection<T> collection, String separator, Class clazz, String field) {
        if (collection == null)
            return "";
        StringBuilder strBuilder = new StringBuilder();
        for (T t : collection) {
            strBuilder.append(separator);
            strBuilder.append(tToString(t, clazz, field));
        }
        String returnedStr = strBuilder.toString();
        if (returnedStr.length() > 0)
            return returnedStr.substring(separator.length());
        return "";
    }

    @SuppressWarnings("unchecked")
    private static <T> String tToString(T t, Class<T> clazz, String fieldName) {
        if (fieldName == null) {
            if (clazz == Byte.class
                    || clazz == Short.class
                    || clazz == Integer.class
                    || clazz == Double.class
                    || clazz == Long.class
                    || clazz == Float.class
                    || clazz == Boolean.class
                    || clazz == String.class) {
                return "" + t;
            }
            return "";
        } else {
            try {
                List<Method> methodList = findAllClassMethod(clazz);
                Method setMethod = null;
                for (Method method : methodList) {
                    if (method.getName().startsWith("get")) {
                        if (method.getName().equals("get" + upperFirst(fieldName))) {
                            setMethod = method;
                            break;
                        }
                    }
                }
                if (setMethod != null) {
                    Class returnType = setMethod.getReturnType();
                    return tToString(setMethod.invoke(t), returnType, null);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static List<Method> findAllClassMethod(Class clazz) {
        List<Method> methodList = new ArrayList<>();
        findAllClassMethod(clazz, methodList);
        return methodList;
    }

    private static void findAllClassMethod(Class clazz, List<Method> methodList) {
        Method[] methods = clazz.getDeclaredMethods();
        methodList.addAll(Arrays.asList(methods));
        if (clazz.getSuperclass() != null) {
            findAllClassMethod(clazz.getSuperclass(), methodList);
        }
    }

    /**
     * 转成数组
     *
     * @param splitedStr
     * @param separator
     * @param clazz
     * @return
     */
    public static <T> T[] splitedStringToArray(String splitedStr, String separator, Class<T> clazz) {
        return (T[]) splitedStringToArray(splitedStr, separator, clazz, null);
    }

    /**
     * 转成数组
     *
     * @param splitedStr
     * @param separator
     * @param clazz
     * @param field      clazz是bean的时候的属性名，该属性必须有set方法
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] splitedStringToArray(String splitedStr, String separator, Class<T> clazz, String field) {

        if (!splitedStr.matches("^[^" + separator + "]+(" + separator + "[^" + separator + "]+)*$")) {
            return (T[]) Array.newInstance(clazz, 0);
        }
        String[] stringArray = splitedStr.split(separator);
        T[] result = (T[]) Array.newInstance(clazz, stringArray.length);
        //Object[] result=new Object[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            if ("".equals(stringArray[i])) {
                continue;
            }
            result[i] = stringToT(stringArray[i], clazz, field);
        }
        return (T[]) result;
    }

    @SuppressWarnings("unchecked")
    private static <T> T stringToT(String strT, Class<T> clazz, String fieldName) {
        if (fieldName == null) {
            Object t = strT;
            if (clazz == Byte.class) {
                // 如果传入的就是数字类型，则用此提高点效率
                t = Byte.parseByte(strT);
            }
            if (clazz == Short.class) {
                t = Short.parseShort(strT);

            }
            if (clazz == Integer.class) {

                t = Integer.parseInt(strT);
            }
            if (clazz == Double.class) {
                t = Double.parseDouble(strT);
            }
            if (clazz == Long.class) {
                t = Long.parseLong(strT);
            }
            if (clazz == Float.class) {
                t = Float.parseFloat(strT);
            }
            if (clazz == Boolean.class) {
                t = Boolean.parseBoolean(strT);
            }
            return (T) t;
        } else {
            T obj = null;
            try {
                obj = (T) clazz.newInstance();
                List<Method> methodList = findAllClassMethod(clazz);
                Method setMethod = null;
                for (Method method : methodList) {
                    if (method.getName().startsWith("set")) {
                        if (method.getName().equals("set" + upperFirst(fieldName))) {
                            setMethod = method;
                            break;
                        }
                    }
                }
                if (setMethod != null) {
                    Class[] paramType = setMethod.getParameterTypes();
                    if (paramType.length != 1) {
                        return null;//setMethodError
                    }
                    Object tData = stringToT(strT, paramType[0], null);
                    setMethod.invoke(obj, tData);
                    return (T) obj;
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    //压缩字符串,最后省略
    public static String tipsString(String str, int length) {
        if (str.length() > length) {
            return str.substring(0, length) + "...";
        } else {
            return str;
        }
    }

    /**
     * 替换下划线及百分号，加上转移字符
     *
     * @param str 字符串
     * @return
     */
    public static String replaceUnderlineAndPercent(String str) {
        if (str == null) {
            return null;
        }
        str = str.replace("%", "\\%");
        str = str.replace("_", "\\_");
        return str;
    }

}
