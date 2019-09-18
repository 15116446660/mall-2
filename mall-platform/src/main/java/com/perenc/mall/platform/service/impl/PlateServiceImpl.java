package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.constant.PlateTypeConstants;
import com.perenc.mall.common.constant.PunctuationConstants;
import com.perenc.mall.common.constant.StatusConstants;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.common.util.ListUtils;
import com.perenc.mall.common.util.StringHelper;
import com.perenc.mall.platform.entity.dto.PlateDTO;
import com.perenc.mall.platform.entity.model.*;
import com.perenc.mall.platform.entity.vo.*;
import com.perenc.mall.platform.mapper.*;
import com.perenc.mall.platform.service.IPlateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PlateServiceImpl
 * @Description: 板块服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 14:00 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class PlateServiceImpl extends BaseService<PlateMapper, PlateDO> implements IPlateService {

    @Autowired
    private RelatedPlateGoodsCategoryMapper relatedPlateGoodsCategoryMapper;

    @Autowired
    private RelatedPlateActionMapper relatedPlateActionMapper;

    @Autowired
    private RelatedPlateStoreCategoryMapper relatedPlateStoreCategoryMapper;

    @Autowired
    private RelatedPlateGoodsMapper relatedPlateGoodsMapper;

    @Autowired
    private RelatedPlateAdvertiseMapper relatedPlateAdvertiseMapper;

    @Autowired
    private AdvertiseMapper advertiseMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ActionMapper actionMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    private StoreCategoryMapper storeCategoryMapper;

    @Override
    public void savePlate(PlateDTO plateDTO) {
        PlateDO plateDO = PlateDO.build();
        BeanUtils.copyProperties(plateDTO, plateDO);

        // 生成唯一ID
        String uuid = StringHelper.generateID();
        plateDO.setUuid(uuid);
        // 存储navMenuDO
        super.saveEntity(plateDO);
        // 通过uuid获取存储对象的id
        PlateDO onePlateDO = super.getEntityOne(new QueryWrapper<PlateDO>().eq("uuid", uuid));

        // 标准分类
        if (PlateTypeConstants.NORMAL == onePlateDO.getType()) {
            log.info("添加标准分类板块信息");
            saveRelatedPlateGoodsDO(plateDTO, onePlateDO);
        }

        // 店铺分类
        if (PlateTypeConstants.STORE == onePlateDO.getType()) {
            log.info("添加店铺分类板块信息");
            if (StringUtils.isBlank(plateDTO.getStoreCategoryIds())) {
                throw new BusinessException("请添加店铺分类ID");
            }
            String[] storeCategoryIdArray = plateDTO.getStoreCategoryIds().split(PunctuationConstants.COMMAS);
            List<Integer> storeCategoryIdList = ListUtils.convertIntegerList(storeCategoryIdArray);
            for (Integer id : storeCategoryIdList) {
                relatedPlateStoreCategoryMapper.insert(RelatedPlateStoreCategoryDO.build()
                        .setPlateId(onePlateDO.getId())
                        .setStoreCategoryId(id));
            }
        }

        // 商品分类
        if (PlateTypeConstants.GOODS == onePlateDO.getType()) {
            log.info("添加商品分类板块信息");
            if (StringUtils.isBlank(plateDTO.getGoodsCategoryIds())) {
                throw new BusinessException("请添加商品分类ID");
            }
            String[] goodsCategoryIdArray = plateDTO.getGoodsCategoryIds().split(PunctuationConstants.COMMAS);
            List<Integer> goodsCategoryIdList = ListUtils.convertIntegerList(goodsCategoryIdArray);
            for (Integer id : goodsCategoryIdList) {
                relatedPlateGoodsCategoryMapper.insert(RelatedPlateGoodsCategoryDO.build()
                        .setPlateId(onePlateDO.getId())
                        .setGoodsCategoryId(id));
            }
        }

        // 活动分类
        if (PlateTypeConstants.ACTION == onePlateDO.getType()) {
            log.info("添加活动分类板块信息");
            String actionId = plateDTO.getActionId();
            if (StringUtils.isBlank(actionId)) {
                throw new BusinessException("请添加活动ID");
            }
            if (!StringHelper.isNumeric(actionId)) {
                throw new BusinessException("请添加有效的活动ID，该ID不是一个数字");
            }
            relatedPlateActionMapper.insert(RelatedPlateActionDO.build()
                    .setPlateId(onePlateDO.getId())
                    .setActionId(Integer.valueOf(plateDTO.getActionId())));

            saveRelatedPlateGoodsDO(plateDTO, onePlateDO);
        }

        // 广告分类
        if (PlateTypeConstants.ADVERTISE == onePlateDO.getType()) {
            log.info("添加广告分类板块信息");
            String adId = plateDTO.getAdId();
            if (StringUtils.isBlank(adId)) {
                throw new BusinessException("请添加广告ID");
            }
            if (!StringHelper.isNumeric(adId)) {
                throw new BusinessException("请添加有效的广告ID，该ID不是一个数字");
            }
            relatedPlateAdvertiseMapper.insert(RelatedPlateAdvertiseDO.build()
                    .setPlateId(onePlateDO.getId())
                    .setAdId(Integer.valueOf(plateDTO.getAdId())));
        }

    }

    /**
     * @description: 保存RelatedPlateGoodsDO
     * @param plateDTO
     * @param plateDO
     * @return void
     * @author: GR
     * @date: 2019/9/18       
     */
    private void saveRelatedPlateGoodsDO(PlateDTO plateDTO, PlateDO plateDO) {
        if (StringUtils.isBlank(plateDTO.getGoodsIds())) {
            throw new BusinessException("请添加商品ID");
        }
        String[] goodsIdArray = plateDTO.getGoodsIds().split(PunctuationConstants.COMMAS);
        List<Integer> goodsIdList = ListUtils.convertIntegerList(goodsIdArray);
        for (Integer id : goodsIdList) {
            relatedPlateGoodsMapper.insert(RelatedPlateGoodsDO.build()
                    .setPlateId(plateDO.getId())
                    .setGoodsId(id));
        }
    }

    /**
     * @description: 设置相关商品
     * @param plateDO
     * @param plateVO
     * @return void
     * @author: GR
     * @date: 2019/9/18       
     */
    private void setRelatedPlateGoodsVO(PlateDO plateDO, PlateVO plateVO) {
        List<RelatedPlateGoodsDO> relatedPlateGoodsDOList = relatedPlateGoodsMapper.selectList(new QueryWrapper<RelatedPlateGoodsDO>()
                .eq("plate_id", plateDO.getId()).select("goods_id"));
        List<Integer> relatedPlateGoodsDOIdList = new ArrayList<>();
        relatedPlateGoodsDOList.forEach(relatedPlateGoodsDO ->
                relatedPlateGoodsDOIdList.add(relatedPlateGoodsDO.getGoodsId())
        );
        List<GoodsDO> goodsDOList = goodsMapper.selectList(new QueryWrapper<GoodsDO>().in("id", relatedPlateGoodsDOIdList));
        List<GoodsVO> goodsVOList = new ArrayList<>();
        goodsDOList.forEach(goodsDO -> {
            GoodsVO goodsVO = GoodsVO.build();
            BeanUtils.copyProperties(goodsDO, goodsVO);
            goodsVOList.add(goodsVO);
        });
        plateVO.setGoods(goodsVOList);
    }

    @Override
    public PlateVO getPlate(Integer id) {
        PlateDO plateDO = super.getEntityById(id);
        if (null == plateDO) {
            throw new BusinessException("板块ID=" + id + "不存在");
        }

        PlateVO plateVO = PlateVO.build();

        // 标准分类
        if (PlateTypeConstants.NORMAL == plateDO.getType()) {
            setRelatedPlateGoodsVO(plateDO, plateVO);
        }

        // 店铺分类
        if (PlateTypeConstants.STORE == plateDO.getType()) {
            List<RelatedPlateStoreCategoryDO> relatedPlateStoreCategoryDOList = relatedPlateStoreCategoryMapper
                    .selectList(new QueryWrapper<RelatedPlateStoreCategoryDO>().eq("plate_id", plateDO.getId())
                            .select("store_category_id"));
            List<Integer> relatedPlateStoreCategoryDOIdList = new ArrayList<>();
            relatedPlateStoreCategoryDOList.forEach(relatedPlateStoreCategoryDO ->
                    relatedPlateStoreCategoryDOIdList.add(relatedPlateStoreCategoryDO.getStoreCategoryId())
            );
            List<StoreCategoryDO> storeCategoryDOList = storeCategoryMapper.selectList(new QueryWrapper<StoreCategoryDO>()
                    .in("id", relatedPlateStoreCategoryDOIdList));
            List<StoreCategoryVO> storeCategoryVOList = new ArrayList<>();
            storeCategoryDOList.forEach(storeCategoryDO -> {
                StoreCategoryVO storeCategoryVO = StoreCategoryVO.build();
                BeanUtils.copyProperties(storeCategoryDO, storeCategoryVO);
                storeCategoryVOList.add(storeCategoryVO);
            });
            plateVO.setStoreCategory(storeCategoryVOList);
        }

        // 商品分类
        if (PlateTypeConstants.GOODS == plateDO.getType()) {
            List<RelatedPlateGoodsCategoryDO> relatedPlateGoodsCategoryDOList = relatedPlateGoodsCategoryMapper
                    .selectList(new QueryWrapper<RelatedPlateGoodsCategoryDO>()
                            .eq("plate_id", plateDO.getId()).select("goods_category_id"));
            List<Integer> relatedPlateGoodsCategoryDOIdList = new ArrayList<>();
            relatedPlateGoodsCategoryDOList.forEach(relatedPlateGoodsCategoryDO ->
                    relatedPlateGoodsCategoryDOIdList.add(relatedPlateGoodsCategoryDO.getGoodsCategoryId())
            );
            List<GoodsCategoryDO> goodsCategoryDOList = goodsCategoryMapper.selectList(new QueryWrapper<GoodsCategoryDO>()
                    .in("id", relatedPlateGoodsCategoryDOIdList));
            List<GoodsCategoryVO> goodsCategoryVOList = new ArrayList<>();
            goodsCategoryDOList.forEach(goodsCategoryDO -> {
                GoodsCategoryVO goodsCategoryVO = GoodsCategoryVO.build();
                BeanUtils.copyProperties(goodsCategoryDO, goodsCategoryVO);
                goodsCategoryVOList.add(goodsCategoryVO);
            });
            plateVO.setGoodsCategory(goodsCategoryVOList);
        }

        // 活动分类
        if (PlateTypeConstants.ACTION == plateDO.getType()) {
            RelatedPlateActionDO relatedPlateActionDO = relatedPlateActionMapper.selectOne(new QueryWrapper<RelatedPlateActionDO>()
                    .eq("plate_id", plateDO.getId())
                    .select("action_id"));
            ActionDO actionDO = actionMapper.selectById(relatedPlateActionDO.getActionId());
            ActionVO actionVO = ActionVO.build();
            BeanUtils.copyProperties(actionDO, actionVO);
            plateVO.setAction(actionVO);

            setRelatedPlateGoodsVO(plateDO, plateVO);
        }

        // 广告分类
        if (PlateTypeConstants.ADVERTISE == plateDO.getType()) {
            RelatedPlateAdvertiseDO relatedPlateAdvertiseDO = relatedPlateAdvertiseMapper.selectOne(new QueryWrapper<RelatedPlateAdvertiseDO>()
                    .eq("plate_id", plateDO.getId())
                    .select("ad_id"));
            AdvertiseDO advertiseDO = advertiseMapper.selectById(relatedPlateAdvertiseDO.getAdId());
            AdvertiseVO advertiseVO = AdvertiseVO.build();
            BeanUtils.copyProperties(advertiseDO, advertiseVO);
            plateVO.setAd(advertiseVO);
        }

        return plateVO;
    }

    @Override
    public void removePlateById(Integer id) {
        PlateDO plateDO = super.getEntityById(id);

        // 标准分类
        if (PlateTypeConstants.NORMAL == plateDO.getType()) {
            relatedPlateGoodsMapper.delete(new QueryWrapper<RelatedPlateGoodsDO>().eq("plate_id", plateDO.getId()));
        }

        // 店铺分类
        if (PlateTypeConstants.STORE == plateDO.getType()) {
            relatedPlateStoreCategoryMapper.delete(new QueryWrapper<RelatedPlateStoreCategoryDO>().eq("plate_id", plateDO.getId()));
        }

        // 商品分类
        if (PlateTypeConstants.GOODS == plateDO.getType()) {
            relatedPlateGoodsCategoryMapper.delete(new QueryWrapper<RelatedPlateGoodsCategoryDO>().eq("plate_id", plateDO.getId()));
        }

        // 活动分类
        if (PlateTypeConstants.ACTION == plateDO.getType()) {
            relatedPlateActionMapper.delete(new QueryWrapper<RelatedPlateActionDO>().eq("plate_id", plateDO.getId()));
            relatedPlateGoodsMapper.delete(new QueryWrapper<RelatedPlateGoodsDO>().eq("plate_id", plateDO.getId()));
        }

        // 广告分类
        if (PlateTypeConstants.ADVERTISE == plateDO.getType()) {
            relatedPlateAdvertiseMapper.delete(new QueryWrapper<RelatedPlateAdvertiseDO>().eq("plate_id", plateDO.getId()));
        }

        super.removeEntityById(id);

    }

    @Override
    public List<PlateDO> listPlate() {
        return super.listEntitys(new QueryWrapper<PlateDO>().orderByAsc("sort"));
    }

    @Override
    public void updatePlate(PlateDTO plateDTO) {

    }
}
