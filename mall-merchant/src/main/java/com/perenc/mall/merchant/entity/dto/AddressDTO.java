package com.perenc.mall.merchant.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @ClassName: AddressDTO
 * @Description: 上传用户地址数据
 *
 * @Author: GR
 * @Date: 2019/9/20 20:26 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class AddressDTO {
    private Integer id;
    private Integer user_id;
    @NotBlank(message = "name不能为空")
    private String name;
    private String province;
    private String city;
    private String area;
    private String address;
    @NotBlank(message = "phone不能为空")
    private String phone;
    private String desc;
    private String remark;
    @Min(0)
    private Integer status;
}
