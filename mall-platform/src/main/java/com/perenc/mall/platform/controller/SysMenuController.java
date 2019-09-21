package com.perenc.mall.platform.controller;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.platform.entity.dto.SysMenuDTO;
import com.perenc.mall.platform.entity.vo.SysMenuVO;
import com.perenc.mall.platform.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: SysMenuController
 * @Description: 系统菜单
 *
 * @Author: GR
 * @Date: 2019/9/19 14:01 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/19     GR     		
 */
@Slf4j
@RestController
@RequestMapping("sysMenu")
public class SysMenuController {

    @Autowired
    private ISysMenuService service;


    /**
     * @description: 添加系统菜单
     * @param SysMenuDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addSysMenu(@RequestBody @Valid SysMenuDTO SysMenuDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveSysMenu(SysMenuDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取系统菜单列表，默认升序排列返回
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.SysMenuVO>
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public List<SysMenuVO> listSysMenu() {
        List<SysMenuVO> listSysMenuDO = service.listSysMenu();
        return listSysMenuDO;
    }

    /**
     * @description: 根据指定ID获取SysMenu信息
     * @param id
     * @return com.perenc.mall.platform.entity.vo.SysMenuVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public SysMenuVO getSysMenu(@RequestParam Integer id) {
        SysMenuVO sysMenuVO = service.getSysMenu(id);
        if (null == sysMenuVO) {
            throw new BusinessException("ID为" + id + "的系统菜单不存在");
        }
        return sysMenuVO;
    }

    /**
     * @description: 系统菜单更新
     * @param sysMenuDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateSysMenu(@RequestBody SysMenuDTO sysMenuDTO) {
        service.updateSysMenu(sysMenuDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 系统菜单删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removeSysMenu(@RequestParam Integer id) {
        service.removeSysMenuById(id);
        return JsonResult.buildResultOk();
    }
}
