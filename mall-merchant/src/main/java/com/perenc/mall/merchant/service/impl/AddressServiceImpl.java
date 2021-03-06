package com.perenc.mall.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.constant.StatusConstants;
import com.perenc.mall.common.context.BaseContextHandler;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.AddressDTO;
import com.perenc.mall.merchant.entity.model.AddressDO;
import com.perenc.mall.merchant.entity.vo.AddressVO;
import com.perenc.mall.merchant.mapper.AddressMapper;
import com.perenc.mall.merchant.service.IAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AddressServiceImpl
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/20 21:02 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class AddressServiceImpl extends BaseService<AddressMapper, AddressDO> implements IAddressService {
    @Override
    public void saveAddress(AddressDTO addressDTO) {
        AddressDO addressDO = AddressDO.build();
        BeanUtils.copyProperties(addressDTO, addressDO);

        // 上传状态不为空，且设置当前为默认地址，则修改原来的默认地址为正常地址
        if (null != addressDO.getStatus() && StatusConstants.DEFAULT == addressDO.getStatus()) {
            super.updateEntity(addressDO, new QueryWrapper<AddressDO>()
                    .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())
                    .eq(CommonFiledConstants.FILED_STATUS, StatusConstants.DEFAULT));
        }

        super.saveEntity(addressDO);
    }

    @Override
    public AddressVO getAddress(Integer id) {
        AddressDO addressDO = super.getEntityById(id);
        if (null == addressDO) {
            throw new BusinessException("当前用户地址不存在,请输入正确的地址ID");
        }
        AddressVO addressVO = AddressVO.build();
        BeanUtils.copyProperties(addressDO, addressVO);
        return addressVO;
    }

    @Override
    public void removeAddressById(Integer id) {
        super.removeEntityById(id);
    }

    @Override
    public PageVO<AddressVO> listAddress(Integer currentPage, Integer pageSize) {
        IPage<AddressDO> iPage = super.listEntitysByPage(new Page<AddressDO>(currentPage, pageSize), new QueryWrapper<AddressDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId()));
        List<AddressDO> addressDOList = iPage.getRecords();
        List<AddressVO> addressVOList = new ArrayList<>();
        addressDOList.forEach(addressDO -> {
            AddressVO addressVO = AddressVO.build();
            BeanUtils.copyProperties(addressDO, addressVO);
            addressVOList.add(addressVO);
        });

        return PageVO.<AddressVO>build().setCurrentPage(currentPage)
                .setPageSize(pageSize)
                .setList(addressVOList)
                .setTotal(super.count(new QueryWrapper<AddressDO>()
                        .eq(CommonFiledConstants.FILED_USER_ID, BaseContextHandler.getUserID())));
    }

    @Override
    public void updateAddress(AddressDTO addressDTO) {
        AddressDO addressDO = AddressDO.build();
        BeanUtils.copyProperties(addressDTO, addressDO);

        // 更新状态不为空，且设置当前为默认地址，则修改原来的默认地址为正常地址
        if (null != addressDO.getStatus() && StatusConstants.DEFAULT == addressDO.getStatus()) {
            super.updateEntity(addressDO, new QueryWrapper<AddressDO>()
                    .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())
                    .eq(CommonFiledConstants.FILED_STATUS, StatusConstants.DEFAULT));
        }
        super.updateEntity(addressDO);
    }
}
