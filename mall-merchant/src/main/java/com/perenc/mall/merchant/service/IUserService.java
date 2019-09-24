package com.perenc.mall.merchant.service;

import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.UserDTO;
import com.perenc.mall.merchant.entity.vo.UserVO;

import java.util.List;

/**
 * @ClassName: IUserService
 * @Description: 用户服务类
 *
 * @Author: GR
 * @Date: 2019/9/23 9:51 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
public interface IUserService {

    /**
     * @description: 添加用户
     * @param userDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveUser(UserDTO userDTO);

    /**
     * @description: 获取用户
     * @param id
     * @return com.perenc.mall.platform.entity.vo.UserVO
     * @author: GR
     * @date: 2019/9/17
     */
    UserVO getUser(Integer id);


    /**
     * @description: 根据ID移除用户
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeUserById(Integer id);


    /**
     * @description: 获取用户列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.UserVO>
     * @author: GR
     * @date: 2019/9/17
     */
    PageVO<UserVO> listUsers(Integer currentPage, Integer pageSize);

    /**
     * @description: 获取用户列表
     * @param userDTO
     * @return java.util.List<com.perenc.mall.platform.entity.model.UserDO>
     * @author: GR
     * @date: 2019/9/17
     */
    void updateUser(UserDTO userDTO);
}
