package com.perenc.mall.merchant.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: AddressVO
 * @Description: 前端地址展示数据
 *
 * @Author: GR
 * @Date: 2019/9/20 20:41 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class AddressVO {
    private Integer id;
    private String name;
    private String province;
    private String city;
    private String area;
    private String address;
    private String phone;
    private String desc;
    private String remark;
    private Integer status;
    private String createTime;
}
