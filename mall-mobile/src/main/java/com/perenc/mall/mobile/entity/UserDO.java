package com.perenc.mall.mobile.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.perenc.mall.mobile.vo.UserVO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: UserDO
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/12 10:56 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/12     GR     		
 */
@Data
@TableName(value = "user")
public class UserDO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "名字不能为空")
    @NotEmpty(message = "名字不能为空")
    private String name;

    private String tel;

}
