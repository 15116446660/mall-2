package com.perenc.mall.merchant.entity.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: RelatedActionMoneyOffGoodsDO
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/23 16:05 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "store_action_money_off_goods")
public class RelatedActionMoneyOffGoodsDO {
    @TableField("store_id")
    private Integer storeId;
    @TableField("action_money_off_id")
    private Integer actionMoneyOffId;
    @TableField("goods_id")
    private Integer goodsId;
    @TableField("create_user")
    private String createUser;
    @TableField("update_user")
    private String updateUser;
    @TableField("create_time")
    private String createTime;
    @TableField("update_time")
    private String updateTime;
}
