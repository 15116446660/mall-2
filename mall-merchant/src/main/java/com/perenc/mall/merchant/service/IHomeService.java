package com.perenc.mall.merchant.service;

/**
 * @ClassName: IHomeService
 * @Description: 首页相关服务类
 *
 * @Author: GR
 * @Date: 2019/9/23 18:56 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
public interface IHomeService {

    Object statisticalBaseData();

    Object statisticalGraph(String startDate, String endDate);

    Object statisticalHotGoods(int type);

    Object statisticalNotice();
}
