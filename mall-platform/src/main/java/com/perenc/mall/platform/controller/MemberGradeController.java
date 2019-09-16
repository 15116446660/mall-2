package com.perenc.mall.platform.controller;

import com.perenc.mall.common.controller.BaseController;
import com.perenc.mall.platform.entity.model.MemberGradeDO;
import com.perenc.mall.platform.service.impl.MemberGradeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MemberGradeController
 * @Description: 会员等级相关
 *
 * @Author: GR
 * @Date: 2019-9-14 17:47 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@RestController
@RequestMapping("grade")
public class MemberGradeController extends BaseController<MemberGradeServiceImpl, MemberGradeDO> {
}
