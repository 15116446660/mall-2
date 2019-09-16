package com.perenc.mall.common.util;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.Validate;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: EntityUtils
 * @Description: 实体工具类，快速解决对实体的常驻字段，如：createUser、updateUser等值快速注入
 *
 * @Author: GR
 * @Date: 2019/9/16 16:04 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/16     GR     		
 */
@Slf4j
@NoArgsConstructor(staticName = "build")
public class EntityUtils {

    public <T> void setCreateInfo(T entity) {
        Map<String, Object> map = new HashMap<>();
        map.put("createUser", "创建用户");
        map.put("createTime", DateUtils.getCurrentTime());
        try {
            BeanUtils.populate(entity, map);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("对实体常驻字段注入失败：{}", e.getMessage());
        }
    }

    /**
     *快速将bean的updUser、updHost、updTime附上相关值
     *
     *@param
     *@return
     */
    public <T> void setUpdatedInfo(T entity) {
        Map<String, Object> map = new HashMap<>();
        map.put("updateUser", "创建用户");
        map.put("updateTime", DateUtils.getCurrentTime());
        try {
            BeanUtils.populate(entity, map);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("对实体常驻字段更新失败：{}", e.getMessage());
        }
    }


    /**
     * @description: 判断实体中是否包含某个字段
     * @param obj
     * @param fieldName
     * @return boolean
     * @author: GR
     * @date: 2019/9/16
     */
    public boolean hasField(final Object obj, final String fieldName) {
        Field field = getAccessibleField(obj, fieldName);
        if (field == null) {
            return false;
        }
        return true;

    }

    /**
     * @description: 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问.如向上转型到Object仍无法找到, 返回null.
     * @param obj
     * @param fieldName
     * @return java.lang.reflect.Field
     * @author: GR
     * @date: 2019/9/16
     */
    public Field getAccessibleField(final Object obj, final String fieldName) {
        Validate.notNull(obj, "object can't be null");
        Validate.notBlank(fieldName, "fieldName can't be blank");
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                Field field = superClass.getDeclaredField(fieldName);
                makeAccessible(field);
                return field;
            } catch (NoSuchFieldException e) {//NOSONAR
                // Field不在当前类定义,继续向上转型
                continue;// new add
            }
        }
        return null;
    }

    /**
     * @description: 改变private/protected的成员变量为public，尽量不调用实际改动的语句，避免JDK的SecurityManager抱怨。
     * @param field
     * @return void
     * @author: GR
     * @date: 2019/9/16
     */
    public void makeAccessible(Field field) {
        Boolean bool = (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier
                .isFinal(field.getModifiers())) && !field.isAccessible();
        if (bool) {
            field.setAccessible(true);
        }
    }
}
