package com.perenc.mall.merchant.service;


import com.perenc.mall.merchant.entity.dto.BannerDTO;
import com.perenc.mall.merchant.entity.model.BannerDO;
import com.perenc.mall.merchant.entity.vo.BannerVO;

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
     * @description: 添加轮播图
     * @param bannerDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveBanner(BannerDTO bannerDTO);

    /**
     * @description: 获取轮播图
     * @param id
     * @return com.perenc.mall.platform.entity.vo.BannerVO
     * @author: GR
     * @date: 2019/9/17       
     */
    BannerVO getBanner(Integer id);


    /**
     * @description: 根据ID移除轮播图
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeBannerById(Integer id);


    /**
     * @description: 获取轮播图列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.BannerVO>
     * @author: GR
     * @date: 2019/9/17
     */
    List<BannerVO> listBanners();

    /**
     * @description: 获取轮播图列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.model.BannerDO>
     * @author: GR
     * @date: 2019/9/17
     */
    void updateBanner(BannerDTO bannerDTO);

}
