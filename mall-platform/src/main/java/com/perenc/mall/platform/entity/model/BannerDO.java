package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: BannerDO
 * @Description: 轮播图实体类
 *
 * @Author: GR
 * @Date: 2019-9-13 19:59 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-13     GR      		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "platform_banner")
public class BannerDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    @TableField(value = "img_logo")
    private String imgLogo;
    private Integer sort;
    @TableField(value = "`desc`")
    private String desc;
    private String remark;
    @TableField(value = "is_enable")
    private Integer enable;
    @TableField(value = "jump_type")
    private Integer jumpType;
    @TableField(value = "jump_content")
    private String jumpContent;
    @TableField(value = "create_user")
    private String createUser;
    @TableField(value = "update_user")
    private String updateUser;
    @TableField(value = "create_time")
    private String createTime;
    @TableField(value = "update_time")
    private String updateTime;
}
