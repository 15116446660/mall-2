package com.perenc.mall.platform.service;

import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.platform.entity.dto.StoreCategoryDTO;
import com.perenc.mall.platform.entity.vo.StoreCategoryVO;

import java.util.List;

/**
 * @ClassName: IStoreCategoryService
 * @Description: 店铺分类服务类
 *
 * @Author: GR
 * @Date: 2019/9/19 14:01 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/19     GR     		
 */
public interface IStoreCategoryService {
    /**
     * @description: 添加店铺分类
     * @param storeCategoryDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveStoreCategory(StoreCategoryDTO storeCategoryDTO);

    /**
     * @description: 获取店铺分类
     * @param id
     * @return com.perenc.mall.platform.entity.model.StoreCategoryVO
     * @author: GR
     * @date: 2019/9/17
     */
    StoreCategoryVO getStoreCategory(Integer id);


    /**
     * @description: 根据ID移除店铺分类
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeStoreCategoryById(Integer id);


    /**
     * @description: 获取店铺分类列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.StoreCategoryVO>
     * @author: GR
     * @date: 2019/9/17
     */
    PageVO<StoreCategoryVO> listStoreCategory(Integer currentPage, Integer pageSize);

    /**
     * @description: 更新店铺分类
     * @param storeCategoryDTO
     * @return java.util.List<com.perenc.mall.platform.entity.model.StoreCategoryDO>
     * @author: GR
     * @date: 2019/9/17
     */
    void updateStoreCategory(StoreCategoryDTO storeCategoryDTO);

}
