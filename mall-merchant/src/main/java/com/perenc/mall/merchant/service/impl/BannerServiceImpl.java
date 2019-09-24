package com.perenc.mall.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.context.BaseContextHandler;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.common.util.RedisUtils;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.BannerDTO;
import com.perenc.mall.merchant.entity.model.BannerDO;
import com.perenc.mall.merchant.entity.vo.BannerVO;
import com.perenc.mall.merchant.mapper.BannerMapper;
import com.perenc.mall.merchant.service.IBannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public BannerVO getBanner(Integer id) {
        BannerDO bannerDO = super.getEntityById(id);
        if (null == bannerDO) {
            throw new BusinessException("当前轮播图ID不存在,请输入正确的ID");
        }
        BannerVO bannerVO = BannerVO.build();
        BeanUtils.copyProperties(bannerDO, bannerVO);
        return bannerVO;
    }

    @Override
    public void removeBannerById(Integer id) {
        super.removeEntityById(id);
    }


    @Override
    public PageVO<BannerVO> listBanners(Integer currentPage, Integer pageSize) {
        IPage<BannerDO> iPage = super.listEntitysByPage(new Page<BannerDO>(currentPage, pageSize), new QueryWrapper<BannerDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())
                .orderByAsc(CommonFiledConstants.FILED_SORT));
        List<BannerDO> bannerDOList = iPage.getRecords();
        List<BannerVO> bannerVOList = new ArrayList<>();
        bannerDOList.forEach(bannerDO -> {
            BannerVO bannerVO = BannerVO.build();
            BeanUtils.copyProperties(bannerDO, bannerVO);
            bannerVOList.add(bannerVO);
        });

        return PageVO.<BannerVO>build()
                .setCurrentPage(currentPage)
                .setPageSize(pageSize)
                .setList(bannerVOList)
                .setTotal(super.count(new QueryWrapper<BannerDO>()
                        .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())));
    }

    @Override
    public void updateBanner(BannerDTO bannerDTO) {
        if (null == bannerDTO.getId()) {
            throw new BusinessException("轮播图ID不能为空");
        }
        BannerDO bannerDO = BannerDO.build();
        BeanUtils.copyProperties(bannerDTO, bannerDO);
        super.updateEntity(bannerDO);
    }
}
