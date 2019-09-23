package com.perenc.mall.merchant.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: UserVO
 * @Description: 用户展示信息
 *
 * @Author: GR
 * @Date: 2019/9/23 13:36 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class UserVO {
    private Integer id;
    private Integer storeId;
    private Integer roleId;
    private String role;
    private String account;
    private String headImg;
    private Integer sex;
    private String phone;
    private String email;
    private String birthday;
    private String address;
    private Integer status;
    private String remark;
    private String createTime;
}
