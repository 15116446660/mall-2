package com.perenc.mall.merchant.controller;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.GoodsAttributeDTO;
import com.perenc.mall.merchant.entity.vo.GoodsAttributeVO;
import com.perenc.mall.merchant.service.IGoodsAttributeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: GoodsAttributeController
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/20 20:52 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Slf4j
@RestController
@RequestMapping("attr")
public class GoodsAttributeController {

    @Autowired
    private IGoodsAttributeService service;

    /**
     * @description: 添加商品规格
     * @param goodsAttributeDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addGoodsAttribute(@RequestBody @Valid GoodsAttributeDTO goodsAttributeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveGoodsAttribute(goodsAttributeDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取商品规格列表，默认升序排列返回
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.GoodsAttributeVO>
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public List<GoodsAttributeVO> listGoodsAttributes() {
        List<GoodsAttributeVO> goodsAttributeVOList = service.listGoodsAttributes();
        return goodsAttributeVOList;
    }

    /**
     * @description: 根据指定ID获取GoodsAttribute信息
     * @param id
     * @return com.perenc.mall.platform.entity.vo.GoodsAttributeVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public GoodsAttributeVO getGoodsAttribute(@RequestParam Integer id) {
        GoodsAttributeVO goodsAttributeVO = service.getGoodsAttribute(id);
        if (null == goodsAttributeVO) {
            throw new BusinessException("ID为" + id + "的商品规格不存在");
        }
        return goodsAttributeVO;
    }

    /**
     * @description: 商品规格更新
     * @param goodsAttributeDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateGoodsAttribute(@RequestBody GoodsAttributeDTO goodsAttributeDTO) {
        service.updateGoodsAttribute(goodsAttributeDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 商品规格删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removeGoodsAttribute(@RequestParam Integer id) {
        service.removeGoodsAttributeById(id);
        return JsonResult.buildResultOk();
    }

}
