package com.perenc.mall.merchant.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: UserDTO
 * @Description: 前端上传用户信息
 *
 * @Author: GR
 * @Date: 2019/9/23 11:57 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class UserDTO {
    private Integer id;
    private Integer storeId;
    @Min(0)
    @NotNull(message = "roleId不能为空")
    private Integer roleId;
    @NotBlank(message = "account不能为空")
    private String account;
    @NotBlank(message = "password不能为空")
    private String password;
    private String headImg;
    @Min(0)
    @NotNull(message = "sex不能为空")
    private Integer sex;
    @NotBlank(message = "phone不能为空")
    private String phone;
    private String email;
    private String birthday;
    private String address;
    private Integer status;
    private String remark;
}
