package com.perenc.mall.merchant.service;

import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.AddressDTO;
import com.perenc.mall.merchant.entity.model.AddressDO;
import com.perenc.mall.merchant.entity.vo.AddressVO;

import java.util.List;

/**
 * @ClassName: IAddressService
 * @Description: 地址服务类
 *
 * @Author: GR
 * @Date: 2019/9/20 20:57 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
public interface IAddressService {
    /**
     * @description: 添加地址
     * @param addressDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveAddress(AddressDTO addressDTO);

    /**
     * @description: 获取地址
     * @param id
     * @return com.perenc.mall.platform.entity.vo.AddressVO
     * @author: GR
     * @date: 2019/9/17
     */
    AddressVO getAddress(Integer id);


    /**
     * @description: 根据ID移除地址
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeAddressById(Integer id);


    /**
     * @description: 获取地址列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.AddressVO>
     * @author: GR
     * @date: 2019/9/17
     */
    PageVO<AddressVO> listAddress(Integer currentPage, Integer pageSize);

    /**
     * @description: 获取地址列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.model.AddressDO>
     * @author: GR
     * @date: 2019/9/17
     */
    void updateAddress(AddressDTO addressDTO);
}
