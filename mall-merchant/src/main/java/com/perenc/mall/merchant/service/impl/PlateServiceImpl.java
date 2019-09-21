package com.perenc.mall.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.constant.PlateTypeConstants;
import com.perenc.mall.common.constant.PunctuationConstants;
import com.perenc.mall.common.context.BaseContextHandler;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.common.util.EntityUtils;
import com.perenc.mall.common.util.ListUtils;
import com.perenc.mall.common.util.StringHelper;
import com.perenc.mall.merchant.entity.dto.PlateDTO;
import com.perenc.mall.merchant.entity.model.GoodsDO;
import com.perenc.mall.merchant.entity.model.PlateDO;
import com.perenc.mall.merchant.entity.model.RelatedPlateGoodsDO;
import com.perenc.mall.merchant.entity.vo.GoodsVO;
import com.perenc.mall.merchant.entity.vo.PlateVO;
import com.perenc.mall.merchant.mapper.*;
import com.perenc.mall.merchant.service.IPlateService;
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
    private RelatedPlateGoodsMapper relatedPlateGoodsMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public void savePlate(PlateDTO plateDTO) {

        PlateDO plateDO = PlateDO.build();
        BeanUtils.copyProperties(plateDTO, plateDO);

        // 存储navMenuDO
        super.saveEntity(plateDO);
        // 标准分类
        if (PlateTypeConstants.NORMAL == plateDO.getType()) {
            log.info("添加标准分类板块信息");
            saveRelatePlateGoodsDO(plateDTO, plateDO);
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

        super.removeEntityById(id);

    }

    @Override
    public List<PlateDO> listPlate() {
        return super.listEntitys(new QueryWrapper<PlateDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())
                .orderByAsc(CommonFiledConstants.FILED_SORT));
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

    }
}
