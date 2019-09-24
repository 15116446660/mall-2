package com.perenc.mall.merchant.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.RoleDTO;
import com.perenc.mall.merchant.entity.vo.RoleVO;

import java.util.List;

/**
 * @ClassName: IRoleService
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/23 9:51 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
public interface IRoleService {

    /**
     * @description: 添加角色
     * @param roleDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveRole(RoleDTO roleDTO);

    /**
     * @description: 获取角色
     * @param id
     * @return com.perenc.mall.platform.entity.vo.RoleVO
     * @author: GR
     * @date: 2019/9/17
     */
    RoleVO getRole(Integer id);


    /**
     * @description: 根据ID移除角色
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeRoleById(Integer id);


    /**
     * @description: 获取角色列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.RoleVO>
     * @author: GR
     * @date: 2019/9/17
     */
    PageVO<RoleVO> listRoles(Integer currentPage, Integer pageSize);

    /**
     * @description: 获取角色列表
     * @param roleDTO
     * @return java.util.List<com.perenc.mall.platform.entity.model.RoleDO>
     * @author: GR
     * @date: 2019/9/17
     */
    void updateRole(RoleDTO roleDTO);
}
