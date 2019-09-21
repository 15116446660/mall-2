package com.perenc.mall.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName: CacheUserInfo
 * @Description: 缓存当前用户的相关信息
 *
 * @Author: GR
 * @Date: 2019/9/20 17:27 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class CacheUserInfo<T> implements Serializable {

    /**
     * @description: 登陆时间
     * @author: GR
     * @date: 2019/9/20 17:29
     */
    private String loginTime;

    /**
     * @description: 退出时间
     * @author: GR
     * @date: 2019/9/20 17:29
     */
    private String LoginOutTime;

    /**
     * @description: 用户ID
     * @author: GR
     * @date: 2019/9/20 17:29
     */
    private String userId;

    /**
     * @description: 用户姓名
     * @author: GR
     * @date: 2019/9/20 17:29
     */
    private String userName;

    /**
     * @description: 角色ID
     * @author: GR
     * @date: 2019/9/20 17:29
     */
    private String roleId;

    /**
     * @description: 角色ID
     * @author: GR
     * @date: 2019/9/20 17:29
     */
    private Integer storeId;

    /**
     * @description: 角色ID
     * @author: GR
     * @date: 2019/9/20 17:29
     */
    private T t;

}
