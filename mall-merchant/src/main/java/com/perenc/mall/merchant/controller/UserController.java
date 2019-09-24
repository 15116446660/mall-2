package com.perenc.mall.merchant.controller;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.UserDTO;
import com.perenc.mall.merchant.entity.vo.UserVO;
import com.perenc.mall.merchant.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: UserController
 * @Description: 店铺用户管理
 *
 * @Author: GR
 * @Date: 2019/9/20 20:50 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    private IUserService service;

    /**
     * @description: 添加用户
     * @param userDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveUser(userDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取用户列表，默认升序排列返回
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.UserVO>
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public PageVO<UserVO> listUsers(Integer currentPage, Integer pageSize) {
        PageVO<UserVO> pageVO = service.listUsers(currentPage,pageSize);
        return pageVO;
    }

    /**
     * @description: 根据指定ID获取User信息
     * @param id
     * @return com.perenc.mall.platform.entity.vo.UserVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public UserVO getUser(@RequestParam Integer id) {
        UserVO userVO = service.getUser(id);
        if (null == userVO) {
            throw new BusinessException("ID为" + id + "的用户不存在");
        }
        return userVO;
    }

    /**
     * @description: 用户更新
     * @param userDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateUser(@RequestBody UserDTO userDTO) {
        service.updateUser(userDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 用户删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removeUser(@RequestParam Integer id) {
        service.removeUserById(id);
        return JsonResult.buildResultOk();
    }

}
