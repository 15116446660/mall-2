package com.perenc.mall.mobile.controller;

import com.perenc.mall.mobile.entity.UserDO;
import com.perenc.mall.mobile.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * @ClassName: UserController
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/12 11:13 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/12     GR     		
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Object save(@Valid UserDO userDO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }
        userService.save(userDO);
        return "新增用户成功";
    }

    @RequestMapping("removeById")
    public Object removeById(Integer id) {
        userService.removeByUserId(id);
        return "移除用户成功";
    }

}
