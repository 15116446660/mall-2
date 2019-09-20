package com.perenc.mall.merchant.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: GoodsUnitDTO
 * @Description: 商品单位
 *
 * @Author: GR
 * @Date: 2019/9/20 16:41 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class GoodsUnitDTO {
    private Integer id;
    private Integer storeId;
    @NotBlank(message = "name不能为空")
    private String name;
    @NotBlank(message = "desc不能为空")
    private String desc;
    private Integer status;
    private String remark;
}
