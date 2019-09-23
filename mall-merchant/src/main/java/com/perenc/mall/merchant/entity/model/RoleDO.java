package com.perenc.mall.merchant.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: RoleDO
 * @Description: 角色相关实体类
 *
 * @Author: GR
 * @Date: 2019/9/23 9:17 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
@Data
@NoArgsConstructor(staticName = "build")
@TableName(value = "store_role")
public class RoleDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    @TableField(value = "store_id")
    private Integer storeId;
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
