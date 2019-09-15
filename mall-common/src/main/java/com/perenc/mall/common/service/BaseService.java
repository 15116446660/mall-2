package com.perenc.mall.common.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: BaseService
 * @Description:
 *
 * @Author: GR
 * @Date: 2019-9-13 19:36 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-13     GR      		
 */
public class BaseService<M extends BaseMapper<T>, T> {

    @Autowired
    protected M mapper;

    /**
     * @description: 根据唯一标识获取实体对象
     * @param id
     * @return T
     * @throws
     * @author: GR
     * @date: 2019-9-13 19:26
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    public T getEntityById(Integer id) {
        return mapper.selectById(id);
    }

    /**
     * @description: 分页获取所有实体对象
     * @param page
     * @param wrapper
     * @return com.baomidou.mybatisplus.core.metadata.IPage<T>
     * @throws
     * @author: GR
     * @date: 2019-9-13 19:54
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    public IPage<T> listEntitysByPage(IPage<T> page, Wrapper<T> wrapper) {
        return mapper.selectPage(page, wrapper);
    }


    /**
     * @description: 获取所有实体对象
     * @param wrapper
     * @return java.util.List<T>
     * @throws
     * @author: GR
     * @date: 2019-9-15 18:38
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-15       GR
     */
    public List<T> listEntitys(Wrapper<T> wrapper) {
        return mapper.selectList(wrapper);
    }

    /**
     * @description: 保存实体对象
     * @param entity
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-9-13 19:24
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    public void saveEntity(T entity) {
        mapper.insert(entity);
    }

    /**
     * @description: 批量存储实体对象
     * @param list
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-9-13 22:37
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    public void saveEntityBatch(List<T> list) {

    }

    /**
     * @description: 根据唯一标识移除实体对象
     * @param id
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-9-13 19:24
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    public void removeEntityById(Integer id) {
        mapper.deleteById(id);
    }

    /**
     * @description: 批量删除实体对象
     * @param ids
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-9-13 19:24
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    public void removeEntityBatchByIds(List<Integer> ids) {
        mapper.deleteBatchIds(ids);
    }

    /**
     * @description: 根据唯一标识更新实体对象
     * @param entity
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-9-13 19:23
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    public void updateEntity(T entity) {
        mapper.updateById(entity);
    }
}
