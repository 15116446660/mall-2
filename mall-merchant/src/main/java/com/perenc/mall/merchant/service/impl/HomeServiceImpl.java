package com.perenc.mall.merchant.service.impl;

import com.perenc.mall.merchant.service.IHomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName: HomeServiceImpl
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/23 18:56 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
@Slf4j
@Service
public class HomeServiceImpl implements IHomeService {

    @Override
    public Object statisticalBaseData() {
        return null;
    }

    @Override
    public Object statisticalGraph(String startDate, String endDate) {
        return null;
    }

    @Override
    public Object statisticalHotGoods(int type) {
        return null;
    }

    @Override
    public Object statisticalNotice() {
        return null;
    }
}
