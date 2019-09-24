package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: GoodsDO
 * @Description: 订单实体类
 *
 * @Author: GR
 * @Date: 2019/9/18 13:13 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/18     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
@TableName(value = "merchant_order")
public class OrderDO {
}
