package com.perenc.mall.mobile.service;

import com.perenc.mall.mobile.entity.UserDO;

/**
 * @ClassName: UserService
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/12 11:03 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/12     GR     		
 */
public interface UserService {

    /**
     * @description: //TODO 新增用户信息
     * @param userDO
     * @return void
     * @author: GR
     * @date: 2019/9/12
     */
    void save(UserDO userDO);

    /**
     * @description: //TODO 根据用户ID移除用户信息
     * @param userId
     * @return void
     * @author: GR
     * @date: 2019/9/12
     */
    void removeByUserId(Integer userId);
}
