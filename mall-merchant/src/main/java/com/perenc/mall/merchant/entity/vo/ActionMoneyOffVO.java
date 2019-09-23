package com.perenc.mall.merchant.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName: ActionMoneyOffVO
 * @Description: 活动——满减
 *
 * @Author: GR
 * @Date: 2019/9/23 15:18 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class ActionMoneyOffVO {
    private Integer id;
    private String name;
    private String startTime;
    private String endTime;
    private String conditionMoney;
    private String minusMoney;
    private Integer total;
    private Integer status;
    private String remark;
    private String goodsType;
    private String createTime;
}
