package com.perenc.mall.merchant.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "store_member_grade")
public class MemberGradeDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer level;
    @TableField("up_level")
    private String upLevel;
    @TableField("card_img")
    private String card_img;
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
