package com.perenc.mall.platform.controller;

import com.perenc.mall.common.controller.BaseController;
import com.perenc.mall.platform.entity.model.AdvertiseDO;
import com.perenc.mall.platform.service.impl.AdvertiseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AdvertiseController
 * @Description: 广告相关
 *
 * @Author: GR
 * @Date: 2019-9-14 17:15 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@RestController
@RequestMapping("advertise")
public class AdvertiseController extends BaseController<AdvertiseServiceImpl, AdvertiseDO> {
}
