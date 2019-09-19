package com.perenc.mall.platform.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: StoreGradeVO
 * @Description: 前端展示的店铺等级数据
 *
 * @Author: GR
 * @Date: 2019/9/19 20:57 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/19     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class StoreGradeVO {
    private Integer id;
    private String name;
    private String logo;
    private String grade;
    private String desc;
    private String remark;
    private Integer status;
    private String createTime;
}
