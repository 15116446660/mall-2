package com.perenc.mall.platform.controller;

import com.perenc.mall.common.controller.BaseController;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.model.BannerDO;
import com.perenc.mall.platform.service.IBannerService;
import com.perenc.mall.platform.service.impl.BannerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: BannerController
 * @Description: 轮播图
 *
 * @Author: GR
 * @Date: 2019-9-13 21:39 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-13     GR      		
 */
@Slf4j
@RestController
@RequestMapping("/banner")
public class BannerController extends BaseController<BannerServiceImpl, BannerDO> {

}
