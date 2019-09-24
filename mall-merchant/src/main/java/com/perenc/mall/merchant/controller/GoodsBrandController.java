package com.perenc.mall.merchant.controller;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.GoodsBrandDTO;
import com.perenc.mall.merchant.entity.vo.GoodsBrandVO;
import com.perenc.mall.merchant.service.IGoodsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: GoodsBrandController
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/20 20:51 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Slf4j
@RestController
@RequestMapping("brand")
public class GoodsBrandController {

    @Autowired
    private IGoodsBrandService service;

    /**
     * @description: 添加商品规格
     * @param goodsBrandDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addGoodsBrand(@RequestBody @Valid GoodsBrandDTO goodsBrandDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveGoodsBrand(goodsBrandDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取商品规格列表，默认升序排列返回
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.GoodsBrandVO>
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public PageVO<GoodsBrandVO> listGoodsBrands(Integer currentPage, Integer pageSize) {
        PageVO<GoodsBrandVO> pageVO = service.listGoodsBrands(currentPage, pageSize);
        return pageVO;
    }

    /**
     * @description: 根据指定ID获取GoodsBrand信息
     * @param id
     * @return com.perenc.mall.platform.entity.vo.GoodsBrandVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public GoodsBrandVO getGoodsBrand(@RequestParam Integer id) {
        GoodsBrandVO goodsBrandVO = service.getGoodsBrand(id);
        if (null == goodsBrandVO) {
            throw new BusinessException("ID为" + id + "的商品规格不存在");
        }
        return goodsBrandVO;
    }

    /**
     * @description: 商品规格更新
     * @param goodsBrandDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateGoodsBrand(@RequestBody GoodsBrandDTO goodsBrandDTO) {
        service.updateGoodsBrand(goodsBrandDTO);
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
    public Result removeGoodsBrand(@RequestParam Integer id) {
        service.removeGoodsBrandById(id);
        return JsonResult.buildResultOk();
    }

}
