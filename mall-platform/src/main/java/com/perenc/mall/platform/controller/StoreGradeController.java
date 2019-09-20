package com.perenc.mall.platform.controller;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.platform.entity.dto.StoreGradeDTO;
import com.perenc.mall.platform.entity.model.StoreGradeDO;
import com.perenc.mall.platform.entity.vo.StoreGradeVO;
import com.perenc.mall.platform.service.IStoreGradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: StoreGradeController
 * @Description: 店铺等级相关
 *
 * @Author: GR
 * @Date: 2019-9-13 21:39 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-13     GR      		
 */
@Slf4j
@RestController
@RequestMapping("storeGrade")
public class StoreGradeController {

    @Autowired
    private IStoreGradeService service;


    /**
     * @description: 添加店铺等级
     * @param storeGradeDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addStoreGrade(@RequestBody @Valid StoreGradeDTO storeGradeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveStoreGrade(storeGradeDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取店铺等级列表，默认升序排列返回
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.StoreGradeVO>
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public List<StoreGradeDO> listStoreGrades() {
        List<StoreGradeDO> listStoreGradeDO = service.listStoreGrade();
        return listStoreGradeDO;
    }

    /**
     * @description: 根据指定ID获取StoreGrade信息
     * @param id
     * @return com.perenc.mall.platform.entity.vo.StoreGradeVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public StoreGradeVO getStoreGrade(@RequestParam Integer id) {
        StoreGradeVO storeGradeVO = service.getStoreGrade(id);
        if (null == storeGradeVO) {
            throw new BusinessException("ID为" + id + "的店铺等级不存在");
        }
        return storeGradeVO;
    }

    /**
     * @description: 店铺等级更新
     * @param storeGradeDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateStoreGrade(@RequestBody StoreGradeDTO storeGradeDTO) {
        service.updateStoreGrade(storeGradeDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 店铺等级删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removeStoreGrade(@RequestParam Integer id) {
        service.removeStoreGradeById(id);
        return JsonResult.buildResultOk();
    }

}
