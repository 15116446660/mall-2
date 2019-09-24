package com.perenc.mall.platform.controller;

import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.platform.entity.model.ComplainDO;
import com.perenc.mall.platform.entity.vo.ComplainVO;
import com.perenc.mall.platform.service.IComplainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className ComplainController
 * @description 投诉相关
 *
 * @author GR
 * @date 2019/9/24 18:55 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/24     GR     		
 */
@Slf4j
@RestController
@RequestMapping("complain")
public class ComplainController {

    @Autowired
    private IComplainService complainService;

    /**
     * @description 分页查询投诉列表
     * @param currentPage
     * @param pageSize
     * @return com.perenc.mall.common.result.Result
     * @author GR
     * @date 2019/9/24 19:00
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public Result listComplain(Integer currentPage, Integer pageSize) {
        return JsonResult.buildResultOk();
    }


    /**
     * @description
     * @param complainId
     * @return com.perenc.mall.platform.entity.vo.ComplainVO
     * @author GR
     * @date 2019/9/24 19:03
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ComplainVO getComplainDetails(Integer complainId) {
        return ComplainVO.build();
    }
}

