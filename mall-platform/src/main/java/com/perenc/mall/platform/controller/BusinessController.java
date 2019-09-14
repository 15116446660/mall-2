package com.perenc.mall.platform.controller;

import com.perenc.mall.common.controller.BaseController;
import com.perenc.mall.platform.entity.model.BusinessDO;
import com.perenc.mall.platform.service.impl.BusinessServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: BusinessController
 * @Description: 商家店铺行业相关
 *
 * @Author: GR
 * @Date: 2019-9-14 17:16 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@RestController
@RequestMapping("business")
public class BusinessController extends BaseController<BusinessServiceImpl, BusinessDO> {
}
