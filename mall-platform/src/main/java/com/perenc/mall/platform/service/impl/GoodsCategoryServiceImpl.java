package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.constant.CommonConstants;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.dto.GoodsCategoryDTO;
import com.perenc.mall.platform.entity.model.GoodsCategoryDO;
import com.perenc.mall.platform.entity.vo.GoodsCategoryVO;
import com.perenc.mall.platform.mapper.GoodsCategoryMapper;
import com.perenc.mall.platform.service.IGoodsCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @ClassName: GoodsCategoryServiceImpl
 * @Description: 商品分类
 *
 * @Author: GR
 * @Date: 2019/9/19 14:02 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/19     GR     		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class GoodsCategoryServiceImpl extends BaseService<GoodsCategoryMapper, GoodsCategoryDO> implements IGoodsCategoryService {

    @Override
    public void saveGoodsCategory(GoodsCategoryDTO goodsCategoryDTO) {
        GoodsCategoryDO goodsCategoryDO = GoodsCategoryDO.build();
        BeanUtils.copyProperties(goodsCategoryDTO, goodsCategoryDO);
        // 不设置上级分类，默认此次分类为顶级分类，默认parent为0；
        if (null == goodsCategoryDO.getParentId()) {
            goodsCategoryDO.setParentId(CommonConstants.NUMBER_ZERO);
        }
        super.saveEntity(goodsCategoryDO);
    }

    @Override
    public GoodsCategoryVO getGoodsCategory(Integer id) {
        GoodsCategoryDO goodsCategoryDO = super.getEntityById(id);
        // 商品分类不存在
        if (null == goodsCategoryDO) {
            throw new BusinessException("请输入正确的商品分类ID");
        }
        GoodsCategoryVO goodsCategoryVO = GoodsCategoryVO.build();
        BeanUtils.copyProperties(goodsCategoryDO, goodsCategoryVO);
        // 若为顶级分类，则直接返回
        if (CommonConstants.NUMBER_ZERO == goodsCategoryDO.getParentId()) {
            return goodsCategoryVO;
        }

        // 获取父级分类
        GoodsCategoryDO parentGoodsCategoryDO = super.getEntityById(goodsCategoryDO.getParentId());
        if (null == parentGoodsCategoryDO) {
            throw new BusinessException("获取父级分类对象不存在");
        }
        goodsCategoryVO.setParentId(parentGoodsCategoryDO.getId());
        goodsCategoryVO.setParentName(parentGoodsCategoryDO.getName());

        return goodsCategoryVO;
    }

    @Override
    public void removeGoodsCategoryById(Integer id) {
        GoodsCategoryDO goodsCategoryDO = super.getEntityById(id);
        // 商品分类不存在
        if (null == goodsCategoryDO) {
            throw new BusinessException("该商品分类不存在");
        }

        //删除该分类下的所有子分类
        super.removeEntityByWrapper(new QueryWrapper<GoodsCategoryDO>()
                .eq(CommonFiledConstants.FILED_PARENT_ID, goodsCategoryDO.getId()));
        super.removeEntityById(goodsCategoryDO.getId());
    }

    @Override
    public List<GoodsCategoryVO> listGoodsCategory() {
        // 获取所有商品分类
        List<GoodsCategoryDO> goodsCategoryDOList = super.listEntitys(null);
        return recursiveQuery(goodsCategoryDOList, CommonConstants.NUMBER_ZERO);
    }


    /**
     * @description: 递归查询
     * @param parentId
     * @return void
     * @author: GR
     * @date: 2019/9/19
     */
    public List<GoodsCategoryVO> recursiveQuery(List<GoodsCategoryDO> goodsCategoryDOList, int parentId) {
        List<GoodsCategoryVO> parentList = new ArrayList<>();
        goodsCategoryDOList.forEach(goodsCategoryDO -> {
            if (parentId == goodsCategoryDO.getParentId()) {
                parentList.add(GoodsCategoryVO.build()
                        .setId(goodsCategoryDO.getId())
                        .setName(goodsCategoryDO.getName())
                        .setParentId(goodsCategoryDO.getParentId())
                        .setParentName(getParentName(goodsCategoryDOList, goodsCategoryDO.getParentId()))
                        .setChildren(recursiveQuery(goodsCategoryDOList, goodsCategoryDO.getId())));
            }
        });
        return parentList;
    }

    /**
     * @description: 获取父级分类名称
     * @param goodsCategoryDOList
     * @param parentId
     * @return java.lang.String
     * @author: GR
     * @date: 2019/9/19       
     */
    private String getParentName(List<GoodsCategoryDO> goodsCategoryDOList, int parentId) {
        ListIterator<GoodsCategoryDO> goodsCategoryDOListIterator = goodsCategoryDOList.listIterator();
        while (goodsCategoryDOListIterator.hasNext()) {
            GoodsCategoryDO goodsCategoryDO = goodsCategoryDOListIterator.next();
            if (parentId == goodsCategoryDO.getId()) {
                return goodsCategoryDO.getName();
            }
        }
        return "";
    }


    @Override
    public void updateGoodsCategory(GoodsCategoryDTO goodsCategoryDTO) {
        GoodsCategoryDO goodsCategoryDO = GoodsCategoryDO.build();
        BeanUtils.copyProperties(goodsCategoryDTO, goodsCategoryDO);
        // 不设置上级分类，默认此次分类为顶级分类，默认parent为0；
        if (null == goodsCategoryDO.getParentId()) {
            goodsCategoryDO.setParentId(CommonConstants.NUMBER_ZERO);
        }
        super.updateEntity(goodsCategoryDO);
    }

}
