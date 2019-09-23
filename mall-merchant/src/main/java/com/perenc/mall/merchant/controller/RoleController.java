package com.perenc.mall.merchant.controller;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.merchant.entity.dto.RoleDTO;
import com.perenc.mall.merchant.entity.vo.RoleVO;
import com.perenc.mall.merchant.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: RoleController
 * @Description:
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
@RequestMapping("role")
public class RoleController {


    @Autowired
    private IRoleService service;

    /**
     * @description: 添加角色
     * @param roleDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addRole(@RequestBody @Valid RoleDTO roleDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveRole(roleDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取角色列表，默认升序排列返回
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.RoleVO>
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public List<RoleVO> listRoles() {
        List<RoleVO> listRoleVO = service.listRoles();
        return listRoleVO;
    }

    /**
     * @description: 根据指定ID获取Role信息
     * @param id
     * @return com.perenc.mall.platform.entity.vo.RoleVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public RoleVO getRole(@RequestParam Integer id) {
        RoleVO roleVO = service.getRole(id);
        if (null == roleVO) {
            throw new BusinessException("ID为" + id + "的角色不存在");
        }
        return roleVO;
    }

    /**
     * @description: 角色更新
     * @param roleDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateRole(@RequestBody RoleDTO roleDTO) {
        service.updateRole(roleDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 角色删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removeRole(@RequestParam Integer id) {
        service.removeRoleById(id);
        return JsonResult.buildResultOk();
    }

}
