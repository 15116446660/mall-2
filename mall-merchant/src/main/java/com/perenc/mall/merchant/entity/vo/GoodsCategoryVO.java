package com.perenc.mall.merchant.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GoodsCategoryVO
 * @Description: 前端商品分类显示数据
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
public class GoodsCategoryVO {
    private Integer id;
    private String name;
    private Integer parentId;
    private String parentName;
    private Integer sort;
    private Integer status;
    private String createTime;
    List<GoodsCategoryVO> children = new ArrayList<>();
}
