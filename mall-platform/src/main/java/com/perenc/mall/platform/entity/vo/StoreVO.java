package com.perenc.mall.platform.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: StoreVO
 * @Description: 前端显示店铺信息
 *
 * @Author: GR
 * @Date: 2019/9/19 17:33 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/19     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class StoreVO {
    private Integer id;
    private String name;
    private String logo;
    private String province;
    private String city;
    private String area;
    private String address;
    private String applyUser;
    private String applyEmail;
    private String contactUser;
    private String contactPhone;
    private String corporateUser;
    private String corporatePhone;
    private String corporateIdCard;
    private String bank;
    private String bankCardNumber;
    private String businessLicenseUrl;
    private String otherLicenseUrl;
    private String bankOtherDataUrl;
    private String remark;
    private String reason;
    private Integer status;
    private String createTime;
    private StoreCategoryVO storeCategory;
}
