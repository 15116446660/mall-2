package com.perenc.mall.merchant.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: RoleDTO
 * @Description: 前端上传角色数据信息
 *
 * @Author: GR
 * @Date: 2019/9/23 9:11 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class RoleDTO {
    private Integer id;
    @NotBlank(message = "name不能为空")
    private String name;
    private Integer storeId;
    private Integer status;
    private String remark;
    private String menuValues;
}
