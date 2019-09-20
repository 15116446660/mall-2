package com.perenc.mall.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.constant.StatusConstants;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.merchant.entity.dto.AddressDTO;
import com.perenc.mall.merchant.entity.model.AddressDO;
import com.perenc.mall.merchant.mapper.AddressMapper;
import com.perenc.mall.merchant.service.IAddressService;
import org.springframework.beans.BeanUtils;

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
public class AddressServiceImpl extends BaseService<AddressMapper, AddressDO> implements IAddressService {
    @Override
    public void saveAddress(AddressDTO addressDTO) {
        AddressDO addressDO = AddressDO.build();
        BeanUtils.copyProperties(addressDTO, addressDO);

        // 上传状态不为空，且设置当前为默认地址，则修改原来的默认地址为正常地址
        if (null != addressDO.getStatus() && StatusConstants.DEFAULT == addressDO.getStatus()) {
            super.updateEntity(addressDO, new QueryWrapper<AddressDO>()
                    .eq(CommonFiledConstants.FILED_USER_ID, "传入当前的店铺ID")
                    .eq(CommonFiledConstants.FILED_STATUS, StatusConstants.NORMAL));
        }

        super.saveEntity(addressDO);
    }

    @Override
    public AddressDO getAddress(Integer id) {
        return super.getEntityById(id);
    }

    @Override
    public void removeAddressById(Integer id) {
        super.removeEntityById(id);
    }

    @Override
    public List<AddressDO> listAddresss() {
        return null;
    }

    @Override
    public void updateAddress(AddressDTO addressDTO) {

    }
}
