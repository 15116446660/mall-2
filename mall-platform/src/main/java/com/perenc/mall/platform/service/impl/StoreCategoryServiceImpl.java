package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.dto.StoreCategoryDTO;
import com.perenc.mall.platform.entity.model.StoreCategoryDO;
import com.perenc.mall.platform.entity.vo.StoreCategoryVO;
import com.perenc.mall.platform.mapper.StoreCategoryMapper;
import com.perenc.mall.platform.service.IStoreCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @ClassName: StoreCategoryServiceImpl
 * @Description: 店铺分类
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
public class StoreCategoryServiceImpl extends BaseService<StoreCategoryMapper, StoreCategoryDO> implements IStoreCategoryService {

    /**
     * @description: 默认顶级分类parent为0
     * @author: GR
     * @date: 2019/9/19 14:32
     */
    private final int TOP_Store_CATEGORY = 0;

    @Override
    public void saveStoreCategory(StoreCategoryDTO storeCategoryDTO) {
        StoreCategoryDO storeCategoryDO = StoreCategoryDO.build();
        BeanUtils.copyProperties(storeCategoryDTO, storeCategoryDO);
        // 不设置上级分类，默认此次分类为顶级分类，默认parent为0；
        if (null == storeCategoryDO.getParentId()) {
            storeCategoryDO.setParentId(TOP_Store_CATEGORY);
        }
        super.saveEntity(storeCategoryDO);
    }

    @Override
    public StoreCategoryVO getStoreCategory(Integer id) {
        StoreCategoryDO storeCategoryDO = super.getEntityById(id);
        // 店铺分类不存在
        if (null == storeCategoryDO) {
            throw new BusinessException("请输入正确的店铺分类ID");
        }
        StoreCategoryVO storeCategoryVO = StoreCategoryVO.build();
        BeanUtils.copyProperties(storeCategoryDO, storeCategoryVO);
        // 若为顶级分类，则直接返回
        if (TOP_Store_CATEGORY == storeCategoryDO.getParentId()) {
            return storeCategoryVO;
        }

        // 获取父级分类
        StoreCategoryDO parentStoreCategoryDO = super.getEntityById(storeCategoryDO.getParentId());
        if (null == parentStoreCategoryDO) {
            throw new BusinessException("获取父级分类对象不存在");
        }
        storeCategoryVO.setParentId(parentStoreCategoryDO.getId());
        storeCategoryVO.setParentName(parentStoreCategoryDO.getName());

        return storeCategoryVO;
    }

    @Override
    public void removeStoreCategoryById(Integer id) {
        StoreCategoryDO storeCategoryDO = super.getEntityById(id);
        // 店铺分类不存在
        if (null == storeCategoryDO) {
            throw new BusinessException("该店铺分类不存在");
        }

        //删除该分类下的所有子分类
        super.removeEntityByWrapper(new QueryWrapper<StoreCategoryDO>()
                .eq(CommonFiledConstants.FILED_PARENT_ID, storeCategoryDO.getId()));
        super.removeEntityById(storeCategoryDO.getId());
    }

    @Override
    public List<StoreCategoryVO> listStoreCategory() {
        // 获取所有店铺分类
        List<StoreCategoryDO> storeCategoryDOList = super.listEntitys(null);
        return recursiveQuery(storeCategoryDOList, TOP_Store_CATEGORY);
    }


    /**
     * @description: 递归查询
     * @param parentId
     * @return void
     * @author: GR
     * @date: 2019/9/19
     */
    public List<StoreCategoryVO> recursiveQuery(List<StoreCategoryDO> storeCategoryDOList, int parentId) {
        List<StoreCategoryVO> parentList = new ArrayList<>();
        storeCategoryDOList.forEach(storeCategoryDO -> {
            if (parentId == storeCategoryDO.getParentId()) {
                parentList.add(StoreCategoryVO.build()
                        .setId(storeCategoryDO.getId())
                        .setName(storeCategoryDO.getName())
                        .setParentId(storeCategoryDO.getParentId())
                        .setParentName(getParentName(storeCategoryDOList, storeCategoryDO.getParentId()))
                        .setChildren(recursiveQuery(storeCategoryDOList, storeCategoryDO.getId())));
            }
        });
        return parentList;
    }

    /**
     * @description: 获取父级分类名称
     * @param storeCategoryDOList
     * @param parentId
     * @return java.lang.String
     * @author: GR
     * @date: 2019/9/19       
     */
    private String getParentName(List<StoreCategoryDO> storeCategoryDOList, int parentId) {
        ListIterator<StoreCategoryDO> storeCategoryDOListIterator = storeCategoryDOList.listIterator();
        while (storeCategoryDOListIterator.hasNext()) {
            StoreCategoryDO storeCategoryDO = storeCategoryDOListIterator.next();
            if (parentId == storeCategoryDO.getId()) {
                return storeCategoryDO.getName();
            }
        }
        return "";
    }


    @Override
    public void updateStoreCategory(StoreCategoryDTO storeCategoryDTO) {
        StoreCategoryDO storeCategoryDO = StoreCategoryDO.build();
        BeanUtils.copyProperties(storeCategoryDTO, storeCategoryDO);
        // 不设置上级分类，默认此次分类为顶级分类，默认parent为0；
        if (null == storeCategoryDO.getParentId()) {
            storeCategoryDO.setParentId(TOP_Store_CATEGORY);
        }
        super.updateEntity(storeCategoryDO);
    }

}
