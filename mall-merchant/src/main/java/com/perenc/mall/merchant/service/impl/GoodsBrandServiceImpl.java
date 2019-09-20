package com.perenc.mall.merchant.service.impl;

import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.merchant.entity.dto.GoodsBrandDTO;
import com.perenc.mall.merchant.entity.model.GoodsBrandDO;
import com.perenc.mall.merchant.entity.vo.GoodsBrandVO;
import com.perenc.mall.merchant.mapper.GoodsBrandMapper;
import com.perenc.mall.merchant.service.IGoodsBrandService;

import java.util.List;

/**
 * @ClassName: GoodsBrandServiceImpl
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/20 21:03 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
public class GoodsBrandServiceImpl extends BaseService<GoodsBrandMapper, GoodsBrandDO> implements IGoodsBrandService {
    @Override
    public void saveGoodsBrand(GoodsBrandDTO goodsBrandDTO) {

    }

    @Override
    public GoodsBrandVO getGoodsBrand(Integer id) {
        return null;
    }

    @Override
    public void removeGoodsBrandById(Integer id) {

    }

    @Override
    public List<GoodsBrandVO> listGoodsBrands() {
        return null;
    }

    @Override
    public void updateGoodsBrand(GoodsBrandDTO goodsBrandDTO) {

    }
}
