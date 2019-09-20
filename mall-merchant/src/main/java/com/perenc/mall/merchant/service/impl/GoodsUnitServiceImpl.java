package com.perenc.mall.merchant.service.impl;

import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.merchant.entity.dto.GoodsUnitDTO;
import com.perenc.mall.merchant.entity.model.GoodsUnitDO;
import com.perenc.mall.merchant.entity.vo.GoodsUnitVO;
import com.perenc.mall.merchant.mapper.GoodsUnitMapper;
import com.perenc.mall.merchant.service.IGoodsUnitService;

import java.util.List;

/**
 * @ClassName: GoodsUnitServiceImpl
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/20 21:04 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
public class GoodsUnitServiceImpl extends BaseService<GoodsUnitMapper, GoodsUnitDO> implements IGoodsUnitService {
    @Override
    public void saveGoodsUnit(GoodsUnitDTO goodsUnitDTO) {
        
    }

    @Override
    public GoodsUnitVO getGoodsUnit(Integer id) {
        return null;
    }

    @Override
    public void removeGoodsUnitById(Integer id) {

    }

    @Override
    public List<GoodsUnitVO> listGoodsUnits() {
        return null;
    }

    @Override
    public void updateGoodsUnit(GoodsUnitDTO goodsUnitDTO) {

    }
}
