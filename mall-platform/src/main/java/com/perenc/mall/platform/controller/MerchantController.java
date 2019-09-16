package com.perenc.mall.platform.controller;

import com.perenc.mall.common.controller.BaseController;
import com.perenc.mall.platform.entity.model.StoreDO;
import com.perenc.mall.platform.service.impl.StoreServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MerchantController
 * @Description: 店铺相关
 *
 * @Author: GR
 * @Date: 2019-9-14 17:18 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@RestController
@RequestMapping("merchant")
public class MerchantController extends BaseController<StoreServiceImpl, StoreDO> {
}
