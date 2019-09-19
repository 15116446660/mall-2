package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.common.util.StringHelper;
import com.perenc.mall.platform.entity.dto.StoreDTO;
import com.perenc.mall.platform.entity.model.StoreCategoryDO;
import com.perenc.mall.platform.entity.model.StoreDO;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.platform.entity.vo.StoreCategoryVO;
import com.perenc.mall.platform.entity.vo.StoreVO;
import com.perenc.mall.platform.mapper.StoreCategoryMapper;
import com.perenc.mall.platform.mapper.StoreMapper;
import com.perenc.mall.platform.service.IStoreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MerchantServiceImpl
 * @Description: 店铺服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 14:29 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class StoreServiceImpl extends BaseService<StoreMapper, StoreDO> implements IStoreService {

    @Autowired
    private StoreCategoryMapper storeCategoryMapper;

    @Override
    public void saveStore(StoreDTO storeDTO) {
        StoreDO storeDO = StoreDO.build();
        BeanUtils.copyProperties(storeDTO, storeDO);

        super.saveEntity(storeDO);
    }

    @Override
    public StoreVO getStore(Integer id) {
        StoreDO storeDO = super.getEntityById(id);
        if (null == storeDO) {
            throw new BusinessException("当前店铺信息不在存在");
        }

        StoreVO storeVO = StoreVO.build();
        BeanUtils.copyProperties(storeDO, storeVO);

        // 获取店铺分类信息
        StoreCategoryDO storeCategoryDO = storeCategoryMapper.selectOne(new QueryWrapper<StoreCategoryDO>()
                .eq(CommonFiledConstants.FILED_ID, storeDO.getType()));
        if (null == storeCategoryDO) {
            throw new BusinessException("获取店铺分类信息为空");
        }

        StoreCategoryVO storeCategoryVO = StoreCategoryVO.build();
        BeanUtils.copyProperties(storeCategoryDO, storeCategoryVO);
        storeVO.setStoreCategory(storeCategoryVO);

        return storeVO;
    }

    @Override
    public void removeStoreById(Integer id) {
        super.removeEntityById(id);
    }

    @Override
    public PageVO listStore(int currentPage, int pageSize, String name, String applyName,
                            String applyPhone, Integer type, Integer status) {
        // 分页条件
        Page<StoreDO> page = new Page<>(currentPage, pageSize);
        // 查询条件
        QueryWrapper<StoreDO> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(name)) {
            // 默认模糊查询
            queryWrapper.like("name", name);
        }

        if (!StringUtils.isBlank(applyName)) {
            queryWrapper.eq("applyName", applyName);
        }

        if (!StringUtils.isBlank(applyPhone)) {
            queryWrapper.eq("applyPhone", applyPhone);
        }

        if (null != type) {
            queryWrapper.eq("type", type);
        }

        if (null != status) {
            queryWrapper.eq("status", status);
        }

        IPage<StoreDO> iPage = super.listEntitysByPage(page, queryWrapper);
        List<StoreDO> storeDOList = iPage.getRecords();
        List<StoreVO> storeVOList = new ArrayList<>();
        storeDOList.forEach(storeDO -> {
            StoreVO storeVO = StoreVO.build();
            BeanUtils.copyProperties(storeDO, storeVO);
            storeVOList.add(storeVO);
        });


        return PageVO.<StoreVO>build().setCurrentPage(currentPage)
                .setPageSize(pageSize)
                .setTotal(super.count(queryWrapper))
                .setList(storeVOList);
    }

    @Override
    public void updateStore(StoreDTO storeDTO) {
        StoreDO storeDO = StoreDO.build();
        BeanUtils.copyProperties(storeDTO, storeDO);

        super.updateEntity(storeDO);
    }
}
