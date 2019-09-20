package com.perenc.mall.merchant.service;

import com.perenc.mall.merchant.entity.dto.PlateDTO;
import com.perenc.mall.merchant.entity.model.PlateDO;
import com.perenc.mall.merchant.entity.vo.PlateVO;

import java.util.List;

/**
 * @ClassName: IPlateService
 * @Description: 板块服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 13:59 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
public interface IPlateService {
    /**
     * @description: 添加板块
     * @param plateDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void savePlate(PlateDTO plateDTO);

    /**
     * @description: 根据ID获取板块信息
     * @param id
     * @return com.perenc.mall.platform.entity.model.PlateVO
     * @author: GR
     * @date: 2019/9/17
     */
    PlateVO getPlate(Integer id);


    /**
     * @description: 根据ID移除板块信息
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removePlateById(Integer id);


    /**
     * @description: 获取板块信息列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.model.PlateDO>
     * @author: GR
     * @date: 2019/9/17
     */
    List<PlateDO> listPlate();

    /**
     * @description: 更新板块信息
     * @param plateDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void updatePlate(PlateDTO plateDTO);
}
