package com.perenc.mall.platform.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @ClassName: NavMenuDTO
 * @Description: 导航菜单上传数据
 *
 * @Author: GR
 * @Date: 2019/9/17 19:28 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/17     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class NavMenuDTO {
    private Integer id;
    @NotBlank(message = "title不能为空")
    private String title;
    @NotBlank(message = "fileUrl不能为空")
    private String fileUrl;
    @Min(value = 0)
    private Integer sort;
    private String desc;
    private String remark;
    @Min(value = 0)
    private Integer type;
    @Min(value = 0)
    private Integer skipType;
    @NotBlank(message = "skipContent不能为空")
    private String skipContent;
    /**
     * @description: 轮播图数组，以逗号隔开
     * @author: GR
     * @date: 2019/9/17 20:14
     */
    private String bannerId;
    /**
     * @description: 商品数组，以逗号隔开
     * @author: GR
     * @date: 2019/9/17 20:14
     */
    private String goodsId;
    /**
     * @description: 广告ID
     * @author: GR
     * @date: 2019/9/17 20:13
     */
    private String adId;
    private Integer status;
}
