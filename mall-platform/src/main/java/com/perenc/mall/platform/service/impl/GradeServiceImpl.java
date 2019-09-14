package com.perenc.mall.platform.service.impl;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.model.GradeDO;
import com.perenc.mall.platform.mapper.GradeMapper;
import com.perenc.mall.platform.service.IGradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: GradeServiceImpl
 * @Description:
 *
 * @Author: GR
 * @Date: 2019-9-14 17:45 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class GradeServiceImpl extends BaseService<GradeMapper, GradeDO> implements IGradeService {
}
