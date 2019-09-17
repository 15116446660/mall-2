package com.perenc.mall.platform.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: BannerVO
 * @Description: 轮播图VO
 *
 * @Author: GR
 * @Date: 2019/9/17 9:30 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/17     GR     		
 */
@Data
@NoArgsConstructor(staticName = "build")
public class BannerVO {
    private Integer id;
    private String name;
    private String imgLogo;
    private Integer sort;
    private String desc;
    private String remark;
    private Integer enable;
    private Integer jumpType;
    private String storeId;
    private String goodsId;
    private String linkUrl;
    private String createTime;
}
