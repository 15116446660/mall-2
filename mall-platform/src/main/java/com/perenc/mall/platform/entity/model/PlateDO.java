package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName: PlateDO
 * @Description: 板块实体类
 *
 * @Author: GR
 * @Date: 2019-9-14 13:58 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Data
@TableName(value = "platform_plate")
public class PlateDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    @TableField(value = "img_logo")
    private String imgLogo;
    private String sort;
    @TableField("'desc'")
    private String desc;
    private String remark;
    @TableField(value = "link_url")
    private String linkUrl;
    @TableField(value = "img_url")
    private String imgUrl;
    @TableField(value = "is_enable")
    private String enable;
    @TableField(value = "jump_type")
    private String jumpType;
    @TableField(value = "create_user")
    private String createUser;
    @TableField(value = "update_user")
    private String updateUser;
    @TableField(value = "create_time")
    private String createTime;
    @TableField(value = "update_time")
    private String updateTime;
}
