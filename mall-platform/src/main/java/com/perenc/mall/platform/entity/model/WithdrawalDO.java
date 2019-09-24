package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @className WithdrawalDO
 * @description 提现实体对象
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
@TableName(value = "common_withdrawal")
public class WithdrawalDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "store_id")
    private String storeId;
    @TableField(value = "amount_code")
    private String amountCode;
    private String amount;
    @TableField(value = "store_name")
    private String storeName;
    @TableField(value = "apply_user")
    private String applyUser;
    @TableField(value = "contact_phone")
    private String contactPhone;
    @TableField(value = "apply_email")
    private String applyEmail;
    @TableField(value = "apply_time")
    private String applyTime;
    private String bank;
    @TableField(value = "bank_card_number")
    private String bankCardNumber;
    private String remark;
    private String reason;
    private Integer status;
    @TableField(value = "create_user")
    private String createUser;
    @TableField(value = "update_user")
    private String updateUser;
    @TableField(value = "create_time")
    private String createTime;
    @TableField(value = "update_time")
    private String updateTime;
}
