package com.perenc.mall.platform.service;

import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.platform.entity.dto.StoreGradeDTO;
import com.perenc.mall.platform.entity.model.StoreGradeDO;
import com.perenc.mall.platform.entity.vo.StoreGradeVO;

import java.util.List;

/**
 * @ClassName: IStoreGradeService
 * @Description: 店铺等级服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 14:26 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
public interface IStoreGradeService {
    /**
     * @description: 添加店铺等级
     * @param storeGradeDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveStoreGrade(StoreGradeDTO storeGradeDTO);

    /**
     * @description: 根据ID获取店铺等级信息
     * @param id
     * @return com.perenc.mall.platform.entity.model.StoreGradeVO
     * @author: GR
     * @date: 2019/9/17
     */
    StoreGradeVO getStoreGrade(Integer id);


    /**
     * @description: 根据ID移除店铺等级信息
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeStoreGradeById(Integer id);


    /**
     * @description: 获取店铺等级信息列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.model.StoreGradeDO>
     * @author: GR
     * @date: 2019/9/19
     */
    PageVO<StoreGradeVO> listStoreGrade(Integer currentPage, Integer pageSize);

    /**
     * @description: 更新店铺等级信息
     * @param storeGradeDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void updateStoreGrade(StoreGradeDTO storeGradeDTO);

}
