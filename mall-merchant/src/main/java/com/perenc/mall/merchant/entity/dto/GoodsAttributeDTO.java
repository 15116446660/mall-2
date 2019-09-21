package com.perenc.mall.merchant.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: GoodsBrandDTO
 * @Description: 商品规格上传数据
 *
 * @Author: GR
 * @Date: 2019/9/20 20:07 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class GoodsAttributeDTO {
    private Integer id;
    private Integer storeId;
    @NotBlank(message = "name不能为空")
    private String name;
    private String desc;
    @NotBlank(message = "attrValues不能为空")
    private String attrValues;
    private Integer status;
    private String remark;
}
