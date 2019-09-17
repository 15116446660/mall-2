package com.perenc.mall.common.exception;

import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常捕获操作类
 *
 * @Author: GR
 * @Date: 2019-9-12 23:06 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-12     GR      		
 */
@Slf4j
@RestControllerAdvice("com.perenc.mall")
public class GlobalExceptionHandler {

    /**
     * @description: 处理所有BusinessException
     * @param businessException
     * @return com.perenc.mall.common.result.Result
     * @throws
     * @author: GR
     * @date: 2019-9-13 10:44
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    @ExceptionHandler(BusinessException.class)
    public Result BusinessExceptionHandler(BusinessException businessException) {
        log.info("======捕获businessException：code={}，msg={}", businessException.getCode(), businessException.getMessage());
        return JsonResult.buildResultFail(businessException.getCode(), businessException.getMessage());
    }


    /**
     * @description: 处理所有BusinessException
     * @param validResultException
     * @return com.perenc.mall.common.result.Result
     * @throws
     * @author: GR
     * @date: 2019-9-13 10:44
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    @ExceptionHandler(ValidResultException.class)
    public Result ValidResultExceptionHandler(ValidResultException validResultException) {
        log.info("======捕获validResultException：code={}，msg={}", validResultException.getCode(), validResultException.getMessage());
        return JsonResult.buildResultFail(validResultException.getCode(), validResultException.getMessage());
    }
}
