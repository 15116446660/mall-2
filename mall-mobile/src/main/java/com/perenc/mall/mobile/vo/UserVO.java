package com.perenc.mall.mobile.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: UserVO
 * @Description: 用户展示数据
 *
 * @Author: GR
 * @Date: 2019-9-13 9:55 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-13     GR      		
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor(staticName = "build")
public class UserVO {

    private String name;

    private String tel;

}
