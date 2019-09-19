package com.perenc.mall.common.constant;

/**
 * @ClassName: StoreStatusConstants
 * @Description: 店铺状态
 *审核通过 0，审核未通过 1，待审核 2，启用 3，冻结 4
 * @Author: GR
 * @Date: 2019/9/19 17:45 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/19     GR     		
 */
public class StoreStatusConstants {

    /**
     * @description: 审核通过
     * @author: GR
     * @date: 2019/9/19 17:48
     */
    public static final int STATUS_VERIFIED_OK = 0;

    /**
     * @description: 审核未通过
     * @author: GR
     * @date: 2019/9/19 17:48
     */
    public static final int STATUS_VERIFIED_FAIL = 1;

    /**
     * @description: 待审核
     * @author: GR
     * @date: 2019/9/19 17:48
     */
    public static final int STATUS__UN_VERIFIED = 2;

    /**
     * @description: 启用
     * @author: GR
     * @date: 2019/9/19 17:48
     */
    public static final int STATUS_ENABLE = 3;

    /**
     * @description: 冻结
     * @author: GR
     * @date: 2019/9/19 17:48
     */
    public static final int STATUS_UN_ENABLE = 4;
}
