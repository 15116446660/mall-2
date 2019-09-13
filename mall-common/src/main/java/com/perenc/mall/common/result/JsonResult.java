package com.perenc.mall.common.result;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @ClassName: JsonResult
 * @Description:
 *
 * @Author: GR
 * @Date: 2019-9-13 0:17 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-13     GR      		
 */
@Accessors(chain = true)
@ToString(callSuper = true)
@NoArgsConstructor(staticName = "build")
public class JsonResult extends Result {

    @Getter
    @Setter
    private Object data;

    /**
     * @description: //TODO 构建成功数据
     * @param
     * @return com.perenc.mall.common.result.ResultHanlder
     * @throws
     * @author: GR
     * @date: 2019-9-13 0:22
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR      		
     */
    public static Result buildResultOk() {
        return Result.of(ResultCode.OK.getCode(), ResultCode.OK.getMsg());
    }


    /**
     * @description: //TODO  构建成功数据
     * @param data
     * @return com.perenc.mall.common.result.ResultHanlder
     * @throws
     * @author: GR
     * @date: 2019-9-13 0:28
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    public static Result buildResultOk(Object data) {
        return JsonResult.build().setData(data).setCode(ResultCode.OK.getCode()).setMsg(ResultCode.OK.getMsg());
    }


    /**
     * @description: //TODO 构建成功数据
     * @param msg
     * @param data
     * @return com.perenc.mall.common.result.ResultHanlder
     * @throws
     * @author: GR
     * @date: 2019-9-13 0:29
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    public static Result buildResultOk(String msg, Object data) {
        return JsonResult.build().setData(data).setCode(ResultCode.OK.getCode()).setMsg(msg);
    }

    /**
     * @description: //TODO 构建失败数据
     * @param
     * @return com.perenc.mall.common.result.ResultHanlder
     * @throws
     * @author: GR
     * @date: 2019-9-13 0:30
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    public static Result buildResultFail() {
        return Result.of(ResultCode.FAIL.getCode(), ResultCode.FAIL.getMsg());
    }

    /**
     * @description: //TODO 构建失败数据
     * @param msg
     * @return com.perenc.mall.common.result.ResultHanlder
     * @throws
     * @author: GR
     * @date: 2019-9-13 0:39
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    public static Result buildResultFail(String msg) {
        return Result.of(ResultCode.FAIL.getCode(), msg);
    }


    /**
     * @description: //TODO 构建失败数据
     * @param code
     * @param msg
     * @return com.perenc.mall.common.result.ResultHanlder
     * @throws
     * @author: GR
     * @date: 2019-9-13 0:31
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    public static Result buildResultFail(Integer code, String msg) {
        return Result.of(code, msg);
    }


}
