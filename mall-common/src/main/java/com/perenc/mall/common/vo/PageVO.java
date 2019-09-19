package com.perenc.mall.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName: PageVO
 * @Description: 前端分页显示数据
 *
 * @Author: GR
 * @Date: 2019/9/19 19:02 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/19     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class PageVO<T> {
    private Integer currentPage;
    private Integer pageSize;
    private Integer total;
    private List<T> list;
}
