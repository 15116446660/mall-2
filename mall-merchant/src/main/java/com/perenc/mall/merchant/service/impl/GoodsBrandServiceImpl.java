package com.perenc.mall.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.context.BaseContextHandler;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.merchant.entity.dto.GoodsBrandDTO;
import com.perenc.mall.merchant.entity.model.GoodsBrandDO;
import com.perenc.mall.merchant.entity.vo.GoodsBrandVO;
import com.perenc.mall.merchant.mapper.GoodsBrandMapper;
import com.perenc.mall.merchant.service.IGoodsBrandService;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
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
        GoodsBrandDO goodsBrandDO = GoodsBrandDO.build();
        BeanUtils.copyProperties(goodsBrandDTO, goodsBrandDO);
        GoodsBrandDO oldGoodsBrandDO = super.getEntityOne(new QueryWrapper<GoodsBrandDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())
                .eq(CommonFiledConstants.FILED_NAME, goodsBrandDO.getName()));
        if (null != oldGoodsBrandDO) {
            throw new BusinessException("当前品牌名称已存在");
        }
        super.saveEntity(goodsBrandDO);
    }

    @Override
    public GoodsBrandVO getGoodsBrand(Integer id) {
        GoodsBrandDO goodsBrandDO = super.getEntityById(id);
        if (null != goodsBrandDO) {
            throw new BusinessException("当前品牌名称ID不存在");
        }
        GoodsBrandVO goodsBrandVO = GoodsBrandVO.build();
        BeanUtils.copyProperties(goodsBrandDO, goodsBrandVO);
        return goodsBrandVO;
    }

    @Override
    public void removeGoodsBrandById(Integer id) {
        super.removeEntityById(id);
    }

    @Override
    public List<GoodsBrandVO> listGoodsBrands() {
        List<GoodsBrandDO> goodsBrandDOList = super.listEntitys(new QueryWrapper<GoodsBrandDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId()));
        List<GoodsBrandVO> goodsBrandVOList = new ArrayList<>();
        goodsBrandDOList.forEach(goodsBrandDO -> {
            GoodsBrandVO goodsBrandVO = GoodsBrandVO.build();
            BeanUtils.copyProperties(goodsBrandDO, goodsBrandVO);
            goodsBrandVOList.add(goodsBrandVO);
        });
        return goodsBrandVOList;
    }

    @Override
    public void updateGoodsBrand(GoodsBrandDTO goodsBrandDTO) {
        GoodsBrandDO goodsBrandDO = GoodsBrandDO.build();
        if (null == goodsBrandDTO) {
            throw new BusinessException("品牌名称ID不能为空");
        }
        super.updateEntity(goodsBrandDO);
    }
}
