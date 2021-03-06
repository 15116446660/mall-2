package com.perenc.mall.platform.service.impl;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.model.ActionDO;
import com.perenc.mall.platform.mapper.ActionMapper;
import com.perenc.mall.platform.service.IActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: ActionServiceImpl
 * @Description: 活动服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 17:23 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class ActionServiceImpl extends BaseService<ActionMapper, ActionDO> implements IActionService {
}
