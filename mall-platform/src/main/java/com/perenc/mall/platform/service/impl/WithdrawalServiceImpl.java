package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.dto.WithdrawalDTO;
import com.perenc.mall.platform.entity.model.RelatedWithdrawalOrderDO;
import com.perenc.mall.platform.entity.model.WithdrawalDO;
import com.perenc.mall.platform.entity.vo.OrderVO;
import com.perenc.mall.platform.entity.vo.WithdrawalVO;
import com.perenc.mall.platform.mapper.RelatedWithdrawalOrderMapper;
import com.perenc.mall.platform.mapper.WithdrawalMapper;
import com.perenc.mall.platform.service.IWithdrawalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @className WithdrawalerviceImpl
 * @description
 *
 * @author GR
 * @date 2019/9/24 14:53 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/24     GR     		
 */
@Slf4j
@Service
public class WithdrawalServiceImpl extends BaseService<WithdrawalMapper, WithdrawalDO> implements IWithdrawalService {

    @Autowired
    private RelatedWithdrawalOrderMapper relatedWithdrawalOrderMapper;

    @Override
    public WithdrawalVO getWithdrawalDetails(Integer withdrawalId) {
        WithdrawalDO withdrawalDO = super.getEntityById(withdrawalId);
        if (null == withdrawalDO) {
            throw new BusinessException("当前提现记录ID不存在");
        }

        WithdrawalVO withdrawalVO = WithdrawalVO.build();
        BeanUtils.copyProperties(withdrawalDO, withdrawalVO);


        return withdrawalVO;
    }

    @Override
    public List<OrderVO> listOrder(Integer withdrawalId, Integer currentPage, Integer pageSize) {
        QueryWrapper<RelatedWithdrawalOrderDO> relatedWithdrawalOrderDOQueryWrapper = new QueryWrapper<RelatedWithdrawalOrderDO>()
                .eq(CommonFiledConstants.FILED_WITHDRAWAL_ID, withdrawalId)
                .select(CommonFiledConstants.FILED_ORDER_ID);
        IPage<RelatedWithdrawalOrderDO> iPage = new Page<>(currentPage, pageSize);
        relatedWithdrawalOrderMapper.selectPage(iPage, relatedWithdrawalOrderDOQueryWrapper);
        List<RelatedWithdrawalOrderDO> relatedWithdrawalOrderDOList = iPage.getRecords();
        List<Integer> orderIdList = new ArrayList<>();
        // 查询所有相关联的订单ID
        relatedWithdrawalOrderDOList.forEach(relatedWithdrawalOrderDO -> orderIdList.add(relatedWithdrawalOrderDO.getOrderId()));


        return null;
    }

    @Override
    public List<WithdrawalVO> listWithdrawal(Integer currentPage, Integer pageSize) {


        return null;
    }

    @Override
    public void auditWithdrawalDetails(WithdrawalDTO withdrawalDTO) {
        if (null == withdrawalDTO.getId()) {
            throw new BusinessException("提现ID不能为空");
        }

        WithdrawalDO withdrawalDO = WithdrawalDO.build();
        BeanUtils.copyProperties(withdrawalDTO, withdrawalDO);
        super.updateEntity(withdrawalDO);
    }

}
