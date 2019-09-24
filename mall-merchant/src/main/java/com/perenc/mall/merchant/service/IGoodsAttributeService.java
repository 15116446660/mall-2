package com.perenc.mall.merchant.service;

import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.GoodsAttributeDTO;
import com.perenc.mall.merchant.entity.model.GoodsAttributeDO;
import com.perenc.mall.merchant.entity.vo.GoodsAttributeVO;

import java.util.List;

/**
 * @ClassName: IGoodsAttributeService
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/20 20:59 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
public interface IGoodsAttributeService {
    /**
     * @description: 添加商品规格
     * @param goodsAttributeDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveGoodsAttribute(GoodsAttributeDTO goodsAttributeDTO);

    /**
     * @description: 获取商品规格
     * @param id
     * @return com.perenc.mall.platform.entity.vo.GoodsAttributeVO
     * @author: GR
     * @date: 2019/9/17
     */
    GoodsAttributeVO getGoodsAttribute(Integer id);


    /**
     * @description: 根据ID移除商品规格
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeGoodsAttributeById(Integer id);


    /**
     * @description: 获取商品规格列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.GoodsAttributeVO>
     * @author: GR
     * @date: 2019/9/17
     */
    List<GoodsAttributeVO> listGoodsAttributes();

    /**
     * @description: 获取商品规格列表
     * @param goodsAttributeDTO
     * @return java.util.List<com.perenc.mall.platform.entity.model.GoodsAttributeDO>
     * @author: GR
     * @date: 2019/9/17
     */
    void updateGoodsAttribute(GoodsAttributeDTO goodsAttributeDTO);
}
