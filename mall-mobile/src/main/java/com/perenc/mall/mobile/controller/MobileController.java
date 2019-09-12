package com.perenc.mall.mobile.controller;

import com.perenc.mall.mobile.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MobileController
 * @Description:
 *
 * @Author: GR
 * @Date: 2019-9-11 21:13 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-11     GR      		
 */
@Slf4j
@RestController
public class MobileController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping("test")
    public Object testMobileApi() {
        return "测试移动端接口";
    }

    @RequestMapping("get")
    public Object getMobileApi() {
        return "从商品模块中获取商品：" + goodsService.getGoods();
    }
}
