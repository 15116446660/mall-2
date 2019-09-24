package com.perenc.mall.platform.service;

import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.platform.entity.dto.BannerDTO;
import com.perenc.mall.platform.entity.model.BannerDO;
import com.perenc.mall.platform.entity.vo.BannerVO;


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
     * @return com.perenc.mall.platform.entity.model.BannerDO
     * @author: GR
     * @date: 2019/9/17       
     */
    BannerDO getBanner(Integer id);


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
    PageVO<BannerVO> listBanners(Integer currentPage, Integer pageSize);

    /**
     * @description: 获取轮播图列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.model.BannerDO>
     * @author: GR
     * @date: 2019/9/17
     */
    void updateBanner(BannerDTO bannerDTO);

    /**
     * @description: 更新Banner状态
     * @param id
     * @param status
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void updateBannerStatus(Integer id, Integer status);
}
