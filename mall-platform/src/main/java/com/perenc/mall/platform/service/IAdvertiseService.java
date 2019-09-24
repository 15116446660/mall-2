package com.perenc.mall.platform.service;

import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.platform.entity.dto.AdvertiseDTO;
import com.perenc.mall.platform.entity.model.AdvertiseDO;
import com.perenc.mall.platform.entity.vo.AdvertiseVO;

import java.util.List;

/**
 * @ClassName: IAdvertiseService
 * @Description:
 *
 * @Author: GR
 * @Date: 2019-9-14 14:37 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
public interface IAdvertiseService {

    /**
     * @description: 添加广告
     * @param advertiseDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveAdvertise(AdvertiseDTO advertiseDTO);

    /**
     * @description: 根据ID获取广告详情广告
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    AdvertiseDO getAdvertiseById(Integer id);

    /**
     * @description: 广告列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.AdvertiseVO>
     * @author: GR
     * @date: 2019/9/17       
     */
    PageVO<AdvertiseVO> listAdvertises(Integer currentPage, Integer pageSize);


    /**
     * @description: 更新广告
     * @param advertiseDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void updateAdvertise(AdvertiseDTO advertiseDTO);


    /**
     * @description: 根据ID移除广告信息
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeAdvertiseById(Integer id);
}
