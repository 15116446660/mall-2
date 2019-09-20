package com.perenc.mall.merchant.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: BannerVO
 * @Description: 前端轮播图返回数据
 *
 * @Author: GR
 * @Date: 2019/9/18 12:51 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/18     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class BannerVO {
    private Integer id;
    private String title;
    private String fileUrl;
    private Integer sort;
    private String desc;
    private String remark;
    private Integer status;
    private Integer skipType;
    private String skipContent;
    private String createTime;
}
