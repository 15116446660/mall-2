package com.perenc.mall.merchant.controller;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.AddressDTO;
import com.perenc.mall.merchant.entity.vo.AddressVO;
import com.perenc.mall.merchant.service.IAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: AddressController
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/20 20:49 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Slf4j
@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private IAddressService service;

    /**
     * @description: 添加地址
     * @param addressDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addAddress(@RequestBody @Valid AddressDTO addressDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveAddress(addressDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取地址列表，默认升序排列返回
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.AddressVO>
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public PageVO<AddressVO> listAddresss(Integer currentPage, Integer pageSize) {
        PageVO<AddressVO> pageVO = service.listAddress(currentPage, pageSize);
        return pageVO;
    }

    /**
     * @description: 根据指定ID获取Address信息
     * @param id
     * @return com.perenc.mall.platform.entity.vo.AddressVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public AddressVO getAddress(@RequestParam Integer id) {
        AddressVO addressVO = service.getAddress(id);
        if (null == addressVO) {
            throw new BusinessException("ID为" + id + "的地址不存在");
        }
        return addressVO;
    }

    /**
     * @description: 地址更新
     * @param addressDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateAddress(@RequestBody AddressDTO addressDTO) {
        service.updateAddress(addressDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 地址删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removeAddress(@RequestParam Integer id) {
        service.removeAddressById(id);
        return JsonResult.buildResultOk();
    }

}
