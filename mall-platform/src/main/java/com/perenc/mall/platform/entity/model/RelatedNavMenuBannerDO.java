package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: RelatedNavMenuBannerDO
 * @Description: 导航菜单与轮播图关系表
 *
 * @Author: GR
 * @Date: 2019/9/17 20:24 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/17     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "platform_nav_menu_banner")
public class RelatedNavMenuBannerDO {
    @TableField("nav_menu_id")
    private Integer navMenuId;
    @TableField("banner_id")
    private Integer bannerId;
    @TableField("create_user")
    private String createUser;
    @TableField("update_user")
    private String updateUser;
    @TableField("create_time")
    private String createTime;
    @TableField("update_time")
    private String updateTime;
}
