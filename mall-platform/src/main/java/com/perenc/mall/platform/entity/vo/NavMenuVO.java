package com.perenc.mall.platform.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName: NavMenuVO
 * @Description: 导航详情数据
 *
 * @Author: GR
 * @Date: 2019/9/18 11:45 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/18     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class NavMenuVO {
    private Integer id;
    private String title;
    private String fileUrl;
    private Integer sort;
    private String desc;
    private String remark;
    private Integer type;
    private Integer skipType;
    private String skipContent;
    private List<BannerVO> bannerList;
    private List<GoodsVO> goodsList;
    private AdvertiseVO ad;
    private Integer status;
}
