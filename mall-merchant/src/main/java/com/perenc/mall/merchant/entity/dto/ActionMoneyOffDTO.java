package com.perenc.mall.merchant.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: ActionMoneyOffDTO
 * @Description: 活动——满减
 *
 * @Author: GR
 * @Date: 2019/9/23 15:08 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class ActionMoneyOffDTO {
    private Integer id;
    @NotBlank(message = "name不能为空")
    private String name;
    @NotBlank(message = "startTime不能为空")
    private String startTime;
    @NotBlank(message = "endTime不能为空")
    private String endTime;
    @NotBlank(message = "conditionMoney不能为空")
    private String conditionMoney;
    @NotBlank(message = "minusMoney不能为空")
    private String minusMoney;
    @NotNull(message = "total不能为空")
    private Integer total;
    private Integer status;
    private String remark;
    @NotNull(message = "goodsType不能为空")
    private Integer goodsType;
    private String goodsList;
}
