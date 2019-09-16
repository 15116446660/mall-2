package com.perenc.mall.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PropertysUtils
 * @Description: 获取Javabean的读写属性封装类
 *
 * @Author: GR
 * @Date: 2019-7-15 16:58 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-7-15     GR      		
 */
public class PropertysUtils {
    public PropertysUtils() {
    }

    /**
     * @description: //TODO 获取java的所有属性，包括父类
     * @param clazz
     * @return java.util.List<java.lang.reflect.Field>
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:59
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static List<Field> getFields(Class clazz) throws Exception {
        List<Field> fields = new ArrayList<Field>();
        fields = getFields(fields, clazz);
        if (fields.size() == 0) {
            return null;
        } else {
            return fields;
        }
    }

    private static List<Field> getFields(List<Field> fields, Class clazz)
            throws Exception {
        if (clazz.equals(Object.class)) {
            return fields;
        }
        Class superClass = clazz.getSuperclass();
        fields = getFields(fields, superClass);
        Field[] localFields = clazz.getDeclaredFields();
        for (int i = 0; i < localFields.length; i++) {
            localFields[i].setAccessible(true);
            fields.add(localFields[i]);
        }
        return fields;

    }

    public static PropertyDescriptor getProperty(Class clazz, String fieldName)
            throws NoSuchFieldException {
        // 如果已经到达基类，停止递归。
        if (clazz.equals(Object.class)) {
            throw new NoSuchFieldException(fieldName);
        }
        // 1.递归获取父类的字段。
        Class superClass = clazz.getSuperclass();
        try {
            PropertyDescriptor superField = getProperty(superClass, fieldName);
            return superField;
        }
        // 父类中不能找到符合的字段
        catch (Exception e) {
        }
        // 2.在本类定义中找。
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(fieldName)) {
                try {
                    Class fieldType = fields[i].getType();
                    String getterName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    String setterName = "set"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    Class[] args = {fieldType};
                    Method getter = clazz.getMethod(getterName, null);
                    Method setter = clazz.getMethod(setterName, args);
                    PropertyDescriptor property = new PropertyDescriptor(
                            fieldName, getter, setter);
                    return property;
                } catch (Exception e) {
                }
            }
        }
        throw new NoSuchFieldException(fieldName);
    }

    public static Object getFieldValue(Object pojo, String fieldName)
            throws Exception {
        Object result = null;
        Method getter = null;
        String getterName = "get" + fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1);
        try {
            getter = pojo.getClass().getMethod(getterName, null);
            result = getter.invoke(pojo, null);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public static void setFieldValue(Object pojo, String fieldName, Object value)
            throws Exception {
        try {
            PropertyDescriptor property = getProperty(pojo.getClass(),
                    fieldName);
            Method setter = property.getWriteMethod();
            Object[] args = {value};
            setter.invoke(pojo, args);
        } catch (Exception e) {
            throw e;
        }
        return;
    }

    public static Class getFieldType(Class pojo, String fieldName)
            throws Exception {
        try {
            return getProperty(pojo, fieldName).getPropertyType();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @description: //TODO 获取对象声明的字段名称（包括基类中定义的字段）
     * @param clazz
     * @return java.beans.PropertyDescriptor[]
     * @throws
     * @author: GR
     * @date: 2019-7-15 17:01
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static PropertyDescriptor[] getPropertys(Class clazz) {
        // 如果已经到达基类，停止递归。
        if (clazz.equals(Object.class)) {
            return new PropertyDescriptor[0];
        }
        // 1.递归获取父类的字段。
        Class superClass = clazz.getSuperclass();
        PropertyDescriptor[] superFields = getPropertys(superClass);
        ArrayList result = new ArrayList();
        for (int i = 0; i < superFields.length; i++) {
            result.add(superFields[i]);
        }
        // 2.获取本类定义的字段。
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            PropertyDescriptor property;
            try {
                String fieldName = fields[i].getName();
                Class fieldType = fields[i].getType();
                String getterName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                String setterName = "set"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                Class[] args = {fieldType};
                Method getter = clazz.getMethod(getterName, null);
                Method setter = clazz.getMethod(setterName, args);
                property = new PropertyDescriptor(fieldName, getter, setter);
                result.add(property);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (PropertyDescriptor[]) result.toArray(new PropertyDescriptor[0]);
    }
}
