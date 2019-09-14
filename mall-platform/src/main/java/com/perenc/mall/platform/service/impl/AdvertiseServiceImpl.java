package com.perenc.mall.platform.service.impl;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.model.AdvertiseDO;
import com.perenc.mall.platform.mapper.AdvertiseMapper;
import com.perenc.mall.platform.service.IAdvertiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: AdvertiseServiceImpl
 * @Description: 广告服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 14:37 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class AdvertiseServiceImpl extends BaseService<AdvertiseMapper, AdvertiseDO> implements IAdvertiseService {
}
