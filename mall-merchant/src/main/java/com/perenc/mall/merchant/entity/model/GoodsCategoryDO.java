package com.perenc.mall.merchant.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: GoodsCategoryDO
 * @Description: 商品分类
 *
 * @Author: GR
 * @Date: 2019/9/18 21:44 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/18     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "store_goods_category")
public class GoodsCategoryDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    @TableField(value = "parent_id")
    private Integer parentId;
    @TableField(value = "`desc`")
    private String desc;
    private String remark;
    private Integer sort;
    private Integer status;
    @TableField(value = "create_user")
    private String createUser;
    @TableField(value = "update_user")
    private String updateUser;
    @TableField(value = "create_time")
    private String createTime;
    @TableField(value = "update_time")
    private String updateTime;
}
