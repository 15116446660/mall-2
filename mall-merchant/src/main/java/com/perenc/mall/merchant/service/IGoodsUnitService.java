package com.perenc.mall.merchant.service;

import com.perenc.mall.merchant.entity.dto.GoodsUnitDTO;
import com.perenc.mall.merchant.entity.vo.GoodsUnitVO;

import java.util.List;

/**
 * @ClassName: IGoodsUnitService
 * @Description: 商品单位服务类
 *
 * @Author: GR
 * @Date: 2019/9/20 20:58 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
public interface IGoodsUnitService {
    /**
     * @description: 添加商品单位
     * @param goodsUnitDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveGoodsUnit(GoodsUnitDTO goodsUnitDTO);

    /**
     * @description: 获取商品单位
     * @param id
     * @return com.perenc.mall.platform.entity.vo.GoodsUnitVO
     * @author: GR
     * @date: 2019/9/17
     */
    GoodsUnitVO getGoodsUnit(Integer id);


    /**
     * @description: 根据ID移除商品单位
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeGoodsUnitById(Integer id);


    /**
     * @description: 获取商品单位列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.GoodsUnitVO>
     * @author: GR
     * @date: 2019/9/17
     */
    List<GoodsUnitVO> listGoodsUnits();

    /**
     * @description: 获取商品单位列表
     * @param goodsUnitDTO
     * @return java.util.List<com.perenc.mall.platform.entity.model.GoodsUnitDO>
     * @author: GR
     * @date: 2019/9/17
     */
    void updateGoodsUnit(GoodsUnitDTO goodsUnitDTO);
}
