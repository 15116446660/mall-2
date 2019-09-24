package com.perenc.mall.platform.controller;

import com.perenc.mall.platform.entity.vo.WithdrawalVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className WithdrawalController
 * @description 提现管理
 *
 * @author GR
 * @date 2019/9/24 13:57 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/24     GR     		
 */
@Slf4j
@RestController
@RequestMapping("withdrawal")
public class WithdrawalController {

    /**
     * @description 查看提现详情
     * @param withdrawalId
     * @return com.perenc.mall.platform.entity.vo.WithdrawalVO
     * @author GR
     * @date 2019/9/24
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public WithdrawalVO getWithdrawalDetails(Integer withdrawalId) {
        return WithdrawalVO.build();
    }

    /**
     * @description 提现列表
     * @param currentPage
     * @param pageSize
     * @return java.lang.Object
     * @author GR
     * @date 2019/9/24
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public Object listWithdrawal(Integer currentPage, Integer pageSize) {
        return "";
    }

    /**
     * @description 提现申请审核状态
     * @param withdrawalId
     * @param status
     * @param reason
     * @return com.perenc.mall.platform.entity.vo.WithdrawalVO
     * @author GR
     * @date 2019/9/24
     */
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    public WithdrawalVO auditWithdrawalDetails(Integer withdrawalId, Integer status,
                                               @RequestParam(required = false) String reason) {
        return WithdrawalVO.build();
    }
}
