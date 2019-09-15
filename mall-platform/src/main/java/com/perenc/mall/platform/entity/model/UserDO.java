package com.perenc.mall.platform.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName: UserDO
 * @Description: 用户实体类
 *
 * @Author: GR
 * @Date: 2019-9-14 14:17 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Data
@TableName(value = "user")
public class UserDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String tel;
}
