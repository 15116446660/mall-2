package com.perenc.mall.platform.controller;

import com.perenc.mall.common.controller.BaseController;
import com.perenc.mall.platform.entity.model.NarBarDO;
import com.perenc.mall.platform.service.impl.NarBarServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: NarBarController
 * @Description: 导航栏相关
 *
 * @Author: GR
 * @Date: 2019-9-14 17:17 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@RestController
@RequestMapping("narbar")
public class NarBarController extends BaseController<NarBarServiceImpl, NarBarDO> {
}
