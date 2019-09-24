package com.perenc.mall.platform.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @className WithdrawalDO
 * @description 前端展示提现数据信息
 *
 * @author GR
 * @date 2019/9/24 14:43 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/24     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class WithdrawalVO {
    private Integer id;
    private String storeId;
    private String amountCode;
    private String storeName;
    private String applyUser;
    private String contactPhone;
    private String applyEmail;
    private String applyTime;
    private String bank;
    private String bankCardNumber;
    private String remark;
    private String reason;
    private Integer status;
    private String createTime;
    private List<OrderVO> orderList;
}
