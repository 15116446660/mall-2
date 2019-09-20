package com.perenc.mall.merchant.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: GoodsBrandDTO
 * @Description: 商品品牌上传数据
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
public class GoodsBrandDTO {
    private Integer id;
    private Integer storeId;
    @NotBlank(message = "name不能为空")
    private String name;
    @NotBlank(message = "desc不能为空")
    private String desc;
    private Integer status;
    private String remark;
}
