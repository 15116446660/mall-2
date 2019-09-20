package com.perenc.mall.merchant.service.impl;

import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.merchant.entity.dto.GoodsAttributeDTO;
import com.perenc.mall.merchant.entity.model.GoodsAttributeDO;
import com.perenc.mall.merchant.entity.vo.GoodsAttributeVO;
import com.perenc.mall.merchant.mapper.GoodsAttributeMapper;
import com.perenc.mall.merchant.service.IGoodsAttributeService;

import java.util.List;

/**
 * @ClassName: GoodsAttributeServiceImpl
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/20 21:02 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
public class GoodsAttributeServiceImpl extends BaseService<GoodsAttributeMapper, GoodsAttributeDO> implements IGoodsAttributeService {
    @Override
    public void saveGoodsAttribute(GoodsAttributeDTO goodsAttributeDTO) {

    }

    @Override
    public GoodsAttributeVO getGoodsAttribute(Integer id) {
        return null;
    }

    @Override
    public void removeGoodsAttributeById(Integer id) {

    }

    @Override
    public List<GoodsAttributeVO> listGoodsAttributes() {
        return null;
    }

    @Override
    public void updateGoodsAttribute(GoodsAttributeDTO goodsAttributeDTO) {

    }
}
