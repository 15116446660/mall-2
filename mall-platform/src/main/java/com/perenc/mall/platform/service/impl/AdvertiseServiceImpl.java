package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.dto.AdvertiseDTO;
import com.perenc.mall.platform.entity.model.AdvertiseDO;
import com.perenc.mall.platform.entity.model.BannerDO;
import com.perenc.mall.platform.mapper.AdvertiseMapper;
import com.perenc.mall.platform.service.IAdvertiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<AdvertiseDO> listAdvertises() {
        QueryWrapper<AdvertiseDO> queryWrapper = new QueryWrapper<>();
        // 进行升序排序
        queryWrapper.orderByDesc(CommonFiledConstants.FILED_SORT);
        return super.listEntitys(queryWrapper);
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
