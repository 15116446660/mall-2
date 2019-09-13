package com.perenc.mall.common.result;

import com.perenc.mall.common.annotation.ResultHanlder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @ClassName: ResultAdvice
 * @Description: 全局结果返回操作预处理  //TODO 后续可做数据的加密处理
 *
 * @Author: GR
 * @Date: 2019-9-13 0:44
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-13     GR
 */
@Slf4j
@RestControllerAdvice("com.perenc.mall")
public class ResultAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //异常结果返回的Result不用处理
        if (o instanceof Result) {
            return o;
        }

        // 获取Controller返回值中的状态code、描述msg
        ResultHanlder annotation = methodParameter.getMethod().getAnnotation(ResultHanlder.class);
        if (null == annotation) {
            Result result = JsonResult.buildResultOk(o);
            log.info(result.toString());
            return result;
        }

        Result result = JsonResult.buildResultOk(annotation.msg(), o);
        log.info(result.toString());
        return result;
    }
}
