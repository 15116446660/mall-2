package com.perenc.mall.merchant.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;

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
    private String headImg;
    private Integer sex;
    private Integer coin;
    private String level;
    private String birthday;
    private String address;
    private Integer status;
    private String remark;
    protected String createTime;
}
