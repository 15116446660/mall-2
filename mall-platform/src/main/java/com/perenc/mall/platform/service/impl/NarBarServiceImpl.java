package com.perenc.mall.platform.service.impl;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.model.NarBarDO;
import com.perenc.mall.platform.mapper.NarBarMapper;
import com.perenc.mall.platform.service.INarBarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: NarBarServiceImpl
 * @Description: 导航栏服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 14:30 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class NarBarServiceImpl extends BaseService<NarBarMapper, NarBarDO> implements INarBarService {
}
