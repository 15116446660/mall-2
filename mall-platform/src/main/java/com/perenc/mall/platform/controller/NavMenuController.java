package com.perenc.mall.platform.controller;

import com.perenc.mall.common.controller.BaseController;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.platform.entity.dto.AdvertiseDTO;
import com.perenc.mall.platform.entity.dto.NavMenuDTO;
import com.perenc.mall.platform.entity.model.AdvertiseDO;
import com.perenc.mall.platform.entity.model.NavMenuDO;
import com.perenc.mall.platform.entity.vo.NavMenuVO;
import com.perenc.mall.platform.service.IAdvertiseService;
import com.perenc.mall.platform.service.INavMenuService;
import com.perenc.mall.platform.service.impl.NavMenuServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: NarBarController
 * @Description: 导航栏相关
 *
 * @Author: GR
 * @Date: 2019-9-14 17:17 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@RestController
@RequestMapping("nav")
public class NavMenuController {

    @Autowired
    private INavMenuService service;

    /**
     * @description: 添加导航菜单
     * @param navMenuDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addNavMenu(@RequestBody @Valid NavMenuDTO navMenuDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveNavMenu(navMenuDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取导航菜单列表，默认升序排列返回
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.model.NavMenuDO>
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<NavMenuDO> listAdvertises() {
        return service.listNavMenus();
    }

    /**
     * @description: 根据指定ID获取NavMenu信息
     * @param id
     * @return com.perenc.mall.platform.entity.model.NavMenuDO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public NavMenuVO getNavMenu(@RequestParam Integer id) {
        NavMenuVO navMenuDO = service.getNavMenu(id);
        if (null == navMenuDO) {
            throw new BusinessException("ID为" + id + "的导航菜单不存在");
        }
        return navMenuDO;
    }

    /**
     * @description: 导航菜单更新
     * @param navMenuDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateNavMenu(@RequestBody NavMenuDTO navMenuDTO) {
        service.updateNavMenu(navMenuDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 导航菜单删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removeNavMenu(@RequestParam Integer id) {
        service.removeNavMenuById(id);
        return JsonResult.buildResultOk();
    }
}
