package com.perenc.mall.merchant.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: MemberGradeDTO
 * @Description: 前端上传会员等级数据信息·
 *
 * @Author: GR
 * @Date: 2019/9/20 10:02 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class MemberGradeDTO {
    private Integer id;
    private Integer storeId;
    @NotBlank(message = "name不能为空")
    private String name;
    @Min(0)
    @NotNull(message = "level不能为空")
    private Integer level;
    private String cardImg;
    @Min(0)
    @NotNull(message = "upLevel不能为空")
    private Integer upLevel;
    private String remark;
    private Integer status;
}
