package com.perenc.mall.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.context.BaseContextHandler;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.GoodsBrandDTO;
import com.perenc.mall.merchant.entity.model.GoodsBrandDO;
import com.perenc.mall.merchant.entity.vo.GoodsBrandVO;
import com.perenc.mall.merchant.mapper.GoodsBrandMapper;
import com.perenc.mall.merchant.service.IGoodsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
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
    public PageVO<GoodsBrandVO> listGoodsBrands(Integer currentPage, Integer pageSize) {

        IPage<GoodsBrandDO> iPage = super.listEntitysByPage(new Page<GoodsBrandDO>(currentPage, pageSize), new QueryWrapper<GoodsBrandDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId()));
        List<GoodsBrandDO> goodsBrandDOList = iPage.getRecords();
        List<GoodsBrandVO> goodsBrandVOList = new ArrayList<>();
        goodsBrandDOList.forEach(goodsBrandDO -> {
            GoodsBrandVO goodsBrandVO = GoodsBrandVO.build();
            BeanUtils.copyProperties(goodsBrandDO, goodsBrandVO);
            goodsBrandVOList.add(goodsBrandVO);
        });
        return PageVO.<GoodsBrandVO>build()
                .setCurrentPage(currentPage)
                .setPageSize(pageSize)
                .setList(goodsBrandVOList)
                .setTotal(super.count(new QueryWrapper<GoodsBrandDO>()
                        .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())));
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
