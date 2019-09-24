package com.perenc.mall.platform.service;

import com.perenc.mall.platform.entity.dto.WithdrawalDTO;
import com.perenc.mall.platform.entity.vo.OrderVO;
import com.perenc.mall.platform.entity.vo.WithdrawalVO;

import java.util.List;

/**
 * @className IWithdrawalService
 * @description 提现服务类
 *
 * @author GR
 * @date 2019/9/24 14:53 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/24     GR     		
 */
public interface IWithdrawalService {
    /**
     * @description 根据提现Id获取提现详情
     * @param withdrawalId
     * @return com.perenc.mall.platform.entity.vo.WithdrawalVO
     * @author GR
     * @date 2019/9/24       
     */
    WithdrawalVO getWithdrawalDetails(Integer withdrawalId);


    /**
     * @description 分页查询提现相关订单列表
     * @param withdrawalId
     * @param currentPage
     * @param pageSize
     * @return java.util.List<com.perenc.mall.platform.entity.vo.OrderVO>
     * @author GR
     * @date 2019/9/24 16:07
     */
    List<OrderVO> listOrder(Integer withdrawalId, Integer currentPage, Integer pageSize);

    /**
     * @description 获取提现列表
     * @param currentPage
     * @param pageSize
     * @return java.util.List<com.perenc.mall.platform.entity.vo.WithdrawalVO>
     * @author GR
     * @date 2019/9/24
     */
    List<WithdrawalVO> listWithdrawal(Integer currentPage, Integer pageSize);


    /**
     * @description 审核提现
     * @param withdrawalDTO
     * @return void
     * @author GR
     * @date 2019/9/24 16:07
     */
    void auditWithdrawalDetails(WithdrawalDTO withdrawalDTO);
}
