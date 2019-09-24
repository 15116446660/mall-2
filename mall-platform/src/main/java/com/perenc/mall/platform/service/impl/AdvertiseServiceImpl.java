package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.context.BaseContextHandler;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.platform.entity.dto.AdvertiseDTO;
import com.perenc.mall.platform.entity.model.AdvertiseDO;
import com.perenc.mall.platform.entity.model.BannerDO;
import com.perenc.mall.platform.entity.vo.AdvertiseVO;
import com.perenc.mall.platform.mapper.AdvertiseMapper;
import com.perenc.mall.platform.service.IAdvertiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AdvertiseServiceImpl
 * @Description: 广告服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 14:37 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class AdvertiseServiceImpl extends BaseService<AdvertiseMapper, AdvertiseDO> implements IAdvertiseService {
    @Override
    public void saveAdvertise(AdvertiseDTO advertiseDTO) {
        AdvertiseDO advertiseDO = AdvertiseDO.build();
        BeanUtils.copyProperties(advertiseDTO, advertiseDO);
        super.saveEntity(advertiseDO);
    }

    @Override
    public AdvertiseDO getAdvertiseById(Integer id) {
        return super.getEntityById(id);
    }

    @Override
    public PageVO<AdvertiseVO> listAdvertises(Integer currentPage, Integer pageSize) {
        IPage<AdvertiseDO> iPage = super.listEntitysByPage(new Page<AdvertiseDO>(currentPage, pageSize), new QueryWrapper<AdvertiseDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())
                .orderByAsc(CommonFiledConstants.FILED_SORT));
        List<AdvertiseDO> advertiseDOList = iPage.getRecords();
        List<AdvertiseVO> advertiseVOList = new ArrayList<>();
        advertiseDOList.forEach(advertiseDO -> {
            AdvertiseVO advertiseVO = AdvertiseVO.build();
            BeanUtils.copyProperties(advertiseDO, advertiseVO);
            advertiseVOList.add(advertiseVO);
        });

        return PageVO.<AdvertiseVO>build()
                .setCurrentPage(currentPage)
                .setPageSize(pageSize)
                .setList(advertiseVOList)
                .setTotal(super.count(new QueryWrapper<AdvertiseDO>()
                        .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())));
    }

    @Override
    public void updateAdvertise(AdvertiseDTO advertiseDTO) {
        AdvertiseDO advertiseDO = AdvertiseDO.build();
        BeanUtils.copyProperties(advertiseDTO, advertiseDO);
        super.updateEntity(advertiseDO);
    }

    @Override
    public void removeAdvertiseById(Integer id) {
        super.removeEntityById(id);
    }
}
