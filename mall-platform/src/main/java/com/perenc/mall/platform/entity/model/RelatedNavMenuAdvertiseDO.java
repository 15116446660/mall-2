package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: RelatedNavMenuAdvertiseDO
 * @Description: 导航菜单与广告关系表
 *
 * @Author: GR
 * @Date: 2019/9/17 20:27 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/17     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "platform_nav_menu_ad")
public class RelatedNavMenuAdvertiseDO {
    @TableField("nav_menu_id")
    private Integer navMenuId;
    @TableField("ad_id")
    private Integer adId;
    @TableField("create_user")
    private String createUser;
    @TableField("update_user")
    private String updateUser;
    @TableField("create_time")
    private String createTime;
    @TableField("update_time")
    private String updateTime;
}
