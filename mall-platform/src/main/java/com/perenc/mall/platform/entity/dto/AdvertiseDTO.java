package com.perenc.mall.platform.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: AdvertiseDTO
 * @Description: 广告上传数据
 *
 * @Author: GR
 * @Date: 2019/9/17 15:26 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/17     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class AdvertiseDTO {
    private Integer id;
    @NotBlank(message = "title不能为空")
    private String title;
    @NotBlank(message = "fileUrl不能为空")
    private String fileUrl;
    @Min(value = 0)
    @NotNull(message = "sort不能为空")
    private Integer sort;
    @Min(value = 0)
    @NotNull(message = "type不能为空")
    private Integer type;
    private String desc;
    @Min(value = 0)
    @NotNull(message = "skipType不能为空")
    private Integer skipType;
    @NotBlank(message = "skipContent不能为空")
    private String skipContent;
    @Min(0)
    @NotNull(message = "status不能为空")
    private Integer status;
}
