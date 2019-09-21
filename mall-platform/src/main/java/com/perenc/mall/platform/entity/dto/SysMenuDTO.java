package com.perenc.mall.platform.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: SysMenuDTO
 * @Description: 系统菜单上传数据
 *
 * @Author: GR
 * @Date: 2019/9/21 15:20 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/21     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class SysMenuDTO {
    private Integer id;
    private Integer parentId;
    @NotBlank(message = "name不能为空")
    private String name;
    private Integer sort;
    private Integer status;
}
