package com.perenc.mall.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
     * @description: 根据ID获取单个实体对象
     * @param id
     * @return java.lang.Object
     * @throws
     * @author: GR
     * @date: 2019-9-15 18:17
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-15       GR
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result getEntityById(Integer id) {
        log.info("执行get方法，接收实体对象ID为{}", id);
        Object entity = service.getEntityById(id);
        log.info("获取实体对象信息：{}", entity.toString());
        return JsonResult.buildResultOk(entity);
    }


    /**
     * @description: 分页、类型列表
     * @param currentPage
     * @param total
     * @param type
     * @return com.perenc.mall.common.result.Result
     * @throws
     * @author: GR
     * @date: 2019-9-15 21:52
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-15       GR
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result listEntitys(Integer currentPage, Integer total, Integer type) {
        log.info("执行list方法，接收分页信息currentPage={},total={},type={}", currentPage, total, type);
        QueryWrapper<Entity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        if (null == currentPage || null == total) {
            log.info("未见检验到分页信息参数，执行查询所有信息");
            List list = service.listEntitys(queryWrapper);
            log.info("获取所有实体对象数组信息：{}", list.toString());
            return JsonResult.buildResultOk(list);
        }

        Page<Entity> page = new Page<>(currentPage, total);
        IPage iPage = service.listEntitysByPage(page, queryWrapper);
        List<Entity> records = iPage.getRecords();
        log.info("获取分页实体对象数组信息：{}", records.toString());
        return JsonResult.buildResultOk(records);
    }


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
        log.info("执行save方法，接收实体对象信息：{}", entity.toString());
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
        log.info("执行saveBatch方法，接收到{}个实体对象", list.size());
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
        log.info("执行update方法，接收实体对象信息：{}", entity.toString());
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
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public Result removeEntityById(Integer id) {
        log.info("执行remove方法，接收实体对象信息ID为{}", id);
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
        log.info("执行removeBatch方法，接收实体数组对象信息ID为{}", ids);
        service.removeEntityBatchByIds(ids);
        return JsonResult.buildResultOk();
    }

}
