package com.perenc.mall.platform.controller;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.platform.entity.dto.StoreCategoryDTO;
import com.perenc.mall.platform.entity.vo.StoreCategoryVO;
import com.perenc.mall.platform.service.IStoreCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: StoreCategoryController
 * @Description: 店铺分类
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
@RequestMapping("storeCategory")
public class StoreCategoryController {

    @Autowired
    private IStoreCategoryService service;


    /**
     * @description: 添加店铺分类
     * @param storeCategoryDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addStoreCategory(@RequestBody @Valid StoreCategoryDTO storeCategoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveStoreCategory(storeCategoryDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取店铺分类列表，默认升序排列返回
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.StoreCategoryVO>
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public List<StoreCategoryVO> listStoreCategory() {
        List<StoreCategoryVO> listStoreCategoryDO = service.listStoreCategory();
        return listStoreCategoryDO;
    }

    /**
     * @description: 根据指定ID获取StoreCategory信息
     * @param id
     * @return com.perenc.mall.platform.entity.vo.StoreCategoryVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public StoreCategoryVO getStoreCategory(@RequestParam Integer id) {
        StoreCategoryVO StoreCategoryVO = service.getStoreCategory(id);
        if (null == StoreCategoryVO) {
            throw new BusinessException("ID为" + id + "的店铺不存在");
        }
        return StoreCategoryVO;
    }

    /**
     * @description: 店铺分类更新
     * @param storeCategoryDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateStoreCategory(@RequestBody StoreCategoryDTO storeCategoryDTO) {
        service.updateStoreCategory(storeCategoryDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 店铺分类删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removeStoreCategory(@RequestParam Integer id) {
        service.removeStoreCategoryById(id);
        return JsonResult.buildResultOk();
    }
}
