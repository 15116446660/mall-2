package com.perenc.mall.platform.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: AdvertiseVO
 * @Description: 前端广告返回数据
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
public class AdvertiseVO {
    private Integer id;
    private String title;
    private String fileUrl;
    private Integer sort;
    private String desc;
    private String remark;
    private Integer status;
    private Integer type;
    private Integer skipType;
    private String skipContent;
    private String createTime;
}
