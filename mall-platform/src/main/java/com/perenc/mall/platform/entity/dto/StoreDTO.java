package com.perenc.mall.platform.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "type不能为空")
    private Integer type;
    @NotBlank(message = "province不能为空")
    private String province;
    @NotBlank(message = "city不能为空")
    private String city;
    private String area;
    private String address;
    @NotBlank(message = "applyUser不能为空")
    private String applyUser;
    private String applyEmail;
    @NotBlank(message = "contactUser不能为空")
    private String contactUser;
    @NotBlank(message = "contactPhone电话不能为空")
    private String contactPhone;
    private String corporateUser;
    @NotBlank(message = "corporatePhone不能为空")
    private String corporatePhone;
    @NotBlank(message = "corporateIdCard不能为空")
    private String corporateIdCard;
    @NotBlank(message = "bank不能为空")
    private String bank;
    @NotBlank(message = "bankCardNumber不能为空")
    private String bankCardNumber;
    @NotBlank(message = "businessLicenseUrl不能为空")
    private String businessLicenseUrl;
    private String otherLicenseUrl;
    private String bankOtherDataUrl;
    private String remark;
    @Min(0)
    private Integer status;
}
