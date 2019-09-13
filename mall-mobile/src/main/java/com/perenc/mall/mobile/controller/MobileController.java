package com.perenc.mall.mobile.controller;

import com.perenc.mall.common.annotation.ResultHanlder;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.mobile.entity.UserDO;
import com.perenc.mall.mobile.service.GoodsService;
import com.perenc.mall.mobile.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    @RequestMapping("list")
    @ResultHanlder(msg = "测试数据成功")
    public UserVO testMobileType(String type) {
        if ("1".equals(type)) {
            throw new BusinessException("接收到type=1，抛出业务异常");
        }
        UserDO userDO = new UserDO();
        userDO.setName("ceshi");
        userDO.setTel("18923721783");

        UserVO userVO = UserVO.build();

        BeanUtils.copyProperties(userDO, userVO);

        return userVO;
    }

    @RequestMapping("get")
    public Object getMobileApi() {
        return "从商品模块中获取商品：" + goodsService.getGoods();
    }
}
