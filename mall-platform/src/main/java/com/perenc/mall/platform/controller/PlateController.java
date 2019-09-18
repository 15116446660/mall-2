package com.perenc.mall.platform.controller;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.platform.entity.dto.PlateDTO;
import com.perenc.mall.platform.entity.model.PlateDO;
import com.perenc.mall.platform.entity.vo.PlateVO;
import com.perenc.mall.platform.service.IPlateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: PlateController
 * @Description: 板块相关
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
@RequestMapping("plate")
public class PlateController {
    @Autowired
    private IPlateService service;

    /**
     * @description: 添加板块信息
     * @param plateDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addPlate(@RequestBody @Valid PlateDTO plateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.savePlate(plateDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取板块列表，默认升序排列返回
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.model.PlateDO>
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<PlateDO> listAdvertises() {
        return service.listPlate();
    }

    /**
     * @description: 根据指定ID获取板块信息
     * @param id
     * @return com.perenc.mall.platform.entity.model.PlateVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public PlateVO getNavMenu(@RequestParam Integer id) {
        PlateVO plateVO = service.getPlate(id);
        if (null == plateVO) {
            throw new BusinessException("ID为" + id + "的板块不存在");
        }
        return plateVO;
    }

    /**
     * @description: 板块更新
     * @param plateDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updatePlate(@RequestBody PlateDTO plateDTO) {
        service.updatePlate(plateDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 板块删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removePlate(@RequestParam Integer id) {
        service.removePlateById(id);
        return JsonResult.buildResultOk();
    }
}
