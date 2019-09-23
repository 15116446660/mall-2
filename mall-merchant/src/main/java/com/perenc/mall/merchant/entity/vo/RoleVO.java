package com.perenc.mall.merchant.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName: RoleVO
 * @Description: 前端显示角色信息
 *
 * @Author: GR
 * @Date: 2019/9/23 9:46 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class RoleVO {
    private Integer id;
    private String name;
    private Integer status;
    private String remark;
    private String createTime;
    private List<SysMenuVO> list;
}
