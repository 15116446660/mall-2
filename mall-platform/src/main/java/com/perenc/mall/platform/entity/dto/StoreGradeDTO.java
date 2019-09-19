package com.perenc.mall.platform.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: StoreGradeDTO
 * @Description: 前端上传店铺等级数据
 *
 * @Author: GR
 * @Date: 2019/9/19 20:55 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/19     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class StoreGradeDTO {
    private Integer id;
    @NotBlank(message = "name不能为空")
    private String name;
    private String logo;
    @NotBlank(message = "grade不能为空")
    private String grade;
    private String desc;
    private String remark;
    private Integer status;
}
