package com.perenc.mall.merchant.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: ActionMoneyOffDO
 * @Description: 活动——满减
 *
 * @Author: GR
 * @Date: 2019/9/23 15:11 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "store_action_money_off")
public class ActionMoneyOffDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("store_id")
    private Integer storeId;
    private String name;
    @TableField(value = "start_time")
    private String startTime;
    @TableField(value = "end_time")
    private String endTime;
    @TableField(value = "condition_money")
    private String conditionMoney;
    @TableField(value = "minus_money")
    private String minusMoney;
    @TableField(value = "goods_type")
    private Integer goodsType;
    private Integer total;
    private Integer status;
    private String remark;
    @TableField(value = "create_user")
    private String createUser;
    @TableField(value = "update_user")
    private String updateUser;
    @TableField(value = "create_time")
    private String createTime;
    @TableField(value = "update_time")
    private String updateTime;
}
