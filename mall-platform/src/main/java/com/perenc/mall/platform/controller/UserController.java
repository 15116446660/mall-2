package com.perenc.mall.platform.controller;

import com.perenc.mall.common.controller.BaseController;
import com.perenc.mall.platform.entity.model.UserDO;
import com.perenc.mall.platform.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserController
 * @Description: 用户相关
 *
 * @Author: GR
 * @Date: 2019-9-14 17:19 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserServiceImpl, UserDO> {
}
