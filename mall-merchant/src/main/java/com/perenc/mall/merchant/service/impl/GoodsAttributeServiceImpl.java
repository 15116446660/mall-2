package com.perenc.mall.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.constant.CommonConstants;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.constant.PunctuationConstants;
import com.perenc.mall.common.context.BaseContextHandler;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.merchant.entity.dto.GoodsAttributeDTO;
import com.perenc.mall.merchant.entity.model.GoodsAttributeDO;
import com.perenc.mall.merchant.entity.vo.GoodsAttributeVO;
import com.perenc.mall.merchant.mapper.GoodsAttributeMapper;
import com.perenc.mall.merchant.service.IGoodsAttributeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: GoodsAttributeServiceImpl
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/20 21:02 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class GoodsAttributeServiceImpl extends BaseService<GoodsAttributeMapper, GoodsAttributeDO> implements IGoodsAttributeService {
    @Override
    public void saveGoodsAttribute(GoodsAttributeDTO goodsAttributeDTO) {
        GoodsAttributeDO superGoodsAttributeDO = GoodsAttributeDO.build();
        BeanUtils.copyProperties(goodsAttributeDTO, superGoodsAttributeDO);
        // 商品规格名称是否被占用
        if (!StringUtils.isBlank(superGoodsAttributeDO.getName())) {
            GoodsAttributeDO goodsAttributeDO = super.getEntityOne(new QueryWrapper<GoodsAttributeDO>()
                    .eq(CommonFiledConstants.FILED_NAME, superGoodsAttributeDO.getName()));
            if (null != goodsAttributeDO) {
                throw new BusinessException("商品规格名称已存在，请重新输入");
            }
        }

        // 保存实体对象时会将当前对象ID返回给原对象
        super.saveEntity(superGoodsAttributeDO);
        // 获取下级规格值名称
        String[] subAddrArray = goodsAttributeDTO.getAttrValues().split(PunctuationConstants.COMMAS);
        // 去重
        subAddrArray = filterRepeat(subAddrArray);

        // 分别存储每一个二级规格值，且设置下级规格值的上级规格值ID
        for (String subAttr : subAddrArray) {
            GoodsAttributeDO subGoodsAttributeDO = GoodsAttributeDO.build()
                    .setName(subAttr)
                    .setParentId(superGoodsAttributeDO.getId());
            super.saveEntity(subGoodsAttributeDO);
        }
    }

    @Override
    public GoodsAttributeVO getGoodsAttribute(Integer id) {
        GoodsAttributeDO goodsAttributeDO = super.getEntityById(id);
        if (null == goodsAttributeDO) {
            throw new BusinessException("该商品规格不存在，请输入正确的商品规格ID");
        }

        GoodsAttributeVO goodsAttributeVO = GoodsAttributeVO.build();
        // 判断是否是一级规格
        if (CommonConstants.NUMBER_ZERO == goodsAttributeDO.getParentId()) {
            //获取所有二级商品规格
            List<GoodsAttributeDO> subGoodsAttributeDOList = super.listEntitys(new QueryWrapper<GoodsAttributeDO>()
                    .eq(CommonFiledConstants.FILED_PARENT_ID, goodsAttributeDO.getId()));
            List<GoodsAttributeVO> subGoodsAttributeVOList = new ArrayList<>();
            subGoodsAttributeDOList.forEach(subGoodsAttributeDO -> {
                GoodsAttributeVO subGoodsAttributeVO = GoodsAttributeVO.build();
                BeanUtils.copyProperties(goodsAttributeDO, subGoodsAttributeVO);
                subGoodsAttributeVOList.add(subGoodsAttributeVO);
            });
            return goodsAttributeVO.setChildren(subGoodsAttributeVOList);
        }
        BeanUtils.copyProperties(goodsAttributeDO, goodsAttributeVO);
        return goodsAttributeVO;
    }

    @Override
    public void removeGoodsAttributeById(Integer id) {
        GoodsAttributeDO goodsAttributeDO = super.getEntityById(id);
        if (null == goodsAttributeDO) {
            throw new BusinessException("该商品规格不存在");
        }

        // 一级规格，则删除所有子规格
        if (CommonConstants.NUMBER_ZERO == goodsAttributeDO.getParentId()) {
            super.removeEntityByWrapper(new QueryWrapper<GoodsAttributeDO>()
                    .eq(CommonFiledConstants.FILED_PARENT_ID, goodsAttributeDO.getId()));
        }

        super.removeEntityById(id);
    }

    @Override
    public List<GoodsAttributeVO> listGoodsAttributes() {
        List<GoodsAttributeDO> goodsAttributeDOList = super.listEntitys(new QueryWrapper<GoodsAttributeDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId()));

        return recursiveQuery(goodsAttributeDOList, CommonConstants.NUMBER_ZERO);
    }

    /**
     * @description: 递归查询
     * @param parentId
     * @return void
     * @author: GR
     * @date: 2019/9/19
     */
    public List<GoodsAttributeVO> recursiveQuery(List<GoodsAttributeDO> goodsAttributeDOList, int parentId) {
        List<GoodsAttributeVO> parentList = new ArrayList<>();
        goodsAttributeDOList.forEach(goodsAttributeDO -> {
            if (parentId == goodsAttributeDO.getParentId()) {
                parentList.add(GoodsAttributeVO.build()
                        .setId(goodsAttributeDO.getId())
                        .setName(goodsAttributeDO.getName())
                        .setChildren(recursiveQuery(goodsAttributeDOList, goodsAttributeDO.getId())));
            }
        });
        return parentList;
    }

    @Override
    public void updateGoodsAttribute(GoodsAttributeDTO goodsAttributeDTO) {
        if (null == goodsAttributeDTO.getId()) {
            throw new BusinessException("商品规格ID不能为空");
        }

        // 获取新的下级规格值名称
        String[] newSubAddrArray = goodsAttributeDTO.getAttrValues().split(PunctuationConstants.COMMAS);
        // 去重
        String[] addrArray = filterRepeat(newSubAddrArray);

        // 所有子规格
        List<GoodsAttributeDO> subGoodsAttributeDOList = super.listEntitys(new QueryWrapper<GoodsAttributeDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())
                .eq(CommonFiledConstants.FILED_PARENT_ID, goodsAttributeDTO.getId()));

        // 要删除的子规格ID列表
        List<Integer> deleteSubAddrList = new ArrayList<>();
        subGoodsAttributeDOList.forEach(subGoodsAttributeDO -> {
            for (String subAttr : addrArray) {
                if (!subGoodsAttributeDO.getName().equals(subAttr)) {
                    deleteSubAddrList.add(subGoodsAttributeDO.getId());
                }
            }
        });
        // 移除所有子规格
        super.removeEntityBatchByIds(deleteSubAddrList);
        // 过滤已存在的，插入新的规格
        for (String subAttr : addrArray) {
            subGoodsAttributeDOList.forEach(subGoodsAttributeDO -> {
                // 若当前不存在该规格，则插入
                if (!subAttr.equals(subGoodsAttributeDO.getName())) {
                    GoodsAttributeDO goodsAttributeDO = GoodsAttributeDO.build()
                            .setName(subAttr)
                            .setParentId(subGoodsAttributeDO.getId());
                    super.saveEntity(goodsAttributeDO);
                }
            });
        }
    }

    /**
     * @description: 去重
     * @param strArray
     * @return java.lang.String[]
     * @author: GR
     * @date: 2019/9/23       
     */
    private String[] filterRepeat(String[] strArray) {
        List<String> strList = Arrays.asList(strArray);
        strList = strList.stream().distinct().collect(Collectors.toList());
        return (String[]) strList.toArray();
    }
}
