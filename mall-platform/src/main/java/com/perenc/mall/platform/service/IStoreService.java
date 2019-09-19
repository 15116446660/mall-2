package com.perenc.mall.platform.service;

import com.perenc.mall.platform.entity.dto.StoreDTO;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.platform.entity.vo.StoreVO;

/**
 * @ClassName: IStoreService
 * @Description: 店铺服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 14:26 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
public interface IStoreService {
    /**
     * @description: 添加店铺
     * @param storeDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveStore(StoreDTO storeDTO);

    /**
     * @description: 根据ID获取店铺信息
     * @param id
     * @return com.perenc.mall.platform.entity.model.StoreVO
     * @author: GR
     * @date: 2019/9/17
     */
    StoreVO getStore(Integer id);


    /**
     * @description: 根据ID移除店铺信息
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeStoreById(Integer id);


    /**
     * @description: 获取店铺信息列表
     * @param currentPage
     * @param pageSize
     * @param name
     * @param applyName
     * @param applyPhone
     * @param type
     * @param status
     * @return com.perenc.mall.common.vo.PageVO
     * @author: GR
     * @date: 2019/9/19
     */
    PageVO listStore(int currentPage, int pageSize, String name, String applyName, String applyPhone, Integer type, Integer status);

    /**
     * @description: 更新店铺信息
     * @param storeDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void updateStore(StoreDTO storeDTO);

    /**
     * @description: 审核店铺
     * @param id
     * @param reason 理由
     * @return void
     * @author: GR
     * @date: 2019/9/19
     */
    void storeAudit(Integer id, Integer status, String reason);
}
