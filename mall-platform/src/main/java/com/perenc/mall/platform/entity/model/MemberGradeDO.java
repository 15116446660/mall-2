package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName: MemberGradeDO
 * @Description: 会员等级实体类
 *
 * @Author: GR
 * @Date: 2019-9-14 17:43 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Data
@TableName(value = "platform_member_grade")
public class MemberGradeDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String upgrade;
    @TableField("card_img")
    private String card_img;
    private String level;
    @TableField("is_enable")
    private String enable;
    private String remark;
    @TableField("create_user")
    private String create_user;
    @TableField("update_user")
    private String update_user;
    @TableField("create_time")
    private String create_time;
    @TableField("update_time")
    private String update_time;
}
