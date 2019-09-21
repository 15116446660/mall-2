package com.perenc.mall.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.context.BaseContextHandler;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.merchant.entity.dto.GoodsUnitDTO;
import com.perenc.mall.merchant.entity.model.GoodsUnitDO;
import com.perenc.mall.merchant.entity.vo.GoodsUnitVO;
import com.perenc.mall.merchant.mapper.GoodsUnitMapper;
import com.perenc.mall.merchant.service.IGoodsUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class GoodsUnitServiceImpl extends BaseService<GoodsUnitMapper, GoodsUnitDO> implements IGoodsUnitService {
    @Override
    public void saveGoodsUnit(GoodsUnitDTO goodsUnitDTO) {
        GoodsUnitDO goodsUnitDO = GoodsUnitDO.build();
        BeanUtils.copyProperties(goodsUnitDTO, goodsUnitDO);
        GoodsUnitDO oldGoodsUnitDO = super.getEntityOne(new QueryWrapper<GoodsUnitDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())
                .eq(CommonFiledConstants.FILED_NAME, goodsUnitDO.getName()));
        if (null != oldGoodsUnitDO) {
            throw new BusinessException("当前品牌名称已存在");
        }
        super.saveEntity(goodsUnitDO);
    }

    @Override
    public GoodsUnitVO getGoodsUnit(Integer id) {
        GoodsUnitDO goodsUnitDO = super.getEntityById(id);
        if (null != goodsUnitDO) {
            throw new BusinessException("当前单位名称ID不存在");
        }
        GoodsUnitVO goodsUnitVO = GoodsUnitVO.build();
        BeanUtils.copyProperties(goodsUnitDO, goodsUnitVO);
        return goodsUnitVO;
    }

    @Override
    public void removeGoodsUnitById(Integer id) {
        super.removeEntityById(id);
    }

    @Override
    public List<GoodsUnitVO> listGoodsUnits() {
        List<GoodsUnitDO> goodsUnitDOList = super.listEntitys(new QueryWrapper<GoodsUnitDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId()));
        List<GoodsUnitVO> goodsUnitVOList = new ArrayList<>();
        goodsUnitDOList.forEach(goodsUnitDO -> {
            GoodsUnitVO goodsUnitVO = GoodsUnitVO.build();
            BeanUtils.copyProperties(goodsUnitDO, goodsUnitVO);
            goodsUnitVOList.add(goodsUnitVO);
        });
        return goodsUnitVOList;
    }

    @Override
    public void updateGoodsUnit(GoodsUnitDTO goodsUnitDTO) {
        GoodsUnitDO goodsUnitDO = GoodsUnitDO.build();
        if (null == goodsUnitDO) {
            throw new BusinessException("单位名称ID不能为空");
        }
        super.updateEntity(goodsUnitDO);
    }
}
