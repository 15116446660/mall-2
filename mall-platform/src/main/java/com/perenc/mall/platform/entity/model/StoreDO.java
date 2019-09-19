package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;

/**
 * @ClassName: StoreDO
 * @Description: 商家店铺实体类
 *
 * @Author: GR
 * @Date: 2019-9-14 14:15 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "platform_store")
public class StoreDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String logo;
    /**
     * @description: 店铺行业
     * @author: GR
     * @date: 2019/9/19 17:21
     */
    private Integer type;
    private String province;
    private String city;
    private String area;
    private String address;
    @TableField(value = "apply_user")
    private String applyUser;
    @TableField(value = "apply_email")
    private String applyEmail;
    @TableField(value = "contact_user")
    private String contactUser;
    @TableField(value = "contact_phone")
    private String contactPhone;
    @TableField(value = "corporate_user")
    private String corporateUser;
    @TableField(value = "corporate_phone")
    private String corporatePhone;
    @TableField(value = "corporate_id_card")
    private String corporateIdCard;
    @TableField(value = "bank")
    private String bank;
    @TableField(value = "bank_card_number")
    private String bankCardNumber;
    @TableField(value = "business_license_url")
    private String businessLicenseUrl;
    @TableField(value = "other_license_url")
    private String otherLicenseUrl;
    @TableField(value = "bank_other_data_url")
    private String bankOtherDataUrl;
    private String remark;
    private Integer status;
    @TableField(value = "create_user")
    private String createUser;
    @TableField(value = "update_user")
    private String updateUser;
    @TableField(value = "create_time")
    private String createTime;
    @TableField(value = "update_time")
    private String updateTime;
}
