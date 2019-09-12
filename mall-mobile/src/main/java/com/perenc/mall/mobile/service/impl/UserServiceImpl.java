package com.perenc.mall.mobile.service.impl;

import com.perenc.mall.mobile.mapper.UserMapper;
import com.perenc.mall.mobile.entity.UserDO;
import com.perenc.mall.mobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: UserServiceImpl
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/12 11:06 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/12     GR     		
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(UserDO userDO) {
        userMapper.insert(userDO);
    }

    @Override
    public void removeByUserId(Integer userId) {
        userMapper.deleteById(userId);
    }
}
