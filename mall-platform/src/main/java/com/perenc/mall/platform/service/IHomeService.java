package com.perenc.mall.platform.service;

/**
 * @className IHomeService
 * @description
 *
 * @author GR
 * @date 2019/9/24 10:05 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/24     GR     		
 */
public interface IHomeService {

    /**
     * @description 查询会员信息
     * @param
     * @return java.lang.Object
     * @author GR
     * @date 2019/9/24
     */
    Object searchMemberInfo();

    /**
     * @description 查询店铺信息
     * @param
     * @return java.lang.Object
     * @author GR
     * @date 2019/9/24
     */
    Object searchStoreInfo();

    /**
     * @description 查询订单信息
     * @param
     * @return java.lang.Object
     * @author GR
     * @date 2019/9/24
     */
    Object searchOrderInfo();

    /**
     * @description 根据订单查询数据统计图表
     * @param startDate
     * @param endDate
     * @return java.lang.Object
     * @author GR
     * @date 2019/9/24
     */
    Object searchGraphByDate(String startDate, String endDate);

    /**
     * @description 根据时间节点查询热门店铺
     * @param type
     * @return java.lang.Object
     * @author GR
     * @date 2019/9/24
     */
    Object searchHotStoreByType(Integer type);


    /**
     * @description 查询消息通知
     * @param
     * @return java.lang.Object
     * @author GR
     * @date 2019/9/24
     */
    Object searchNotice();
}
