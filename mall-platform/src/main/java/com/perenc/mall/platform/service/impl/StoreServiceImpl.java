package com.perenc.mall.platform.service.impl;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.model.StoreDO;
import com.perenc.mall.platform.mapper.StoreMapper;
import com.perenc.mall.platform.service.IStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: MerchantServiceImpl
 * @Description: 店铺服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 14:29 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class StoreServiceImpl extends BaseService<StoreMapper, StoreDO> implements IStoreService {
}
