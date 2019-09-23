package com.perenc.mall.merchant.service;

import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.ActionMoneyOffDTO;
import com.perenc.mall.merchant.entity.vo.ActionMoneyOffVO;

import java.util.List;

/**
 * @ClassName: IActionMoneyOffService
 * @Description: 活动——满减服务类
 *
 * @Author: GR
 * @Date: 2019/9/23 15:55 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
public interface IActionMoneyOffService {
    /**
     * @description: 添加满减活动
     * @param actionMoneyOffDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveActionMoneyOff(ActionMoneyOffDTO actionMoneyOffDTO);

    /**
     * @description: 获取满减活动
     * @param id
     * @return com.perenc.mall.platform.entity.vo.ActionMoneyOffVO
     * @author: GR
     * @date: 2019/9/17
     */
    ActionMoneyOffVO getActionMoneyOff(Integer id);


    /**
     * @description: 根据ID移除满减活动
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeActionMoneyOffById(Integer id);


    /**
     * @description: 获取满减活动分页列表
     * @param currentPage
     * @param pageSize
     * @return com.perenc.mall.common.vo.PageVO
     * @author: GR
     * @date: 2019/9/23
     */
    PageVO listActionMoneyOffs(Integer currentPage, Integer pageSize);


    /**
     * @description: 活动活动商品分页
     * @param actionId
     * @return com.perenc.mall.common.vo.PageVO
     * @author: GR
     * @date: 2019/9/23       
     */
    PageVO listActionMoneyOffs(Integer actionId, Integer currentPage, Integer pageSize);

    /**
     * @description: 获取满减活动列表
     * @param actionMoneyOffDTO
     * @return java.util.List<com.perenc.mall.platform.entity.model.ActionMoneyOffDO>
     * @author: GR
     * @date: 2019/9/17
     */
    void updateActionMoneyOff(ActionMoneyOffDTO actionMoneyOffDTO);
}
