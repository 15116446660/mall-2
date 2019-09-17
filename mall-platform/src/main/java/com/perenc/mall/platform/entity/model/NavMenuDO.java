package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: NavMenuDO
 * @Description: 导航栏
 *
 * @Author: GR
 * @Date: 2019-9-14 14:05 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "platform_nav_menu")
public class NavMenuDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    @TableField("file_url")
    private String fileUrl;
    private Integer sort;
    @TableField("`desc`")
    private String desc;
    private String remark;
    private Integer status;
    private Integer type;
    @TableField("skip_type")
    private Integer skipType;
    @TableField("skip_content")
    private String skipContent;
    @TableField("banner_id")
    private String bannerId;
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
