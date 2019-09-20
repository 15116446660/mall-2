package com.perenc.mall.platform.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: MemberGradeVO
 * @Description: 前端会员等级返回数据
 *
 * @Author: GR
 * @Date: 2019/9/18 12:51 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/18     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class MemberGradeVO {
    private Integer id;
    private String name;
    private Integer level;
    private String upLevel;
    private String card_img;
    private Integer status;
    private String remark;
    private String create_time;
}
