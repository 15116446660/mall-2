package com.perenc.mall.common.util;

import com.perenc.mall.common.constant.ContextConstants;
import com.perenc.mall.common.context.BaseContextHandler;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    @Autowired
    private RedisUtils redisUtils;

    public <T> void setCreateInfo(T entity) {
        Map<String, Object> map = new HashMap<>();
        map.put("createUser", "创建用户");
        map.put("createTime", DateUtils.getCurrentTime());

        setKeyValue(entity, map);
    }

    public <T> void setUpdatedInfo(T entity) {
        Map<String, Object> map = new HashMap<>();
        map.put("updateUser", "更新用户");
        map.put("updateTime", DateUtils.getCurrentTime());
        setKeyValue(entity, map);
    }

    private <T> void setKeyValue(T entity, Map<String, Object> map) {
        Assert.notNull(map, "map must not be null");

        // 若为商家端，从缓存中读取当前用户所属商家ID，则注入商家ID
        Integer storeId = BaseContextHandler.getStoreId();
        if (null != storeId) {
            map.put("storeId", BaseContextHandler.getStoreId());
        }

        Set<String> keys = map.keySet();
        for (String key : keys) {
            if (ReflectionUtils.hasField(entity, key)) {
                ReflectionUtils.invokeSetter(entity, key, map.get(key));
            }
        }
    }

}
