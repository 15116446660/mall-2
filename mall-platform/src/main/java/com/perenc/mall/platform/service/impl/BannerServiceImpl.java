package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.dto.BannerDTO;
import com.perenc.mall.platform.entity.model.BannerDO;
import com.perenc.mall.platform.mapper.BannerMapper;
import com.perenc.mall.platform.service.IBannerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
        BannerDO bannerDO = BannerDO.build()
                .setName(bannerDTO.getTitle())
                .setImgLogo(bannerDTO.getFileUrl())
                .setSort(Integer.valueOf(bannerDTO.getSort()))
                .setDesc(bannerDTO.getDesc())
                .setJumpType(Integer.valueOf(bannerDTO.getSkipType()))
                .setJumpContent(bannerDTO.getSkipContent());
        super.saveEntity(bannerDO);
    }

    @Override
    public List<BannerDO> listBanners() {
        QueryWrapper<BannerDO> queryWrapper = new QueryWrapper<>();
        // 进行升序排序
        queryWrapper.orderByAsc("sort");
        List<BannerDO> listBanner = super.listEntitys(queryWrapper);
        return listBanner;
    }

    @Override
    public void updateBanner(BannerDTO bannerDTO) {
        if (StringUtils.isBlank(bannerDTO.getId())) {
            throw new BusinessException("轮播图ID不能为空");
        }

        BannerDO bannerDO = BannerDO.build()
                .setId(Integer.valueOf(bannerDTO.getId()))
                .setName(bannerDTO.getTitle())
                .setImgLogo(bannerDTO.getFileUrl())
                .setSort(Integer.valueOf(bannerDTO.getSort()))
                .setDesc(bannerDTO.getDesc())
                .setJumpType(Integer.valueOf(bannerDTO.getSkipType()))
                .setJumpContent(bannerDTO.getSkipContent())
                .setEnable(Integer.valueOf(bannerDTO.getStatus()));
        super.updateEntity(bannerDO);
    }

    @Override
    public void updateBannerStatus(Integer id, Integer status) {
        BannerDO bannerDO = BannerDO.build().setId(id).setEnable(status);
        super.updateEntity(bannerDO);
    }
}
