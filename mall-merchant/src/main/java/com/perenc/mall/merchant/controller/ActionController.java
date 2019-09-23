package com.perenc.mall.merchant.controller;

import ch.qos.logback.core.joran.action.ActionConst;
import com.perenc.mall.common.constant.ActionTypeConstants;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.ActionMoneyOffDTO;
import com.perenc.mall.merchant.entity.vo.ActionMoneyOffVO;
import com.perenc.mall.merchant.service.IActionMoneyOffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: ActionMoneyOffController
 * @Description: 店铺相关活动
 *
 * @Author: GR
 * @Date: 2019/9/20 20:50 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
@Slf4j
@RestController
@RequestMapping("action")
public class ActionController {


    @Autowired
    private IActionMoneyOffService service;

    /**
     * @description: 添加活动——满减
     * @param actionMoneyOffDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "/mo/add", method = RequestMethod.POST)
    public Result addActionMoneyOff(@RequestBody @Valid ActionMoneyOffDTO actionMoneyOffDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveActionMoneyOff(actionMoneyOffDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 活动——满减更新
     * @param actionMoneyOffDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "/mo/update", method = RequestMethod.POST)
    public Result updateActionMoneyOff(@RequestBody ActionMoneyOffDTO actionMoneyOffDTO) {
        service.updateActionMoneyOff(actionMoneyOffDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取活动——满减列表，默认升序排列返回
     * @param currentPage
     * @param pageSize
     * @param type
     * @return com.perenc.mall.common.vo.PageVO
     * @author: GR
     * @date: 2019/9/23
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public PageVO listActionMoneyOffs(int currentPage,
                                      int pageSize,
                                      @RequestParam(required = false) Integer type) {
        if (null == type || ActionTypeConstants.ACTION_MONEY_OFF == type) {
            return service.listActionMoneyOffs(currentPage, pageSize);
        }

        return PageVO.build();
    }


    /**
     * @description: 获取活动——满减商品列表，默认升序排列返回
     * @param actionId
     * @param currentPage
     * @param pageSize
     * @param type
     * @return com.perenc.mall.common.vo.PageVO
     * @author: GR
     * @date: 2019/9/23
     */
    @RequestMapping(value = "/actionGoods/list", method = RequestMethod.POST)
    public PageVO listActionGoods(int actionId,
                                  int currentPage,
                                  int pageSize,
                                  @RequestParam(required = false) Integer type) {
        if (null == type || ActionTypeConstants.ACTION_MONEY_OFF == type) {
            return service.listActionMoneyOffs(actionId, currentPage, pageSize);
        }

        return PageVO.build();
    }

    /**
     * @description: 根据指定ID获取ActionMoneyOff信息
     * @param id
     * @return com.perenc.mall.platform.entity.vo.ActionMoneyOffVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public Object getAction(@RequestParam Integer id,
                            @RequestParam(required = false) Integer type) {
        if (null == type || ActionTypeConstants.ACTION_MONEY_OFF == type) {
            ActionMoneyOffVO actionMoneyOffVO = service.getActionMoneyOff(id);
            if (null == actionMoneyOffVO) {
                throw new BusinessException("ID为" + id + "的活动——满减不存在");
            }
        }

        return JsonResult.buildResultFail();
    }

    /**
     * @description: 活动——满减删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removeAction(@RequestParam Integer id,
                               @RequestParam(required = false) Integer type) {
        if (null == type || ActionTypeConstants.ACTION_MONEY_OFF == type) {
            service.removeActionMoneyOffById(id);
        }

        return JsonResult.buildResultOk();
    }

}
