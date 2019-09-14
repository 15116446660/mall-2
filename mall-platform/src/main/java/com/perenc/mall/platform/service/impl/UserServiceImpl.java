package com.perenc.mall.platform.service.impl;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.model.UserDO;
import com.perenc.mall.platform.mapper.UserMapper;
import com.perenc.mall.platform.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 14:32 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class UserServiceImpl extends BaseService<UserMapper, UserDO> implements IUserService {
}
