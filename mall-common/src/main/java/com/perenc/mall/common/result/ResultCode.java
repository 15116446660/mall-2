package com.perenc.mall.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: ResultCode
 * @Description: 结果状态
 *
 * @Author: GR
 * @Date: 2019-9-12 23:59 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-12     GR      		
 */
@AllArgsConstructor
public enum ResultCode {
    OK(0, "成功"),
    FAIL(1, "失败");

    @Getter
    private Integer code;

    @Getter
    private String msg;
}
