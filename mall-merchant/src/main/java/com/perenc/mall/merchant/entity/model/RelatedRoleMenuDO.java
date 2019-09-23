package com.perenc.mall.merchant.entity.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: RelatedRoleMenuDO
 * @Description: 角色——菜单
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
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "store_role_menu")
public class RelatedRoleMenuDO {
    @TableField(value = "store_id")
    private Integer storeId;
    @TableField(value = "role_id")
    private Integer roleId;
    @TableField(value = "menu_id")
    private Integer menuId;
    @TableField(value = "create_user")
    private String createUser;
    @TableField(value = "update_user")
    private String updateUser;
    @TableField(value = "create_time")
    private String createTime;
    @TableField(value = "update_time")
    private String updateTime;
}
