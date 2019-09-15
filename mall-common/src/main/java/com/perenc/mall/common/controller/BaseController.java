package com.perenc.mall.common.controller;

import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.common.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: BaseController
 * @Description: controller层基类
 *
 * @Author: GR
 * @Date: 2019-9-13 21:42 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-13     GR      		
 */
@Slf4j
public class BaseController<Service extends BaseService, Entity> {

    @Autowired
    protected Service service;

    /**
     * @description: 统一新增保存数据
     * @param entity
     * @return com.perenc.mall.common.result.Result
     * @throws
     * @author: GR
     * @date: 2019-9-13 22:41
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR      		
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result saveEntity(@RequestBody Entity entity) {
        log.info("执行save方法，接收实体对象信息：｛｝", entity.toString());
        service.saveEntity(entity);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 统一批量新增保存数据
     * @param list
     * @return com.perenc.mall.common.result.Result
     * @throws
     * @author: GR
     * @date: 2019-9-13 22:43
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    @RequestMapping(value = "/saveBatch", method = RequestMethod.POST)
    public Result saveEntityBatch(@RequestBody List<Entity> list) {
        service.saveEntityBatch(list);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 统一更新数据
     * @param entity
     * @return com.perenc.mall.common.result.Result
     * @throws
     * @author: GR
     * @date: 2019-9-13 22:43
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateEntity(@RequestBody Entity entity) {
        service.updateEntity(entity);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 统一删除数据
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @throws
     * @author: GR
     * @date: 2019-9-13 22:44
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public Result removeEntityById(@PathVariable Integer id) {
        service.removeEntityById(id);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 统一批量删除数据
     * @param ids
     * @return com.perenc.mall.common.result.Result
     * @throws
     * @author: GR
     * @date: 2019-9-13 22:45
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    @RequestMapping(value = "/removeBatch", method = RequestMethod.POST)
    public Result removeEntityBatchByIds(@RequestBody List<Integer> ids) {
        service.removeEntityBatchByIds(ids);
        return JsonResult.buildResultOk();
    }


    @RequestMapping(value = "/test")
    public Result test() {
        return JsonResult.buildResultOk();
    }
}
