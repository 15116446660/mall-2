package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
@TableName(value = "platform_store")
public class StoreDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    @TableField(value = "img_logo")
    private String img_logo;
    private Integer business;
    private String province;
    private String city;
    private String area;
    private String address;
    @TableField(value = "img_logo")
    private String apply_user;
    private String email;
    /**
     * @description: 联系人
     * @author: GR
     * @date: 2019/9/16 20:17
     */
    @TableField(value = "contact_user")
    private String contactUser;
    /**
     * @description: 联系电话
     * @author: GR
     * @date: 2019/9/16 20:17
     */
    @TableField(value = "contact_phone")
    private String contactPhone;
    /**
     * @description: 法人
     * @author: GR
     * @date: 2019/9/16 20:17
     */
    @TableField(value = "corporate_user")
    private String corporateUser;
    /**
     * @description: 法人电话
     * @author: GR
     * @date: 2019/9/16 20:17
     */
    @TableField(value = "corporate_phone")
    private String corporatePhone;
    /**
     * @description: 法人身份证
     * @author: GR
     * @date: 2019/9/16 20:17
     */
    @TableField(value = "corporate_identity_card")
    private String corporateIdentityCard;
    /**
     * @description: 开户银行
     * @author: GR
     * @date: 2019/9/16 20:17
     */
    @TableField(value = "open_bank")
    private String openBank;
    /**
     * @description: 银行卡号
     * @author: GR
     * @date: 2019/9/16 20:17
     */
    @TableField(value = "bank_card_number")
    private String bankCardNumber;
    /**
     * @description: 营业执照
     * @author: GR
     * @date: 2019/9/16 20:17
     */
    @TableField(value = "business_license")
    private String businessLicense;
    /**
     * @description: 其他证件
     * @author: GR
     * @date: 2019/9/16 20:17
     */
    @TableField(value = "other_license")
    private String otherLicense;
    /**
     * @description: 银行其他材料
     * @author: GR
     * @date: 2019/9/16 20:17
     */
    @TableField(value = "bank_other_material")
    private String bankOtherMaterial;
    private String remark;
    private Integer state;
    @TableField(value = "is_enable")
    private Integer enable;
    @TableField(value = "create_user")
    private String createUser;
    @TableField(value = "update_user")
    private String updateUser;
    @TableField(value = "create_time")
    private String createTime;
    @TableField(value = "update_time")
    private String updateTime;
}
