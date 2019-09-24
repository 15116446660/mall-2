package com.perenc.mall.platform.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @className WithdrawalDTO
 * @description
 *
 * @author GR
 * @date 2019/9/24 15:52 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/24     GR     		
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "build")
public class WithdrawalDTO {
    private Integer id;
    @Min(0)
    @NotNull(message = "storeId不能为空")
    private Integer storeId;
    @Min(0)
    @NotNull(message = "status不能为空")
    private Integer status;
    private String reason;
}
