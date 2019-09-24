package com.perenc.mall.platform.controller;

import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.platform.service.IHomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @className HomeController
 * @description
 *
 * @author GR
 * @date 2019/9/24 10:04 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/24     GR     		
 */
@Slf4j
@RestController
@RequestMapping("home")
public class HomeController {

    @Autowired
    private IHomeService homeService;

    @RequestMapping(value = "base", method = RequestMethod.POST)
    public Object statisticalBaseData() {
        Map<String, Object> map = new HashMap<>();
        Object memberInfo = homeService.searchMemberInfo();
        map.put("member", memberInfo);

        Object searchStoreInfo = homeService.searchStoreInfo();
        map.put("store", searchStoreInfo);

        Object searchOrderInfo = homeService.searchOrderInfo();
        map.put("order", searchOrderInfo);
        return map;
    }

    @RequestMapping(value = "graph", method = RequestMethod.POST)
    public Object statisticalGraph(String startDate, String endDate) {
        return homeService.searchGraphByDate(startDate, endDate);
    }

    @RequestMapping(value = "hot", method = RequestMethod.POST)
    public Object statisticalHotGoods(Integer type) {
        return statisticalHotGoods(type);
    }

    @RequestMapping(value = "notice", method = RequestMethod.POST)
    public Object statisticalNotice() {
        return homeService.searchNotice();
    }


}
