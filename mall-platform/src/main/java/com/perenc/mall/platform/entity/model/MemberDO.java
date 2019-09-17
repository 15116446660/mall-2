package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName: MemberDO
 * @Description: 会员实体类
 *
 * @Author: GR
 * @Date: 2019-9-14 17:29 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Data
@TableName(value = "platform_member")
public class MemberDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String phone;
    private String wechat;
    private String balance;
    @TableField("head_img")
    private String head_img;
    @TableField("`desc`")
    private String desc;
    private String remark;
    private Integer sex;
    private String gold;
    private String grade;
    private String birthday;
    private String address;
    @TableField("create_user")
    private String create_user;
    @TableField("update_user")
    private String update_user;
    @TableField("create_time")
    private String create_time;
    @TableField("update_time")
    private String update_time;
}
