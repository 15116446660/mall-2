package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.constant.PlateTypeConstants;
import com.perenc.mall.common.constant.PunctuationConstants;
import com.perenc.mall.common.context.BaseContextHandler;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.common.util.EntityUtils;
import com.perenc.mall.common.util.ListUtils;
import com.perenc.mall.common.util.StringHelper;
import com.perenc.mall.common.vo.PageVO;
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
import java.util.Set;

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
        // 存储plateDO
        super.saveEntity(plateDO);

        // 标准分类
        if (PlateTypeConstants.NORMAL == plateDO.getType()) {
            log.info("添加标准分类板块信息");
            saveRelatePlateGoodsDO(plateDTO, plateDO);
        }

        // 店铺分类
        if (PlateTypeConstants.STORE == plateDO.getType()) {
            log.info("添加店铺分类板块信息");
            if (StringUtils.isBlank(plateDTO.getStoreCategoryIds())) {
                throw new BusinessException("请添加店铺分类ID");
            }
            String[] storeCategoryIdArray = plateDTO.getStoreCategoryIds().split(PunctuationConstants.COMMAS);
            List<Integer> storeCategoryIdList = ListUtils.convertIntegerList(storeCategoryIdArray);
            for (Integer id : storeCategoryIdList) {
                RelatedPlateStoreCategoryDO relatedPlateStoreCategoryDO = RelatedPlateStoreCategoryDO.build()
                        .setPlateId(plateDO.getId())
                        .setStoreCategoryId(id);
                EntityUtils.build().setCreateInfo(relatedPlateStoreCategoryDO);
                relatedPlateStoreCategoryMapper.insert(relatedPlateStoreCategoryDO);
            }
        }

        // 商品分类
        if (PlateTypeConstants.GOODS == plateDO.getType()) {
            log.info("添加商品分类板块信息");
            if (StringUtils.isBlank(plateDTO.getGoodsCategoryIds())) {
                throw new BusinessException("请添加商品分类ID");
            }
            String[] goodsCategoryIdArray = plateDTO.getGoodsCategoryIds().split(PunctuationConstants.COMMAS);
            List<Integer> goodsCategoryIdList = ListUtils.convertIntegerList(goodsCategoryIdArray);
            for (Integer id : goodsCategoryIdList) {
                RelatedPlateGoodsCategoryDO relatedPlateGoodsCategoryDO = RelatedPlateGoodsCategoryDO.build()
                        .setPlateId(plateDO.getId())
                        .setGoodsCategoryId(id);
                EntityUtils.build().setCreateInfo(relatedPlateGoodsCategoryDO);
                relatedPlateGoodsCategoryMapper.insert(relatedPlateGoodsCategoryDO);
            }
        }

        // 活动分类
        if (PlateTypeConstants.ACTION == plateDO.getType()) {
            log.info("添加活动分类板块信息");
            String actionId = plateDTO.getActionId();
            if (StringUtils.isBlank(actionId)) {
                throw new BusinessException("请添加活动ID");
            }
            if (!StringHelper.isNumeric(actionId)) {
                throw new BusinessException("请添加有效的活动ID，该ID不是一个数字");
            }
            RelatedPlateActionDO relatedPlateActionDO = RelatedPlateActionDO.build()
                    .setPlateId(plateDO.getId())
                    .setActionId(Integer.valueOf(plateDTO.getActionId()));
            EntityUtils.build().setCreateInfo(relatedPlateActionDO);
            relatedPlateActionMapper.insert(relatedPlateActionDO);

            saveRelatePlateGoodsDO(plateDTO, plateDO);
        }

        // 广告分类
        if (PlateTypeConstants.ADVERTISE == plateDO.getType()) {
            log.info("添加广告分类板块信息");
            String adId = plateDTO.getAdId();
            if (StringUtils.isBlank(adId)) {
                throw new BusinessException("请添加广告ID");
            }
            if (!StringHelper.isNumeric(adId)) {
                throw new BusinessException("请添加有效的广告ID，该ID不是一个数字");
            }
            RelatedPlateAdvertiseDO relatedPlateAdvertiseDO = RelatedPlateAdvertiseDO.build()
                    .setPlateId(plateDO.getId())
                    .setAdId(Integer.valueOf(plateDTO.getAdId()));
            EntityUtils.build().setCreateInfo(relatedPlateAdvertiseDO);
            relatedPlateAdvertiseMapper.insert(relatedPlateAdvertiseDO);
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
    private void saveRelatePlateGoodsDO(PlateDTO plateDTO, PlateDO plateDO) {
        if (StringUtils.isBlank(plateDTO.getGoodsIds())) {
            throw new BusinessException("请添加商品ID");
        }
        List<Integer> relatePlateGoodsDOIdList = ListUtils.getIntegerListByString(plateDTO.getGoodsIds(), PunctuationConstants.COMMAS);
        relatePlateGoodsDOIdList.forEach(id -> {
            RelatedPlateGoodsDO relatedPlateGoodsDO = RelatedPlateGoodsDO.build()
                    .setPlateId(plateDO.getId())
                    .setGoodsId(id);
            EntityUtils.build().setCreateInfo(relatedPlateGoodsDO);
            relatedPlateGoodsMapper.insert(relatedPlateGoodsDO);
        });
    }


    /**
     * @description: 获取板块相关的所有的商品ID
     * @param plateDO
     * @return java.util.List<java.lang.Integer>
     * @author: GR
     * @date: 2019/9/19
     */
    private List<Integer> getAllRelatedPlateGoodsDOId(PlateDO plateDO) {
        List<RelatedPlateGoodsDO> relatedPlateGoodsDOList = relatedPlateGoodsMapper.selectList(new QueryWrapper<RelatedPlateGoodsDO>()
                .eq(CommonFiledConstants.FILED_PLATE_ID, plateDO.getId()).select(CommonFiledConstants.FILED_GOODS_ID));
        List<Integer> relatedPlateGoodsDOIdList = new ArrayList<>();
        relatedPlateGoodsDOList.forEach(relatedPlateGoodsDO ->
                relatedPlateGoodsDOIdList.add(relatedPlateGoodsDO.getGoodsId())
        );
        return relatedPlateGoodsDOIdList;
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
        // 获取板块相关的所有的商品ID
        List<Integer> relatedPlateGoodsDOIdList = getAllRelatedPlateGoodsDOId(plateDO);
        // 获取板块相关的所有的商品详情
        List<GoodsDO> goodsDOList = goodsMapper.selectList(new QueryWrapper<GoodsDO>().in(CommonFiledConstants.FILED_ID, relatedPlateGoodsDOIdList));
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
                    .selectList(new QueryWrapper<RelatedPlateStoreCategoryDO>().eq(CommonFiledConstants.FILED_PLATE_ID, plateDO.getId())
                            .select(CommonFiledConstants.FILED_STORE_CATEGORY_ID));
            List<Integer> relatedPlateStoreCategoryDOIdList = new ArrayList<>();
            relatedPlateStoreCategoryDOList.forEach(relatedPlateStoreCategoryDO ->
                    relatedPlateStoreCategoryDOIdList.add(relatedPlateStoreCategoryDO.getStoreCategoryId())
            );
            List<StoreCategoryDO> storeCategoryDOList = storeCategoryMapper.selectList(new QueryWrapper<StoreCategoryDO>()
                    .in(CommonFiledConstants.FILED_ID, relatedPlateStoreCategoryDOIdList));
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
                            .eq(CommonFiledConstants.FILED_PLATE_ID, plateDO.getId()).select(CommonFiledConstants.FILED_GOODS_CATEGORY_ID));
            List<Integer> relatedPlateGoodsCategoryDOIdList = new ArrayList<>();
            relatedPlateGoodsCategoryDOList.forEach(relatedPlateGoodsCategoryDO ->
                    relatedPlateGoodsCategoryDOIdList.add(relatedPlateGoodsCategoryDO.getGoodsCategoryId())
            );
            List<GoodsCategoryDO> goodsCategoryDOList = goodsCategoryMapper.selectList(new QueryWrapper<GoodsCategoryDO>()
                    .in(CommonFiledConstants.FILED_ID, relatedPlateGoodsCategoryDOIdList));
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
                    .eq(CommonFiledConstants.FILED_PLATE_ID, plateDO.getId())
                    .select(CommonFiledConstants.FILED_ACTION_ID));
            ActionDO actionDO = actionMapper.selectById(relatedPlateActionDO.getActionId());
            ActionVO actionVO = ActionVO.build();
            BeanUtils.copyProperties(actionDO, actionVO);
            plateVO.setAction(actionVO);

            setRelatedPlateGoodsVO(plateDO, plateVO);
        }

        // 广告分类
        if (PlateTypeConstants.ADVERTISE == plateDO.getType()) {
            RelatedPlateAdvertiseDO relatedPlateAdvertiseDO = relatedPlateAdvertiseMapper.selectOne(new QueryWrapper<RelatedPlateAdvertiseDO>()
                    .eq(CommonFiledConstants.FILED_PLATE_ID, plateDO.getId())
                    .select(CommonFiledConstants.FILED_AD_ID));
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

        // 板块不存在
        if (null == plateDO) {
            throw new BusinessException("请输入正确的板块ID");
        }

        // 标准分类
        if (PlateTypeConstants.NORMAL == plateDO.getType()) {
            relatedPlateGoodsMapper.delete(new QueryWrapper<RelatedPlateGoodsDO>().eq(CommonFiledConstants.FILED_PLATE_ID, plateDO.getId()));
        }

        // 店铺分类
        if (PlateTypeConstants.STORE == plateDO.getType()) {
            relatedPlateStoreCategoryMapper.delete(new QueryWrapper<RelatedPlateStoreCategoryDO>().eq(CommonFiledConstants.FILED_PLATE_ID, plateDO.getId()));
        }

        // 商品分类
        if (PlateTypeConstants.GOODS == plateDO.getType()) {
            relatedPlateGoodsCategoryMapper.delete(new QueryWrapper<RelatedPlateGoodsCategoryDO>().eq(CommonFiledConstants.FILED_PLATE_ID, plateDO.getId()));
        }

        // 活动分类
        if (PlateTypeConstants.ACTION == plateDO.getType()) {
            relatedPlateActionMapper.delete(new QueryWrapper<RelatedPlateActionDO>().eq(CommonFiledConstants.FILED_PLATE_ID, plateDO.getId()));
            relatedPlateGoodsMapper.delete(new QueryWrapper<RelatedPlateGoodsDO>().eq(CommonFiledConstants.FILED_PLATE_ID, plateDO.getId()));
        }

        // 广告分类
        if (PlateTypeConstants.ADVERTISE == plateDO.getType()) {
            relatedPlateAdvertiseMapper.delete(new QueryWrapper<RelatedPlateAdvertiseDO>().eq(CommonFiledConstants.FILED_PLATE_ID, plateDO.getId()));
        }

        super.removeEntityById(id);

    }

    @Override
    public PageVO<PlateVO> listPlate(Integer currentPage, Integer pageSize) {
        IPage<PlateDO> iPage = super.listEntitysByPage(new Page<PlateDO>(currentPage, pageSize), null);
        List<PlateDO> plateDOList = iPage.getRecords();
        List<PlateVO> plateVOList = new ArrayList<>();
        plateDOList.forEach(plateDO -> {
            PlateVO plateVO = PlateVO.build();
            BeanUtils.copyProperties(plateDO, plateVO);
            plateVOList.add(plateVO);
        });

        return PageVO.<PlateVO>build()
                .setCurrentPage(currentPage)
                .setPageSize(pageSize)
                .setList(plateVOList)
                .setTotal(super.count(null));
    }

    @Override
    public void updatePlate(PlateDTO plateDTO) {
        Integer plateDTOId = plateDTO.getId();
        if (null == plateDTOId) {
            throw new BusinessException("板块ID不能为空");
        }

        PlateDO plateDO = super.getEntityById(plateDTOId);
        // 板块不存在
        if (null == plateDO) {
            throw new BusinessException("请输入正确的板块ID");
        }

        // 标准分类
        if (PlateTypeConstants.NORMAL == plateDO.getType()) {
            // 原有板块相关的所有的商品ID
            List<Integer> oldRelatedPlateGoodsDOIdList = getAllRelatedPlateGoodsDOId(plateDO);
            if (StringUtils.isBlank(plateDTO.getGoodsIds())) {
                throw new BusinessException("请选择相关的商品");
            }
            // 上传更新的商品ID列表
            List<Integer> newRelatedPlateGoodsDOIdList = ListUtils.getIntegerListByString(plateDTO.getGoodsIds(), PunctuationConstants.COMMAS);
            // 需要删除的商品ID列表
            Set<Integer> deleteRelatedPlateGoodsDOIdList = ListUtils.getNotExistBySource(newRelatedPlateGoodsDOIdList, oldRelatedPlateGoodsDOIdList);
            // 批量删除相关数据
            relatedPlateGoodsMapper.delete(new QueryWrapper<RelatedPlateGoodsDO>()
                    .in(CommonFiledConstants.FILED_GOODS_ID, deleteRelatedPlateGoodsDOIdList));
            // 需要新增的商品ID列表
            Set<Integer> insertRelatedPlateGoodsDOIdList = ListUtils.getNotExistBySource(oldRelatedPlateGoodsDOIdList, newRelatedPlateGoodsDOIdList);
            insertRelatedPlateGoodsDOIdList.forEach(id -> {
                RelatedPlateGoodsDO relatedPlateGoodsDO = RelatedPlateGoodsDO.build()
                        .setPlateId(plateDO.getId())
                        .setGoodsId(id);
                EntityUtils.build().setCreateInfo(relatedPlateGoodsDO);
                relatedPlateGoodsMapper.insert(relatedPlateGoodsDO);
            });
        }

        // 店铺分类
        if (PlateTypeConstants.STORE == plateDO.getType()) {
            List<RelatedPlateStoreCategoryDO> relatedPlateStoreCategoryDOList = relatedPlateStoreCategoryMapper.selectList(new QueryWrapper<RelatedPlateStoreCategoryDO>()
                    .eq(CommonFiledConstants.FILED_PLATE_ID, plateDO.getId())
                    .select(CommonFiledConstants.FILED_STORE_CATEGORY_ID));
            // 获取所有与板块相关的已存在店铺分类ID
            List<Integer> oldRelatedPlateStoreCategoryDOIdList = new ArrayList<>();
            relatedPlateStoreCategoryDOList.forEach(relatedPlateStoreCategoryDO ->
                    oldRelatedPlateStoreCategoryDOIdList.add(relatedPlateStoreCategoryDO.getStoreCategoryId())
            );
            // 上传更新的店铺分类ID列表
            List<Integer> newRelatedPlateStoreCategoryDOIdList = ListUtils.getIntegerListByString(plateDTO.getStoreCategoryIds(), PunctuationConstants.COMMAS);
            // 需要删除的店铺ID列表
            Set<Integer> deleteRelatedPlateStoreCategoryDOIdList = ListUtils.getNotExistBySource(newRelatedPlateStoreCategoryDOIdList, oldRelatedPlateStoreCategoryDOIdList);
            relatedPlateStoreCategoryMapper.delete(new QueryWrapper<RelatedPlateStoreCategoryDO>()
                    .in(CommonFiledConstants.FILED_STORE_CATEGORY_ID, deleteRelatedPlateStoreCategoryDOIdList));
            // 需要新增的店铺ID列表
            Set<Integer> insertRelatedPlateStoreCategoryDOIdList = ListUtils.getNotExistBySource(oldRelatedPlateStoreCategoryDOIdList, newRelatedPlateStoreCategoryDOIdList);
            insertRelatedPlateStoreCategoryDOIdList.forEach(id -> {
                RelatedPlateStoreCategoryDO relatedPlateStoreCategoryDO = RelatedPlateStoreCategoryDO.build()
                        .setPlateId(plateDO.getId())
                        .setStoreCategoryId(id);
                EntityUtils.build().setCreateInfo(relatedPlateStoreCategoryDO);
                relatedPlateStoreCategoryMapper.insert(relatedPlateStoreCategoryDO);
            });
        }

        // 商品分类
        if (PlateTypeConstants.GOODS == plateDO.getType()) {
            List<RelatedPlateGoodsCategoryDO> relatedPlateGoodsCategoryDOList = relatedPlateGoodsCategoryMapper.selectList(new QueryWrapper<RelatedPlateGoodsCategoryDO>()
                    .eq(CommonFiledConstants.FILED_PLATE_ID, plateDO.getId())
                    .select(CommonFiledConstants.FILED_GOODS_CATEGORY_ID));
            // 获取所有与板块相关的已存在商品分类ID
            List<Integer> oldRelatedPlateGoodsCategoryDOIdList = new ArrayList<>();
            relatedPlateGoodsCategoryDOList.forEach(relatedPlateGoodsCategoryDO ->
                    oldRelatedPlateGoodsCategoryDOIdList.add(relatedPlateGoodsCategoryDO.getGoodsCategoryId())
            );
            // 上传更新的商品分类ID列表
            List<Integer> newRelatedPlateGoodsCategoryDOIdList = ListUtils.getIntegerListByString(plateDTO.getGoodsCategoryIds(), PunctuationConstants.COMMAS);
            // 需要删除的商品ID列表
            Set<Integer> deleteRelatedPlateGoodsCategoryDOIdList = ListUtils.getNotExistBySource(newRelatedPlateGoodsCategoryDOIdList, oldRelatedPlateGoodsCategoryDOIdList);
            relatedPlateGoodsCategoryMapper.delete(new QueryWrapper<RelatedPlateGoodsCategoryDO>()
                    .in(CommonFiledConstants.FILED_GOODS_CATEGORY_ID, deleteRelatedPlateGoodsCategoryDOIdList));
            // 需要新增的商品ID列表
            Set<Integer> insertRelatedPlateGoodsCategoryDOIdList = ListUtils.getNotExistBySource(oldRelatedPlateGoodsCategoryDOIdList, newRelatedPlateGoodsCategoryDOIdList);
            insertRelatedPlateGoodsCategoryDOIdList.forEach(id -> {
                RelatedPlateGoodsCategoryDO relatedPlateGoodsCategoryDO = RelatedPlateGoodsCategoryDO.build()
                        .setPlateId(plateDO.getId())
                        .setGoodsCategoryId(id);
                EntityUtils.build().setCreateInfo(relatedPlateGoodsCategoryDO);
                relatedPlateGoodsCategoryMapper.insert(relatedPlateGoodsCategoryDO);
            });
        }

        // 活动分类
        if (PlateTypeConstants.ACTION == plateDO.getType()) {
            String actionId = plateDTO.getActionId();
            if (StringUtils.isBlank(actionId)) {
                throw new BusinessException("请添加活动ID");
            }
            if (!StringHelper.isNumeric(actionId)) {
                throw new BusinessException("请添加有效的活动ID，该ID不是一个数字");
            }
            RelatedPlateActionDO relatedPlateActionDO = relatedPlateActionMapper.selectOne(new QueryWrapper<RelatedPlateActionDO>()
                    .eq(CommonFiledConstants.FILED_PLATE_ID, plateDO.getId()));
            relatedPlateActionMapper.update(relatedPlateActionDO.setActionId(Integer.valueOf(actionId))
                    , new QueryWrapper<RelatedPlateActionDO>().eq(CommonFiledConstants.FILED_PLATE_ID, plateDO.getId()));

            // 原有板块相关的所有的商品ID
            List<Integer> oldRelatedPlateGoodsDOIdList = getAllRelatedPlateGoodsDOId(plateDO);
            if (StringUtils.isBlank(plateDTO.getGoodsIds())) {
                throw new BusinessException("请选择相关的商品");
            }
            // 上传更新的商品ID列表
            List<Integer> newRelatedPlateGoodsDOIdList = ListUtils.getIntegerListByString(plateDTO.getGoodsIds(), PunctuationConstants.COMMAS);
            // 需要删除的商品ID列表
            Set<Integer> deleteRelatedPlateGoodsDOIdList = ListUtils.getNotExistBySource(newRelatedPlateGoodsDOIdList, oldRelatedPlateGoodsDOIdList);
            // 批量删除相关数据
            relatedPlateGoodsMapper.delete(new QueryWrapper<RelatedPlateGoodsDO>()
                    .in(CommonFiledConstants.FILED_GOODS_ID, deleteRelatedPlateGoodsDOIdList));
            // 需要新增的商品ID列表
            Set<Integer> insertRelatedPlateGoodsDOIdList = ListUtils.getNotExistBySource(oldRelatedPlateGoodsDOIdList, newRelatedPlateGoodsDOIdList);
            insertRelatedPlateGoodsDOIdList.forEach(id -> {
                RelatedPlateGoodsDO relatedPlateGoodsDO = RelatedPlateGoodsDO.build()
                        .setPlateId(plateDO.getId())
                        .setGoodsId(id);
                EntityUtils.build().setCreateInfo(relatedPlateGoodsDO);
                relatedPlateGoodsMapper.insert(relatedPlateGoodsDO);
            });
        }

        // 广告分类
        if (PlateTypeConstants.ADVERTISE == plateDO.getType()) {
            String adId = plateDTO.getAdId();
            if (StringUtils.isBlank(adId)) {
                throw new BusinessException("请添加广告ID");
            }
            if (!StringHelper.isNumeric(adId)) {
                throw new BusinessException("请添加有效的广告ID，该ID不是一个数字");
            }
            RelatedPlateAdvertiseDO relatedPlateAdvertiseDO = RelatedPlateAdvertiseDO.build()
                    .setPlateId(plateDO.getId())
                    .setAdId(Integer.valueOf(plateDTO.getAdId()));
            EntityUtils.build().setUpdatedInfo(relatedPlateAdvertiseDO);
            relatedPlateAdvertiseMapper.update(relatedPlateAdvertiseDO, new QueryWrapper<RelatedPlateAdvertiseDO>()
                    .eq(CommonFiledConstants.FILED_PLATE_ID, plateDO.getId()));
        }

    }
}
