package com.perenc.mall.platform.controller;

import com.perenc.mall.common.controller.BaseController;
import com.perenc.mall.platform.entity.model.MemberDO;
import com.perenc.mall.platform.service.impl.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MemberController
 * @Description: 会员相关
 *
 * @Author: GR
 * @Date: 2019-9-14 17:34 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@RestController
@RequestMapping("member")
public class MemberController extends BaseController<MemberServiceImpl, MemberDO> {
}
