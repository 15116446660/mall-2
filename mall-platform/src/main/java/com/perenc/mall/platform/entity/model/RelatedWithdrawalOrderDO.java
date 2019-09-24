package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @className RelatedWithdrawalOrderDO
 * @description 提现订单
 *
 * @author GR
 * @date 2019/9/24 16:09 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/24     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "common_withdrawal_order")
public class RelatedWithdrawalOrderDO {
    @TableField("withdrawal_id")
    private Integer withdrawalId;
    @TableField("order_id")
    private Integer orderId;
    @TableField("create_user")
    private String createUser;
    @TableField("update_user")
    private String updateUser;
    @TableField("create_time")
    private String createTime;
    @TableField("update_time")
    private String updateTime;
}
