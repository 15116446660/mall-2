package com.perenc.mall.platform.controller;

import com.perenc.mall.common.controller.BaseController;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.platform.entity.dto.BannerDTO;
import com.perenc.mall.platform.entity.model.BannerDO;
import com.perenc.mall.platform.entity.vo.BannerVO;
import com.perenc.mall.platform.service.impl.BannerServiceImpl;
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
 * @ClassName: BannerController
 * @Description: 轮播图相关
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
@RequestMapping("banner")
public class BannerController {

    @Autowired
    private BannerServiceImpl service;


    /**
     * @description: 添加轮播图
     * @param bannerDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addBanner(@RequestBody @Valid BannerDTO bannerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveBanner(bannerDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取轮播图列表，默认升序排列返回
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.BannerVO>
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<BannerVO> listBanners() {
        List<BannerDO> listBannerDO = service.listBanners();
        List<BannerVO> listBannerVO = new ArrayList<>();
        Iterator<BannerDO> iterator = listBannerDO.iterator();
        while (iterator.hasNext()) {
            BannerVO bannerVO = BannerVO.build();
            BeanUtils.copyProperties(iterator.next(), bannerVO);
            listBannerVO.add(bannerVO);
        }
        return listBannerVO;
    }

    /**
     * @description: 根据指定ID获取Banner信息
     * @param id
     * @return com.perenc.mall.platform.entity.vo.BannerVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public BannerVO getBanner(@RequestParam Integer id) {
        BannerDO bannerDO = service.getEntityById(id);
        if (null == bannerDO) {
            throw new BusinessException("ID为" + id + "的轮播图不存在");
        }

        BannerVO bannerVO = BannerVO.build();
        BeanUtils.copyProperties(bannerDO, bannerVO);
        return bannerVO;
    }

    /**
     * @description: 更新Banner启用状态
     * @param id
     * @param status
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    public Result updateBannerStatus(@RequestParam Integer id, @RequestParam Integer status) {
        service.updateBannerStatus(id, status);
        return JsonResult.buildResultOk();
    }


    /**
     * @description: 轮播图更新
     * @param bannerDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateBanner(@RequestBody BannerDTO bannerDTO) {
        service.updateBanner(bannerDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 轮播图删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removeBanner(@RequestParam Integer id) {
        service.removeEntityById(id);
        return JsonResult.buildResultOk();
    }

}
