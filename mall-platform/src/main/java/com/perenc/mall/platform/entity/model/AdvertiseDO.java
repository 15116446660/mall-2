package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: AdvertiseDO
 * @Description: 广告实体类
 *
 * @Author: GR
 * @Date: 2019-9-14 14:35
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "platform_advertise")
public class AdvertiseDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    @TableField(value = "file_url")
    private String fileUrl;
    private Integer sort;
    @TableField(value = "`desc`")
    private String desc;
    private String remark;
    private Integer status;
    private Integer type;
    @TableField(value = "skip_type")
    private Integer skipType;
    @TableField(value = "skip_content")
    private String skipContent;
    @TableField(value = "create_user")
    private String createUser;
    @TableField(value = "update_user")
    private String updateUser;
    @TableField(value = "create_time")
    private String createTime;
    @TableField(value = "update_time")
    private String updateTime;
}
