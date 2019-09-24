package com.perenc.mall.merchant.controller;

import com.perenc.mall.common.result.Result;
import com.perenc.mall.merchant.service.IHomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HomeController
 * @Description: 首页相关
 *
 * @Author: GR
 * @Date: 2019/9/23 18:54 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
@Slf4j
@RestController
@RequestMapping("home")
public class HomeController {

    @Autowired
    IHomeService homeService;

    /**
     * @description: 统计基本数据
     * @param
     * @return java.lang.Object
     * @author: GR
     * @date: 2019/9/23       
     */
    @RequestMapping(value = "base", method = RequestMethod.POST)
    public Object statisticalBaseData() {
        return homeService.statisticalBaseData();
    }

    @RequestMapping(value = "graph", method = RequestMethod.POST)
    public Object statisticalGraph(String startDate, String endDate) {
        return homeService.statisticalGraph(startDate, endDate);
    }

    /**
     * @description: 统计热门商品
     * @param
     * @return java.lang.Object
     * @author: GR
     * @date: 2019/9/23
     */
    @RequestMapping(value = "hot", method = RequestMethod.POST)
    public Object statisticalHotGoods(int type) {
        return statisticalHotGoods(type);
    }

    /**
     * @description: 统计通知
     * @param
     * @return java.lang.Object
     * @author: GR
     * @date: 2019/9/23
     */
    @RequestMapping(value = "notice", method = RequestMethod.POST)
    public Object statisticalNotice() {
        return statisticalNotice();
    }
}
