package com.perenc.mall.platform.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @ClassName: BannerDTO
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/17 11:35 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/17     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class BannerDTO {
    private String id;
    @NotBlank(message = "title不能为空")
    private String title;
    @NotBlank(message = "fileUrl不能为空")
    private String fileUrl;
    @Min(0)
    @NotBlank(message = "sort不能为空，且必须为数字")
    private String sort;
    @NotBlank(message = "desc不能为空")
    private String desc;
    @Min(0)
    @NotBlank(message = "skipType不能为空，且必须为数字")
    private String skipType;
    @NotBlank(message = "skipContent不能为空")
    private String skipContent;
    @NotBlank(message = "status不能为空")
    private String status;
}
