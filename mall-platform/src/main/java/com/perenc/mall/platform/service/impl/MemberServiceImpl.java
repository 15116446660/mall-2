package com.perenc.mall.platform.service.impl;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.model.MemberDO;
import com.perenc.mall.platform.mapper.MemberMapper;
import com.perenc.mall.platform.service.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: MemberServiceImpl
 * @Description: 会员服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 17:32 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class MemberServiceImpl extends BaseService<MemberMapper, MemberDO> implements IMemberService {
}
