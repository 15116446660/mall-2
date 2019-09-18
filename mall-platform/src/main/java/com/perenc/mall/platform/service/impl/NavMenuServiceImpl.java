package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.constant.JumpTypeConstants;
import com.perenc.mall.common.constant.PunctuationConstants;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.common.util.EntityUtils;
import com.perenc.mall.common.util.ListUtils;
import com.perenc.mall.common.util.StringHelper;
import com.perenc.mall.platform.entity.dto.NavMenuDTO;
import com.perenc.mall.platform.entity.model.*;
import com.perenc.mall.platform.entity.vo.AdvertiseVO;
import com.perenc.mall.platform.entity.vo.BannerVO;
import com.perenc.mall.platform.entity.vo.GoodsVO;
import com.perenc.mall.platform.entity.vo.NavMenuVO;
import com.perenc.mall.platform.mapper.*;
import com.perenc.mall.platform.service.INavMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private RelatedNavMenuGoodsMapper relatedNavMenuGoodsMapper;

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private AdvertiseMapper advertiseMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void saveNavMenu(NavMenuDTO navMenuDTO) {
        NavMenuDO navMenuDO = NavMenuDO.build();
        BeanUtils.copyProperties(navMenuDTO, navMenuDO);
        // 生成唯一ID
        String uuid = StringHelper.generateID();
        navMenuDO.setUuid(uuid);
        // 存储navMenuDO
        super.saveEntity(navMenuDO);

        QueryWrapper<NavMenuDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", uuid);
        NavMenuDO oneNavMenuDO = super.getEntityOne(queryWrapper);

        // 多商品类型
        if (JumpTypeConstants.MORE_GOODS == oneNavMenuDO.getSkipType()) {
            // 导航菜单-广告关系对象
            RelatedNavMenuAdvertiseDO relatedNavMenuAdvertiseDO = RelatedNavMenuAdvertiseDO.build()
                    .setNavMenuId(oneNavMenuDO.getId());
            String adId = navMenuDTO.getAdId();
            // adId不为空且必须为数字
            if (!StringUtils.isBlank(adId)) {
                if (StringHelper.isNumeric(adId)) {
                    relatedNavMenuAdvertiseDO.setAdId(Integer.valueOf(adId));
                    // 插入常驻字段信息
                    EntityUtils.build().setUpdatedInfo(relatedNavMenuAdvertiseDO);
                    // 存储导航菜单-广告关系表
                    relatedNavMenuAdvertiseMapper.insert(relatedNavMenuAdvertiseDO);
                } else {
                    throw new BusinessException("广告ID格式不匹配");
                }
            }

            // 导航菜单-轮播图关系对象
            // 若导航栏指向多商品页面，则可以不选择广告，但必须有轮播图和商品
            if (StringUtils.isBlank(navMenuDTO.getBannerId())) {
                throw new BusinessException("请选择对应的轮播图数据");
            }
            String[] bannerIds = navMenuDTO.getBannerId().split(PunctuationConstants.COMMAS);
            if (bannerIds.length > 0) {
                for (String id : bannerIds) {
                    boolean isNumber = StringHelper.isNumeric(id);
                    if (isNumber) {
                        RelatedNavMenuBannerDO relatedNavMenuBannerDO = RelatedNavMenuBannerDO.build()
                                .setNavMenuId(oneNavMenuDO.getId())
                                .setBannerId(Integer.valueOf(id));
                        // 插入常驻字段信息
                        EntityUtils.build().setUpdatedInfo(relatedNavMenuBannerDO);
                        // 导航菜单-轮播图关系表
                        relatedNavMenuBannerMapper.insert(relatedNavMenuBannerDO);
                    }
                }
            }

            // 导航菜单-商品关系对象
            // 若导航栏指向多商品页面，则可以不选择广告，但必须有轮播图和商品
            if (StringUtils.isBlank(navMenuDTO.getGoodsId())) {
                throw new BusinessException("请选择对应的商品数据");
            }
            String[] goodsIds = navMenuDTO.getGoodsId().split(PunctuationConstants.COMMAS);
            if (goodsIds.length > 0) {
                for (String id : goodsIds) {
                    boolean isNumber = StringHelper.isNumeric(id);
                    if (isNumber) {
                        RelatedNavMenuGoodsDO relatedNavMenuGoodsDO = RelatedNavMenuGoodsDO.build()
                                .setNavMenuId(oneNavMenuDO.getId())
                                .setGoodsId(Integer.valueOf(id));
                        // 插入常驻字段信息
                        EntityUtils.build().setUpdatedInfo(relatedNavMenuGoodsDO);
                        // 导航菜单-商品关系表
                        relatedNavMenuGoodsMapper.insert(relatedNavMenuGoodsDO);
                    }
                }
            }
        }

    }

    @Override
    public NavMenuVO getNavMenu(Integer id) {
        NavMenuDO navMenuDO = super.getEntityById(id);
        NavMenuVO navMenuVO = NavMenuVO.build();
        // 浅拷贝基础数据
        BeanUtils.copyProperties(navMenuDO, navMenuVO);
        // 多商品类型
        if (JumpTypeConstants.MORE_GOODS == navMenuDO.getSkipType()) {
            log.info("获取导航菜单ID={},跳转类型为多商品5", id);
            /*** 轮播图数据查询  start ***/
            QueryWrapper<RelatedNavMenuBannerDO> relatedNavMenuBannerQueryWrapper = new QueryWrapper<>();
            relatedNavMenuBannerQueryWrapper.eq("nav_menu_id", id);
            relatedNavMenuBannerQueryWrapper.select("banner_id");
            // 获取所有符合条件的轮播图id
            List<RelatedNavMenuBannerDO> relatedNavMenuBannerDOList = relatedNavMenuBannerMapper.selectList(relatedNavMenuBannerQueryWrapper);
            List<Integer> bannerDOIdList = new ArrayList<>();
            relatedNavMenuBannerDOList.forEach(relatedNavMenuBannerDO ->
                    bannerDOIdList.add(relatedNavMenuBannerDO.getBannerId())
            );
            // 根据BannerId数组统一查询符合条件的数据
            QueryWrapper<BannerDO> bannerQueryWrapper = new QueryWrapper<>();
            bannerQueryWrapper.in("id", bannerDOIdList);
            List<BannerDO> bannerDOList = bannerMapper.selectList(bannerQueryWrapper);
            List<BannerVO> bannerVOList = new ArrayList<>();
            bannerDOList.forEach(banerDO -> {
                BannerVO bannerVO = BannerVO.build();
                BeanUtils.copyProperties(banerDO, bannerVO);
                bannerVOList.add(bannerVO);
            });
            //赋值
            navMenuVO.setBannerList(bannerVOList);
            /*** 轮播图数据查询  end ***/


            /*** 广告数据查询  start ***/
            QueryWrapper<RelatedNavMenuAdvertiseDO> relatedNavMenuAdQueryWrapper = new QueryWrapper<>();
            relatedNavMenuAdQueryWrapper.eq("nav_menu_id", id);
            RelatedNavMenuAdvertiseDO relatedNavMenuAdvertiseDO = relatedNavMenuAdvertiseMapper.selectOne(relatedNavMenuAdQueryWrapper);
            // 导航栏未配置广告
            if (null != relatedNavMenuAdvertiseDO) {
                // 根据AdId数组统一查询符合条件的数据
                QueryWrapper<AdvertiseDO> adQueryWrapper = new QueryWrapper<>();
                adQueryWrapper.eq("id", relatedNavMenuAdvertiseDO.getAdId());
                AdvertiseDO advertiseDO = advertiseMapper.selectOne(adQueryWrapper);
                AdvertiseVO advertiseVO = AdvertiseVO.build();
                if (null != advertiseDO) {
                    BeanUtils.copyProperties(advertiseDO, advertiseVO);
                } else {
                    throw new BusinessException("根据ID=" + relatedNavMenuAdvertiseDO.getAdId() + "查询广告失败");
                }
                //赋值
                navMenuVO.setAd(advertiseVO);
            }

            /*** 广告数据查询  end ***/


            /*** 商品数据查询  start ***/
            QueryWrapper<RelatedNavMenuGoodsDO> relatedNavMenuGoodsDOQueryWrapper = new QueryWrapper<>();
            relatedNavMenuGoodsDOQueryWrapper.eq("nav_menu_id", id);
            relatedNavMenuGoodsDOQueryWrapper.select("goods_id");
            List<RelatedNavMenuGoodsDO> relatedNavMenuGoodsDOList = relatedNavMenuGoodsMapper.selectList(relatedNavMenuGoodsDOQueryWrapper);
            List<Integer> goodsIdList = new ArrayList<>();
            relatedNavMenuGoodsDOList.forEach(relatedNavMenuGoodsDO ->
                    goodsIdList.add(relatedNavMenuGoodsDO.getGoodsId())
            );
            // 根据goodsId数组统一查询符合条件的数据
            QueryWrapper<GoodsDO> goodsQueryWrapper = new QueryWrapper<>();
            goodsQueryWrapper.in("id", goodsIdList);
            List<GoodsDO> goodsDOList = goodsMapper.selectList(goodsQueryWrapper);
            List<GoodsVO> goodsVOList = new ArrayList<>();
            goodsDOList.forEach(goodsDO -> {
                GoodsVO goodsVO = GoodsVO.build();
                BeanUtils.copyProperties(goodsDO, goodsVO);
                goodsVOList.add(goodsVO);
            });
            // 赋值
            navMenuVO.setGoodsList(goodsVOList);
            /*** 商品数据查询  end ***/
        }

        return navMenuVO;
    }

    @Override
    public void removeNavMenuById(Integer id) {
        NavMenuDO navMenuDO = super.getEntityById(id);
        // 多商品类型
        if (JumpTypeConstants.MORE_GOODS == navMenuDO.getSkipType()) {
            log.info("删除导航菜单ID={},跳转类型为多商品5", id);
            /*** 轮播图数据删除  start ***/
            QueryWrapper<RelatedNavMenuBannerDO> relatedNavMenuBannerQueryWrapper = new QueryWrapper<>();
            relatedNavMenuBannerQueryWrapper.eq("nav_menu_id", id);
            relatedNavMenuBannerMapper.delete(relatedNavMenuBannerQueryWrapper);
            /*** 轮播图数据删除  end ***/

            /*** 广告数据删除  start ***/
            QueryWrapper<RelatedNavMenuAdvertiseDO> relatedNavMenuAdQueryWrapper = new QueryWrapper<>();
            relatedNavMenuAdQueryWrapper.eq("nav_menu_id", id);
            relatedNavMenuAdvertiseMapper.delete(relatedNavMenuAdQueryWrapper);
            /*** 广告数据删除  end ***/

            /*** 商品数据删除  start ***/
            QueryWrapper<RelatedNavMenuGoodsDO> relatedNavMenuGoodsDOQueryWrapper = new QueryWrapper<>();
            relatedNavMenuGoodsDOQueryWrapper.eq("nav_menu_id", id);
            relatedNavMenuGoodsMapper.delete(relatedNavMenuGoodsDOQueryWrapper);
            /*** 商品数据删除  end ***/
        }
        super.removeEntityById(id);
    }

    @Override
    public List<NavMenuDO> listNavMenus() {
        return super.listEntitys(new QueryWrapper<NavMenuDO>().orderByAsc("sort"));
    }

    @Override
    public void updateNavMenu(NavMenuDTO navMenuDTO) {
        NavMenuDO navMenuDO = NavMenuDO.build();
        BeanUtils.copyProperties(navMenuDTO, navMenuDO);

        if (null == navMenuDO.getId()) {
            throw new BusinessException("执行更新操作，ID不能为空");
        }

        QueryWrapper<NavMenuDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", navMenuDO.getId());
        NavMenuDO oneNavMenuDO = super.getEntityOne(queryWrapper);
        if (null == oneNavMenuDO) {
            throw new BusinessException("该导航菜单ID不存在");
        }

        // 多商品类型
        if (JumpTypeConstants.MORE_GOODS == oneNavMenuDO.getSkipType()) {
            /***** 导航菜单-广告关系对象  start ****/
            String adId = navMenuDTO.getAdId();
            if (!StringUtils.isBlank(adId) && StringHelper.isNumeric(adId)) {
                QueryWrapper<RelatedNavMenuAdvertiseDO> relatedNavMenuAdvertiseDOQueryWrapper = new QueryWrapper<>();
                relatedNavMenuAdvertiseDOQueryWrapper.eq("nav_menu_id", navMenuDO.getId());
                RelatedNavMenuAdvertiseDO relatedNavMenuAdvertiseDO = relatedNavMenuAdvertiseMapper.selectOne(relatedNavMenuAdvertiseDOQueryWrapper);
                if (null == relatedNavMenuAdvertiseDO) {
                    log.info("当前导航菜单未设置任何广告，允许执行更新插入该广告ID={}信息", adId);
                    relatedNavMenuAdvertiseDO = RelatedNavMenuAdvertiseDO.build()
                            .setNavMenuId(navMenuDO.getId())
                            .setAdId(Integer.valueOf(adId));
                    relatedNavMenuAdvertiseMapper.insert(relatedNavMenuAdvertiseDO);
                } else {
                    log.info("当前导航菜单已设置广告信息，执行广告更新操作");
                    relatedNavMenuAdvertiseDO.setNavMenuId(navMenuDO.getId()).setAdId(Integer.valueOf(adId));
                    relatedNavMenuAdvertiseMapper.update(relatedNavMenuAdvertiseDO, relatedNavMenuAdvertiseDOQueryWrapper);
                }
            }
            /***** 导航菜单-广告关系对象  end ****/

            /***** 导航菜单-轮播图关系对象  start ****/
            // 若导航栏指向多商品页面，则可以不选择广告，但必须有轮播图和商品
            if (StringUtils.isBlank(navMenuDTO.getBannerId())) {
                throw new BusinessException("请选择对应的轮播图数据");
            }
            // 要更新的轮播图ID数组
            String[] newRelatedNavMenuBannerDOIdArray = navMenuDTO.getBannerId().split(PunctuationConstants.COMMAS);
            if (newRelatedNavMenuBannerDOIdArray.length == 0) {
                throw new BusinessException("轮播图数据不能为空");
            }
            //更新新的轮播图数据ID
            List<Integer> newRelatedNavMenuBannerDOIdList = ListUtils.convertIntegerList(newRelatedNavMenuBannerDOIdArray);

            //查询符合条件的的轮播图数据
            QueryWrapper<RelatedNavMenuBannerDO> relatedNavMenuBannerDOQueryWrapper = new QueryWrapper<>();
            relatedNavMenuBannerDOQueryWrapper.eq("nav_menu_id", navMenuDO.getId());
            List<RelatedNavMenuBannerDO> relatedNavMenuBannerDOList = relatedNavMenuBannerMapper.selectList(relatedNavMenuBannerDOQueryWrapper);
            // 原有设置的轮播图ID数组
            List<Integer> oldRelatedNavMenuBannerDOIdList = new ArrayList<>();
            relatedNavMenuBannerDOList.forEach(relatedNavMenuBannerDO ->
                    oldRelatedNavMenuBannerDOIdList.add(relatedNavMenuBannerDO.getBannerId())
            );

            //要删除的轮播图ID
            Set<Integer> deleteRelatedNavMenuBannerDOIdList = ListUtils.getNotExistBySource(newRelatedNavMenuBannerDOIdList,
                    oldRelatedNavMenuBannerDOIdList);
            //要插入的轮播图ID
            Set<Integer> insertRelatedNavMenuBannerDOIdList = ListUtils.getNotExistBySource(oldRelatedNavMenuBannerDOIdList,
                    newRelatedNavMenuBannerDOIdList);

            for (Integer id : deleteRelatedNavMenuBannerDOIdList) {
                QueryWrapper<RelatedNavMenuBannerDO> deleteRelatedNavMenuBannerDOQueryWrapper = new QueryWrapper<>();
                relatedNavMenuBannerDOQueryWrapper.eq("nav_menu_id", navMenuDO.getId());
                relatedNavMenuBannerDOQueryWrapper.eq("banner_id", id);
                // 导航菜单-轮播图关系表
                relatedNavMenuBannerMapper.delete(deleteRelatedNavMenuBannerDOQueryWrapper);
            }

            for (Integer id : insertRelatedNavMenuBannerDOIdList) {
                RelatedNavMenuBannerDO relatedNavMenuBannerDO = RelatedNavMenuBannerDO.build()
                        .setNavMenuId(oneNavMenuDO.getId())
                        .setBannerId(Integer.valueOf(id));
                // 插入常驻字段信息
                EntityUtils.build().setCreateInfo(relatedNavMenuBannerDO);
                // 导航菜单-轮播图关系表
                relatedNavMenuBannerMapper.insert(relatedNavMenuBannerDO);
            }
            /***** 导航菜单-轮播图关系对象  end ****/

            /***** 导航菜单-商品关系对象  start ****/
            // 若导航栏指向多商品页面，则可以不选择广告，但必须有轮播图和商品
            if (StringUtils.isBlank(navMenuDTO.getGoodsId())) {
                throw new BusinessException("请选择对应的商品数据");
            }
            //要更新的商品数组ID
            String[] newRelatedNavMenuGoodsDOIdArray = navMenuDTO.getGoodsId().split(PunctuationConstants.COMMAS);
            if (newRelatedNavMenuGoodsDOIdArray.length == 0) {
                throw new BusinessException("商品数据不能为空");
            }
            //更新新的商品数据ID
            List<Integer> newRelatedNavMenuGoodsDOIdList = ListUtils.convertIntegerList(newRelatedNavMenuGoodsDOIdArray);

            //查询符合条件的的商品数据
            QueryWrapper<RelatedNavMenuGoodsDO> relatedNavMenuGoodsDOQueryWrapper = new QueryWrapper<>();
            relatedNavMenuGoodsDOQueryWrapper.eq("nav_menu_id", navMenuDO.getId());
            List<RelatedNavMenuGoodsDO> relatedNavMenuGoodsDOList = relatedNavMenuGoodsMapper.selectList(relatedNavMenuGoodsDOQueryWrapper);

            // 原有设置的上商品ID数组
            List<Integer> oldRelatedNavMenuGoodsDOList = new ArrayList<>();
            relatedNavMenuGoodsDOList.forEach(relatedNavMenuGoodsDO ->
                    oldRelatedNavMenuGoodsDOList.add(relatedNavMenuGoodsDO.getGoodsId()));

            //要删除的商品数组ID
            Set<Integer> deleteRelatedNavMenuGoodsDOList = ListUtils.getNotExistBySource(newRelatedNavMenuGoodsDOIdList,
                    oldRelatedNavMenuGoodsDOList);
            //要插入的商品数组ID
            Set<Integer> insertRelatedNavMenuGoodsDOList = ListUtils.getNotExistBySource(oldRelatedNavMenuGoodsDOList,
                    newRelatedNavMenuGoodsDOIdList);

            for (Integer id : deleteRelatedNavMenuGoodsDOList) {
                QueryWrapper<RelatedNavMenuGoodsDO> deleteRelatedNavMenuGoodsDOQueryWrapper = new QueryWrapper<>();
                deleteRelatedNavMenuGoodsDOQueryWrapper.eq("nav_menu_id", navMenuDO.getId());
                deleteRelatedNavMenuGoodsDOQueryWrapper.eq("banner_id", id);
                // 导航菜单-商品关系表
                relatedNavMenuGoodsMapper.delete(deleteRelatedNavMenuGoodsDOQueryWrapper);
            }

            for (Integer id : insertRelatedNavMenuGoodsDOList) {
                RelatedNavMenuGoodsDO relatedNavMenuGoodsDO = RelatedNavMenuGoodsDO.build()
                        .setNavMenuId(oneNavMenuDO.getId())
                        .setGoodsId(Integer.valueOf(id));
                // 插入常驻字段信息
                EntityUtils.build().setUpdatedInfo(relatedNavMenuGoodsDO);
                // 导航菜单-商品关系表
                relatedNavMenuGoodsMapper.insert(relatedNavMenuGoodsDO);
            }
            /***** 导航菜单-商品关系对象  end ****/
        }
        super.updateEntity(navMenuDO);
    }
}
