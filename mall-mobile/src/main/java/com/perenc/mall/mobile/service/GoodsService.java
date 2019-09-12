package com.perenc.mall.mobile.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: GoodsService
 * @Description:
 *
 * @Author: GR
 * @Date: 2019-9-11 23:44 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-11     GR      		
 */
@FeignClient("mall-goods")
public interface GoodsService {

    @RequestMapping(value = "/getGoods", method = RequestMethod.GET)
    String getGoods();
}
