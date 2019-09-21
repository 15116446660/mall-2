package com.perenc.mall.merchant.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor(staticName = "build")
@TableName(value = "store_plate")
public class PlateDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("store_id")
    private Integer storeId;
    private String name;
    private String logo;
    private Integer sort;
    @TableField("`desc`")
    private String desc;
    private String remark;
    private Integer status;
    /**
     * @description: 板块类型
     * 标准 0，店铺 1，商品 2，活动 3，广告 4
     * @author: GR
     * @date: 2019/9/18 19:05
     */
    private Integer type;
    @TableField(value = "create_user")
    private String createUser;
    @TableField(value = "update_user")
    private String updateUser;
    @TableField(value = "create_time")
    private String createTime;
    @TableField(value = "update_time")
    private String updateTime;
}
