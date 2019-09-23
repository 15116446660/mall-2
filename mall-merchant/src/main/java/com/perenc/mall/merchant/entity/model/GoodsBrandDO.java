package com.perenc.mall.merchant.entity.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: GoodsUnitDO
 * @Description: 商品品牌品牌实体类
 *
 * @Author: GR
 * @Date: 2019/9/20 16:44 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "store_goods_brand")
public class GoodsBrandDO {
    private Integer id;
    @TableField("store_id")
    private Integer storeId;
    private String name;
    private String desc;
    private Integer status;
    private String remark;
    @TableField(value = "create_user")
    private String createUser;
    @TableField(value = "update_user")
    private String updateUser;
    @TableField(value = "create_time")
    private String createTime;
    @TableField(value = "update_time")
    private String updateTime;
}
