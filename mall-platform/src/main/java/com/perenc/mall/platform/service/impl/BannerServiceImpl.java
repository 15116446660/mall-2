package com.perenc.mall.platform.service.impl;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.model.BannerDO;
import com.perenc.mall.platform.mapper.BannerMapper;
import com.perenc.mall.platform.service.IBannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: BannerServiceImpl
 * @Description: 轮播图业务类
 *
 * @Author: GR
 * @Date: 2019-9-13 19:56 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-13     GR      		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class BannerServiceImpl extends BaseService<BannerMapper, BannerDO> implements IBannerService {

    public void test() {

    }
}
