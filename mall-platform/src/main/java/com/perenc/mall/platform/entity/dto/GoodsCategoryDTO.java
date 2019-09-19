package com.perenc.mall.platform.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: GoodsCategoryDTO
 * @Description: 前端上传商品分类数据
 *
 * @Author: GR
 * @Date: 2019/9/19 14:05 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/19     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class GoodsCategoryDTO {
    private Integer id;
    @NotBlank(message = "name不能为空")
    private String name;
    @Min(0)
    private Integer parentId;
    private String desc;
    private String remark;
    @Min(0)
    @NotNull(message = "sort不能为空")
    private Integer sort;
    @Min(0)
    @NotNull(message = "status不能为空")
    private Integer status;
}
