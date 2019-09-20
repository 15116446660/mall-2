package com.perenc.mall.merchant.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: PlateDTO
 * @Description: 板块上传数据
 *
 * @Author: GR
 * @Date: 2019/9/18 19:14 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/18     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class PlateDTO {
    private Integer id;
    private Integer storeId;
    @NotBlank(message = "name不能为空")
    private String name;
    @NotBlank(message = "logo不能为空")
    private String logo;
    @Min(0)
    @NotNull(message = "sort不能为空")
    private Integer sort;
    private String desc;
    private String remark;
    @Min(0)
    @NotNull(message = "status不能为空")
    private Integer status;
    /**
     * @description: 板块类型
     * 标准 0，店铺 1，商品 2，活动 3，广告 4
     * @author: GR
     * @date: 2019/9/18 19:05
     */
    @Min(0)
    @NotNull(message = "type不能为空")
    private Integer type;
    /**
     * @description: 广告ID
     * @author: GR
     * @date: 2019/9/18 20:22
     */
    private String adId;
    /**
     * @description: 活动ID
     * @author: GR
     * @date: 2019/9/18 20:22
     */
    private String actionId;
    /**
     * @description: 商品ID列表，以逗号隔开
     * @author: GR
     * @date: 2019/9/18 19:17
     */
    private String goodsIds;
    /**
     * @description: 商品分类ID列表，以逗号隔开
     * @author: GR
     * @date: 2019/9/18 19:17
     */
    private String goodsCategoryIds;
    /**
     * @description: 店铺分类ID列表，以逗号隔开
     * @author: GR
     * @date: 2019/9/18 19:17
     */
    private String storeCategoryIds;

}
