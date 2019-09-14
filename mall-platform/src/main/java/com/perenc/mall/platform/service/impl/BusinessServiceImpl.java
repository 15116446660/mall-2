package com.perenc.mall.platform.service.impl;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.model.BusinessDO;
import com.perenc.mall.platform.mapper.BusinessMapper;
import com.perenc.mall.platform.service.IBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: BusinessServiceImpl
 * @Description: 店铺行业服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 14:25 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class BusinessServiceImpl extends BaseService<BusinessMapper, BusinessDO> implements IBusinessService {
}
