package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.model.ComplainDO;
import com.perenc.mall.platform.entity.vo.ComplainVO;
import com.perenc.mall.platform.mapper.ComplainMapper;
import com.perenc.mall.platform.service.IComplainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @className ComplainServiceImpl
 * @description
 *
 * @author GR
 * @date 2019/9/24 18:50 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/24     GR     		
 */
@Slf4j
@Service
public class ComplainServiceImpl extends BaseService<ComplainMapper, ComplainDO> implements IComplainService {

    @Override
    public List<ComplainVO> listComplain(Integer currentPage, Integer pageSize) {
        IPage<ComplainDO> iPage = new Page<>(currentPage, pageSize);
        super.listEntitysByPage(iPage, null);
        List<ComplainDO> complainDOList = iPage.getRecords();
        List<ComplainVO> complainVOList = new ArrayList<>();
        complainDOList.forEach(complainDO -> {
            ComplainVO complainVO = ComplainVO.build();
            BeanUtils.copyProperties(complainDO, complainVO);
            complainVOList.add(complainVO);
        });
        return complainVOList;
    }

    @Override
    public ComplainVO getComplainDetails(Integer complainId) {
        if (null == complainId) {
            throw new BusinessException("投诉详情ID不能为空");
        }

        ComplainDO complainDO = super.getEntityById(complainId);
        if (null == complainDO) {
            throw new BusinessException("当前投诉ID不存在");
        }

        ComplainVO complainVO = ComplainVO.build();
        BeanUtils.copyProperties(complainDO, complainVO);
        
        return complainVO;
    }
}
