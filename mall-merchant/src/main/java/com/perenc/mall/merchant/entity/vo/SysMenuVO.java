package com.perenc.mall.merchant.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SysMenuVO
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/21 15:24 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/21     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class SysMenuVO {
    private Integer id;
    private String name;
    private Integer parentId;
    private String parentName;
    private Integer sort;
    private Integer status;
    private String createTime;
    List<SysMenuVO> children = new ArrayList<>();

    public void add(SysMenuVO sysMenuVO) {
        this.children.add(sysMenuVO);
    }
}
