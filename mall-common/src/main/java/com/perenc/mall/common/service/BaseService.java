package com.perenc.mall.common.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.perenc.mall.common.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: BaseService
 * @Description:
 *
 * @Author: GR
 * @Date: 2019-9-13 19:36
 *                    .::::.
 *                  .::::::::.
 *                 :::::::::::
 *             ..:::::::::::'
 *           '::::::::::::'
 *             .::::::::::
 *        '::::::::::::::..
 *             ..::::::::::::.
 *           ``::::::::::::::::
 *            ::::``:::::::::'        .:::.
 *           ::::'   ':::::'       .::::::::.
 *         .::::'      ::::     .:::::::'::::.
 *        .:::'       :::::  .:::::::::' ':::::.
 *       .::'        :::::.:::::::::'      ':::::.
 *      .::'         ::::::::::::::'         ``::::.
 *  ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 *                    '.:::::'                    ':'````..
 * ..........................................................
 *          美女保佑             永无BUG
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
     * @description: 根据查询条件查询唯一数据
     * @param queryWrapper
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
    public T getEntityOne(QueryWrapper<T> queryWrapper) {
        return mapper.selectOne(queryWrapper);
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
        EntityUtils.build().setCreateInfo(entity);
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
        for (T t : list) {
            EntityUtils.build().setCreateInfo(t);
            mapper.insert(t);
        }
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
     * @description: 根据条件Map删除对象
     * @param map
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
    public void removeEntityByMap(Map<String, Object> map) {
        mapper.deleteByMap(map);
    }

    /**
     * @description: 批量删除实体对象
     * @param wrapper
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
    public void removeEntityByWrapper(Wrapper<T> wrapper) {
        mapper.delete(wrapper);
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
        EntityUtils.build().setUpdatedInfo(entity);
        mapper.updateById(entity);
    }
}
