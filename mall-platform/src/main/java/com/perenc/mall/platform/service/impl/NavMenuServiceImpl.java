package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.constant.JumpTypeConstants;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.dto.NavMenuDTO;
import com.perenc.mall.platform.entity.model.NavMenuDO;
import com.perenc.mall.platform.entity.model.RelatedNavMenuAdvertiseDO;
import com.perenc.mall.platform.entity.model.RelatedNavMenuBannerDO;
import com.perenc.mall.platform.mapper.NavMenuMapper;
import com.perenc.mall.platform.mapper.RelatedNavMenuAdvertiseMapper;
import com.perenc.mall.platform.mapper.RelatedNavMenuBannerMapper;
import com.perenc.mall.platform.service.INavMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: NavMenuServiceImpl
 * @Description: 导航栏服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 14:30 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class NavMenuServiceImpl extends BaseService<NavMenuMapper, NavMenuDO> implements INavMenuService {

    @Autowired
    private RelatedNavMenuAdvertiseMapper relatedNavMenuAdvertiseMapper;

    @Autowired
    private RelatedNavMenuBannerMapper relatedNavMenuBannerMapper;

    @Override
    public void saveNavMenu(NavMenuDTO navMenuDTO) {
        NavMenuDO navMenuDO = NavMenuDO.build();
        BeanUtils.copyProperties(navMenuDTO, navMenuDO);

        // 多商品类型
        if (JumpTypeConstants.MORE_GOODS == navMenuDO.getSkipType()) {
            // 导航菜单-广告关系对象
            RelatedNavMenuAdvertiseDO relatedNavMenuAdvertiseDO = RelatedNavMenuAdvertiseDO.build();
            if (null != navMenuDO.getAdId()) {
                relatedNavMenuAdvertiseDO.setAdId(navMenuDO.getAdId());
                // 存储导航菜单-广告关系表
                relatedNavMenuAdvertiseMapper.insert(relatedNavMenuAdvertiseDO);
            }

            // 导航菜单-轮播图关系对象
            String[] bannerIds = navMenuDO.getBannerId().split(",");
            if (bannerIds.length > 0) {
                for (String id : bannerIds) {
                    boolean isNumber = id.matches("[0-9]+");
                    if (isNumber) {
                        RelatedNavMenuBannerDO relatedNavMenuBannerDO = RelatedNavMenuBannerDO.build()
                                .setNavMenuId(navMenuDO.getId())
                                .setBannerId(Integer.valueOf(id));
                        // 导航菜单-轮播图关系表
                        relatedNavMenuBannerMapper.insert(relatedNavMenuBannerDO);
                    }
                }
            }
            // 存储navMenuDO
            super.saveEntity(navMenuDO);
        }

    }

    @Override
    public NavMenuDO getNavMenu(Integer id) {
        return super.getEntityById(id);
    }

    @Override
    public void removeNavMenuById(Integer id) {
        super.removeEntityById(id);
    }

    @Override
    public List<NavMenuDO> listNavMenus() {
        QueryWrapper<NavMenuDO> queryWrapper = new QueryWrapper<>();
        // 进行升序排序
        queryWrapper.orderByAsc("sort");
        List<NavMenuDO> listNavMenu = super.listEntitys(queryWrapper);
        return listNavMenu;
    }

    @Override
    public void updateNavMenu(NavMenuDTO navMenuDTO) {
        NavMenuDO navMenuDO = NavMenuDO.build();
        BeanUtils.copyProperties(navMenuDTO, navMenuDO);
        super.updateEntity(navMenuDO);
    }
}
