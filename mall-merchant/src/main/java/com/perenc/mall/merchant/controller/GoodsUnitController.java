package com.perenc.mall.merchant.controller;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.GoodsUnitDTO;
import com.perenc.mall.merchant.entity.vo.GoodsUnitVO;
import com.perenc.mall.merchant.service.IGoodsUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: GoodsUnitController
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
@RequestMapping("unit")
public class GoodsUnitController {


    @Autowired
    private IGoodsUnitService service;

    /**
     * @description: 添加商品单位
     * @param goodsUnitDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addGoodsUnit(@RequestBody @Valid GoodsUnitDTO goodsUnitDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveGoodsUnit(goodsUnitDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取商品单位列表，默认升序排列返回
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.GoodsUnitVO>
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public PageVO<GoodsUnitVO> listGoodsUnits(Integer currentPage, Integer pageSize) {
        PageVO<GoodsUnitVO> pageVO = service.listGoodsUnits(currentPage,pageSize);
        return pageVO;
    }

    /**
     * @description: 根据指定ID获取GoodsUnit信息
     * @param id
     * @return com.perenc.mall.platform.entity.vo.GoodsUnitVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public GoodsUnitVO getGoodsUnit(@RequestParam Integer id) {
        GoodsUnitVO goodsUnitVO = service.getGoodsUnit(id);
        if (null == goodsUnitVO) {
            throw new BusinessException("ID为" + id + "的商品单位不存在");
        }
        return goodsUnitVO;
    }

    /**
     * @description: 商品单位更新
     * @param goodsUnitDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateGoodsUnit(@RequestBody GoodsUnitDTO goodsUnitDTO) {
        service.updateGoodsUnit(goodsUnitDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 商品单位删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removeGoodsUnit(@RequestParam Integer id) {
        service.removeGoodsUnitById(id);
        return JsonResult.buildResultOk();
    }

}
