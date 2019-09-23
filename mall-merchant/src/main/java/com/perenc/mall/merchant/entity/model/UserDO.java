package com.perenc.mall.merchant.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: UserDO
 * @Description: 用户实体对象
 *
 * @Author: GR
 * @Date: 2019/9/23 13:32 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "store_user")
public class UserDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "store_id")
    private Integer storeId;
    @TableField(value = "role_id")
    private Integer roleId;
    private String account;
    private String password;
    @TableField(value = "head_img")
    private String headImg;
    private Integer sex;
    private String phone;
    private String email;
    private String birthday;
    private String address;
    private Integer status;
    private String remark;
    @TableField(value = "create_user")
    private String createUser;
    @TableField(value = "update_user")
    private String updateUser;
    @TableField(value = "create_time")
    private String createTime;
    @TableField(value = "update_time")
    private String updateTime;
}
