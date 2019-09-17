package com.perenc.mall.platform.controller;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.platform.entity.dto.AdvertiseDTO;
import com.perenc.mall.platform.entity.dto.BannerDTO;
import com.perenc.mall.platform.entity.model.AdvertiseDO;
import com.perenc.mall.platform.entity.model.BannerDO;
import com.perenc.mall.platform.service.IAdvertiseService;
import com.perenc.mall.platform.service.impl.AdvertiseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: AdvertiseController
 * @Description: 广告相关
 *
 * @Author: GR
 * @Date: 2019-9-14 17:15 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@RestController
@RequestMapping("ad")
public class AdvertiseController {

    @Autowired
    private IAdvertiseService service;

    /**
     * @description: 添加广告
     * @param advertiseDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addAdvertise(@RequestBody @Valid AdvertiseDTO advertiseDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveAdvertise(advertiseDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取广告列表，默认升序排列返回
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.BannerVO>
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<AdvertiseDO> listAdvertises() {
        return service.listAdvertises();
    }

    /**
     * @description: 根据指定ID获取Banner信息
     * @param id
     * @return com.perenc.mall.platform.entity.vo.BannerVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public AdvertiseDO getBanner(@RequestParam Integer id) {
        AdvertiseDO advertiseDO = service.getAdvertiseById(id);
        if (null == advertiseDO) {
            throw new BusinessException("ID为" + id + "的轮播图不存在");
        }
        return advertiseDO;
    }

    /**
     * @description: 广告更新
     * @param advertiseDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateBanner(@RequestBody AdvertiseDTO advertiseDTO) {
        service.updateAdvertise(advertiseDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 广告删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removeBanner(@RequestParam Integer id) {
        service.removeAdvertiseById(id);
        return JsonResult.buildResultOk();
    }

}
