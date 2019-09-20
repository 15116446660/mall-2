package com.perenc.mall.merchant.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: EvaluateDTO
 * @Description: 客服回复上传数据上传数据
 *
 * @Author: GR
 * @Date: 2019/9/20 20:43 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class EvaluateDTO {
    private Integer id;
    private Integer store_id;
    private Integer user_id;
    private String name;
}
