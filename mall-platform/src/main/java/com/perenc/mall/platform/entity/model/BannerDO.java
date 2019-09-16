package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
@TableName(value = "platform_banner")
public class BannerDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String sort;
    private String name;
    @TableField("'desc'")
    private String desc;
    private String remark;
    @TableField(value = "is_enable")
    private Integer enable;
    @TableField(value = "jump_type")
    private Integer jumpType;
    @TableField(value = "store_id")
    private String storeId;
    @TableField(value = "goods_id")
    private String goodsId;
    @TableField(value = "link_url")
    private String linkUrl;
    @TableField(value = "img_url")
    private String imgUrl;
    @TableField(value = "create_user")
    private String createUser;
    @TableField(value = "update_user")
    private String updateUser;
    @TableField(value = "create_time")
    private String createTime;
    @TableField(value = "update_time")
    private String updateTime;
}
