package com.perenc.mall.common.context;

import com.perenc.mall.common.constant.ContextConstants;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.util.StringHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: BaseContextHandler
 * @Description: 用于存储当前会话用户基本信息
 *
 * @Author: GR
 * @Date: 2019/9/21 9:22 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/21     GR     		
 */
public class BaseContextHandler {

    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static String getRoleId() {
        Object value = get(ContextConstants.CONTEXT_KEY_ROLE_ID);
        return getObjectValue(value);
    }

    public static String getRoleType() {
        Object value = get(ContextConstants.CONTEXT_KEY_ROLE_TYPE);
        return getObjectValue(value);
    }

    public static String getUserID() {
        Object value = get(ContextConstants.CONTEXT_KEY_USER_ID);
        return getObjectValue(value);
    }

    public static Integer getStoreId() {
        Object value = get(ContextConstants.CONTEXT_KEY_STORE_ID);
        if (StringHelper.isNumeric(value.toString())) {
            return Integer.valueOf(getObjectValue(value));
        }
        throw new BusinessException("StoreId 格式不对");
    }


    public static String getUserName() {
        Object value = get(ContextConstants.CONTEXT_KEY_USER_NAME);
        return getObjectValue(value);
    }

    public static String getToken() {
        Object value = get(ContextConstants.CONTEXT_KEY_USER_TOKEN);
        return getObjectValue(value);
    }

    public static void setRoleId(Object roleId) {
        set(ContextConstants.CONTEXT_KEY_ROLE_ID, roleId);
    }

    public static void setRoleType(Object roleType) {
        set(ContextConstants.CONTEXT_KEY_ROLE_TYPE, roleType);
    }

    public static void setUserID(Object userId) {
        set(ContextConstants.CONTEXT_KEY_USER_ID, userId);
    }

    public static void setStoreId(Object storeId) {
        set(ContextConstants.CONTEXT_KEY_STORE_ID, storeId);
    }


    public static void setUserName(Object userName) {
        set(ContextConstants.CONTEXT_KEY_USER_NAME, userName);
    }

    public static void setToken(Object token) {
        set(ContextConstants.CONTEXT_KEY_USER_TOKEN, token);
    }

    public static String getObjectValue(Object value) {
        return value == null ? "" : value.toString();
    }

    public static void clean() {
        threadLocal.remove();
    }
}
