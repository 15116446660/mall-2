package com.perenc.mall.merchant.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName: PlateVO
 * @Description: 前端返回板块数据
 *
 * @Author: GR
 * @Date: 2019/9/18 19:17 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/18     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class PlateVO {
    private Integer id;
    private String name;
    private String logo;
    private Integer sort;
    private Integer status;
    private List<GoodsVO> goods;
    private List<GoodsCategoryVO> goodsCategory;
}
