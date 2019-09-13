package com.perenc.mall.common.result;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @ClassName: ResultHanlder
 * @Description:
 *
 * @Author: GR
 * @Date: 2019-9-12 23:54 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-12     GR      		
 */
@Accessors(chain = true)
@Builder
@Data
@AllArgsConstructor(staticName = "of")
public class Result {

    private Integer code;

    private String msg;

    Result() {

    }
}
