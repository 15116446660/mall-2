package com.perenc.mall.platform.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: StoreCategoryDO
 * @Description: 前端店铺分类显示数据
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
public class StoreCategoryVO {
    private Integer id;
    private String name;
    private String createTime;
}
