package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
@TableName(value = "platform_advertise")
public class AdvertiseDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    @TableField(value = "img_url")
    private String imgUrl;
    private String sort;
    @TableField(value = "`desc`")
    private String desc;
    private String remark;
    @TableField(value = "link_url")
    private String linkUrl;
    @TableField(value = "is_enable")
    private Integer enable;
    private Integer type;
    @TableField(value = "jump_type")
    private Integer jumpType;
    @TableField(value = "store_id")
    private String storeId;
    @TableField(value = "goods_id")
    private String goodsId;
    @TableField(value = "create_user")
    private String createUser;
    @TableField(value = "update_time")
    private String updateUser;
    @TableField(value = "create_user")
    private String createTime;
    @TableField(value = "update_time")
    private String updateTime;
}
