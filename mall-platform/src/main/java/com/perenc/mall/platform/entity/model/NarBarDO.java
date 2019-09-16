package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName: NarBarDO
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
@TableName(value = "platform_narbar")
public class NarBarDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    @TableField("img_logo")
    private String img_logo;
    private Integer sort;
    private String desc;
    private String remark;
    @TableField("link_url")
    private String link_url;
    @TableField("img_url")
    private String img_url;
    @TableField("is_enable")
    private Integer enable;
    @TableField("jump_type")
    private Integer jump_type;
    @TableField("create_user")
    private String create_user;
    @TableField("update_user")
    private String update_user;
    @TableField("create_time")
    private String create_time;
    @TableField("update_time")
    private String update_time;
}
