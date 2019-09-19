package com.perenc.mall.platform.controller;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.platform.entity.dto.StoreDTO;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.platform.entity.vo.StoreVO;
import com.perenc.mall.platform.service.IStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName: StoreController
 * @Description: 店铺相关
 *
 * @Author: GR
 * @Date: 2019-9-14 13:57 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@RestController
@RequestMapping("store")
public class StoreController {
    @Autowired
    private IStoreService service;

    /**
     * @description: 添加店铺信息
     * @param storeDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addStore(@RequestBody @Valid StoreDTO storeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveStore(storeDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取店铺列表，默认升序排列返回
     * @param currentPage
     * @param pageSize
     * @return java.util.List<com.perenc.mall.platform.entity.model.StoreDO>
     * @author: GR
     * @date: 2019/9/19
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public PageVO listStoreDO(Integer currentPage, Integer pageSize,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String applyName,
                              @RequestParam(required = false) String applyPhone,
                              @RequestParam(required = false) Integer type,
                              @RequestParam(required = false) Integer status) {
        return service.listStore(currentPage, pageSize, name, applyName, applyPhone, type, status);
    }

    /**
     * @description: 根据指定ID获取店铺信息
     * @param id
     * @return com.perenc.mall.platform.entity.model.StoreVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public StoreVO getNavMenu(@RequestParam Integer id) {
        StoreVO storeVO = service.getStore(id);
        if (null == storeVO) {
            throw new BusinessException("ID为" + id + "的店铺不存在");
        }
        return storeVO;
    }

    /**
     * @description: 店铺更新
     * @param storeDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateStore(@RequestBody StoreDTO storeDTO) {
        service.updateStore(storeDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 店铺删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removeStore(@RequestParam Integer id) {
        service.removeStoreById(id);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 店铺审核
     * @param id
     * @param reason
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/19
     */
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    public Result storeAudit(@RequestParam Integer id,
                             @RequestParam Integer status,
                             @RequestParam String reason) {
        service.storeAudit(id, status, reason);
        return JsonResult.buildResultOk();
    }
}
