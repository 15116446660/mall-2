package com.perenc.mall.merchant.controller;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.merchant.entity.dto.GoodsCategoryDTO;
import com.perenc.mall.merchant.entity.vo.GoodsCategoryVO;
import com.perenc.mall.merchant.service.IGoodsCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: GoodsCategoryController
 * @Description: 商品分类
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
@RequestMapping("goodsCategory")
public class GoodsCategoryController {

    @Autowired
    private IGoodsCategoryService service;


    /**
     * @description: 添加商品分类
     * @param GoodsCategoryDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addGoodsCategory(@RequestBody @Valid GoodsCategoryDTO GoodsCategoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveGoodsCategory(GoodsCategoryDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取商品分类列表，默认升序排列返回
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.GoodsCategoryVO>
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public List<GoodsCategoryVO> listGoodsCategory() {
        List<GoodsCategoryVO> listGoodsCategoryDO = service.listGoodsCategory();
        return listGoodsCategoryDO;
    }

    /**
     * @description: 根据指定ID获取GoodsCategory信息
     * @param id
     * @return com.perenc.mall.platform.entity.vo.GoodsCategoryVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public GoodsCategoryVO getGoodsCategory(@RequestParam Integer id) {
        GoodsCategoryVO goodsCategoryVO = service.getGoodsCategory(id);
        if (null == goodsCategoryVO) {
            throw new BusinessException("ID为" + id + "的商品分类不存在");
        }
        return goodsCategoryVO;
    }

    /**
     * @description: 商品分类更新
     * @param goodsCategoryDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateGoodsCategory(@RequestBody GoodsCategoryDTO goodsCategoryDTO) {
        service.updateGoodsCategory(goodsCategoryDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 商品分类删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removeGoodsCategory(@RequestParam Integer id) {
        service.removeGoodsCategoryById(id);
        return JsonResult.buildResultOk();
    }
}
