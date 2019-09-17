package com.perenc.mall.platform.service;

import com.perenc.mall.platform.entity.model.BannerDO;

import java.util.List;

/**
 * @ClassName: IBannerService
 * @Description: 轮播图服务类
 *
 * @Author: GR
 * @Date: 2019-9-13 19:10 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-13     GR      		
 */
public interface IBannerService {

    /**
     * @description: 获取轮播图列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.model.BannerDO>
     * @author: GR
     * @date: 2019/9/17
     */
    List<BannerDO> listBanners();
}
