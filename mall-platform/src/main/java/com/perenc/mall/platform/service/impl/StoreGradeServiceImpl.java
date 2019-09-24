package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.platform.entity.dto.StoreGradeDTO;
import com.perenc.mall.platform.entity.model.StoreGradeDO;
import com.perenc.mall.platform.entity.vo.StoreCategoryVO;
import com.perenc.mall.platform.entity.vo.StoreGradeVO;
import com.perenc.mall.platform.entity.vo.StoreVO;
import com.perenc.mall.platform.mapper.StoreGradeMapper;
import com.perenc.mall.platform.service.IStoreGradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: StoreGradeServiceImpl
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/19 21:02 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/19     GR     		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class StoreGradeServiceImpl extends BaseService<StoreGradeMapper, StoreGradeDO> implements IStoreGradeService {
    @Override
    public void saveStoreGrade(StoreGradeDTO storeGradeDTO) {
        StoreGradeDO storeGradeDO = StoreGradeDO.build();
        BeanUtils.copyProperties(storeGradeDTO, storeGradeDO);
        super.saveEntity(storeGradeDO);
    }

    @Override
    public StoreGradeVO getStoreGrade(Integer id) {
        StoreGradeDO storeGradeDO = super.getEntityById(id);
        if (null == storeGradeDO) {
            throw new BusinessException("该店铺等级ID不存在");
        }
        StoreGradeVO storeGradeVO = StoreGradeVO.build();
        BeanUtils.copyProperties(storeGradeDO, storeGradeVO);
        return storeGradeVO;
    }

    @Override
    public void removeStoreGradeById(Integer id) {
        super.removeEntityById(id);
    }

    @Override
    public PageVO<StoreGradeVO> listStoreGrade(Integer currentPage, Integer pageSize) {
        IPage<StoreGradeDO> iPage = super.listEntitysByPage(new Page<StoreGradeDO>(currentPage, pageSize), null);
        List<StoreGradeDO> storeGradeDOList = iPage.getRecords();
        List<StoreGradeVO> storeGradeVOList = new ArrayList<>();
        storeGradeDOList.forEach(storeGradeDO -> {
            StoreGradeVO storeGradeVO = StoreGradeVO.build();
            BeanUtils.copyProperties(storeGradeDO, storeGradeVO);
            storeGradeVOList.add(storeGradeVO);
        });
        return PageVO.<StoreGradeVO>build()
                .setCurrentPage(currentPage)
                .setPageSize(pageSize)
                .setList(storeGradeVOList)
                .setTotal(super.count(null));
    }

    @Override
    public void updateStoreGrade(StoreGradeDTO storeGradeDTO) {
        if (null == storeGradeDTO.getId()) {
            throw new BusinessException("店铺等级ID不能为空");
        }
        StoreGradeDO storeGradeDO = StoreGradeDO.build();
        BeanUtils.copyProperties(storeGradeDTO, storeGradeDO);
        super.updateEntity(storeGradeDO);

    }
}
