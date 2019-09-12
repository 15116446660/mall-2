package com.perenc.mall.goods.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

/**
 * @ClassName: GoodsController
 * @Description:
 *
 * @Author: GR
 * @Date: 2019-9-11 21:08 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-11     GR      		
 */
@Slf4j
@RestController
public class GoodsController {

    @RequestMapping("/test")
    public Object testGoodsApi() {
        return "测试商品接口";
    }

    @RequestMapping(value = "/getGoods",method = RequestMethod.GET)
    public Object getGoodsApi() {
        return "拿到商品了";
    }
}
