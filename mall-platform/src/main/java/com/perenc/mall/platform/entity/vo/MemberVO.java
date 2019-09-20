package com.perenc.mall.platform.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: MemberVO
 * @Description: 前端会员信息显示数据
 *
 * @Author: GR
 * @Date: 2019/9/20 11:25 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class MemberVO {
    private Integer id;
    private String name;
    private String nickname;
    private String phone;
    private String wechat;
    private Double balance;
    private String headImg;
    private Integer sex;
    private String level;
    private String birthday;
    private String address;
    private Integer status;
    private String remark;
    private String createTime;
}
