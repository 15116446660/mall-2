package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.dto.BannerDTO;
import com.perenc.mall.platform.entity.model.BannerDO;
import com.perenc.mall.platform.mapper.BannerMapper;
import com.perenc.mall.platform.service.IBannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: BannerServiceImpl
 * @Description: 轮播图业务类
 *
 * @Author: GR
 * @Date: 2019-9-13 19:56 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-13     GR      		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class BannerServiceImpl extends BaseService<BannerMapper, BannerDO> implements IBannerService {

    @Override
    public void saveBanner(BannerDTO bannerDTO) {
        BannerDO bannerDO = BannerDO.build();
        BeanUtils.copyProperties(bannerDTO, bannerDO);
        super.saveEntity(bannerDO);
    }

    @Override
    public BannerDO getBanner(Integer id) {
        return super.getEntityById(id);
    }

    @Override
    public void removeBannerById(Integer id) {
        super.removeEntityById(id);
    }


    @Override
    public List<BannerDO> listBanners() {
        QueryWrapper<BannerDO> queryWrapper = new QueryWrapper<>();
        // 进行升序排序
        queryWrapper.orderByAsc(CommonFiledConstants.FILED_SORT);
        List<BannerDO> listBanner = super.listEntitys(queryWrapper);
        return listBanner;
    }

    @Override
    public void updateBanner(BannerDTO bannerDTO) {
        if (null == bannerDTO.getId()) {
            throw new BusinessException("广告ID不能为空");
        }
        BannerDO bannerDO = BannerDO.build();
        BeanUtils.copyProperties(bannerDTO, bannerDO);
        super.updateEntity(bannerDO);
    }

    @Override
    public void updateBannerStatus(Integer id, Integer status) {
        BannerDO bannerDO = BannerDO.build().setId(id).setStatus(status);
        super.updateEntity(bannerDO);
    }
}
