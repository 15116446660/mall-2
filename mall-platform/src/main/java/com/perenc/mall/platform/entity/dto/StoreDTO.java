package com.perenc.mall.platform.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @ClassName: StoreDTO
 * @Description: 前端上传店铺信息数据
 *
 * @Author: GR
 * @Date: 2019/9/19 17:26 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/19     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class StoreDTO {
    private Integer id;
    @NotBlank(message = "name不能为空")
    private String name;
    private String logo;
    @Min(0)
    private Integer type;
    @NotBlank(message = "province不能为空")
    private String province;
    @NotBlank(message = "city不能为空")
    private String city;
    private String area;
    private String address;
    @NotBlank(message = "申请人不能为空")
    private String applyUser;
    private String applyEmail;
    @NotBlank(message = "联系人不能为空")
    private String contactUser;
    @NotBlank(message = "联系人电话不能为空")
    private String contactPhone;
    private String corporateUser;
    @NotBlank(message = "法人电话不能为空")
    private String corporatePhone;
    @NotBlank(message = "法人身份证不能为空")
    private String corporateIdCard;
    @NotBlank(message = "开户行不能为空")
    private String bank;
    @NotBlank(message = "银行卡卡号不能为空")
    private String bankCardNumber;
    @NotBlank(message = "营业执照不能为空")
    private String businessLicenseUrl;
    private String otherLicenseUrl;
    private String bankOtherDataUrl;
    private String remark;
    @Min(0)
    private Integer status;
}
