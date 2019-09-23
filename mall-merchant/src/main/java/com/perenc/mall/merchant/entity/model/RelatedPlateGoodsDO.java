package com.perenc.mall.merchant.entity.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: RelatedPlateGoodsDO
 * @Description: 板块——商品
 *
 * @Author: GR
 * @Date: 2019/9/18 19:07 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/18     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "store_plate_goods")
public class RelatedPlateGoodsDO {
    @TableField("store_id")
    private Integer storeId;
    @TableField("plate_id")
    private Integer plateId;
    @TableField("goods_id")
    private Integer goodsId;
    @TableField("create_user")
    private String createUser;
    @TableField("update_user")
    private String updateUser;
    @TableField("create_time")
    private String createTime;
    @TableField("update_time")
    private String updateTime;
}
