package com.perenc.mall.merchant.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "store_member")
public class MemberDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("store_id")
    private Integer storeId;
    private String name;
    private String nickname;
    private String phone;
    private String wechat;
    @TableField("head_img")
    private String headImg;
    private Integer sex;
    private Integer coin;
    private String level;
    private String birthday;
    private String address;
    private Integer status;
    private String remark;
    @TableField("create_user")
    private String createUser;
    @TableField("update_user")
    private String updateUser;
    @TableField("create_time")
    private String createTime;
    @TableField("update_time")
    private String updateTime;
}
