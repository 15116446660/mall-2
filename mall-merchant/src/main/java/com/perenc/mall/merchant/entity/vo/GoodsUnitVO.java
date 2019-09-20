package com.perenc.mall.merchant.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: GoodsUnitVO
 * @Description: 商品单位
 *
 * @Author: GR
 * @Date: 2019/9/20 20:06 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class GoodsUnitVO {
    private Integer id;
    private String name;
    private String desc;
    private Integer status;
    private String remark;
    private String createTime;
}
