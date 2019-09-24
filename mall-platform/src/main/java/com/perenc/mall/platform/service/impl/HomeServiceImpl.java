package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.perenc.mall.platform.entity.model.MemberDO;
import com.perenc.mall.platform.entity.model.OrderDO;
import com.perenc.mall.platform.entity.model.StoreDO;
import com.perenc.mall.platform.mapper.MemberMapper;
import com.perenc.mall.platform.mapper.OrderMapper;
import com.perenc.mall.platform.mapper.StoreMapper;
import com.perenc.mall.platform.service.IHomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className HomeServiceImpl
 * @description
 *
 * @author GR
 * @date 2019/9/24 10:23 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/24     GR     		
 */
@Slf4j
@Service
public class HomeServiceImpl implements IHomeService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private OrderMapper orderMapper;

    private <M> Object selectInfo(BaseMapper<M> mapper) {
        Map<String, Object> map = new HashMap<>();
        // 查询今日新增会员数
        QueryWrapper<M> toDayNewAddQueryWrapper = new QueryWrapper<M>()
                .last("WHERE TO_DAYS(create_time) = TO_DAYS(NOW())");
        // 今日新增会员
        Integer toDayNewAddNumber = mapper.selectCount(toDayNewAddQueryWrapper);

        // 查询昨日新增会员数
        QueryWrapper<M> yesterdayAddQueryWrapper = new QueryWrapper<M>()
                .last("WHERE DATE_SUB(CURDATE(), INTERVAL 1 DAY) = DATE(create_time)");
        // 今日新增会员
        Integer yesterdayNewAddNumber = mapper.selectCount(yesterdayAddQueryWrapper);
        // 统计所有会员数
        Integer totalNumber = mapper.selectCount(null);

        map.put("toDayNumber", toDayNewAddNumber);
        map.put("yesterdayNumber", yesterdayNewAddNumber);
        map.put("totalNumber", totalNumber);
        return map;
    }

    @Override
    public Object searchMemberInfo() {
        return selectInfo(memberMapper);
    }

    @Override
    public Object searchStoreInfo() {
        return selectInfo(storeMapper);
    }

    @Override
    public Object searchOrderInfo() {
        return selectInfo(orderMapper);
    }

    private <M> Object selectGraphByDate(BaseMapper<M> mapper, String startDate, String endDate) {
        StringBuilder sqlBuild = new StringBuilder();
        sqlBuild.append("WHERE  DATE(create_time) >= DATE_FORMAT(");
        sqlBuild.append(startDate);
        sqlBuild.append(",'%Y-%m-%d') AND DATE(create_time) <= DATE_FORMAT(");
        sqlBuild.append(endDate);
        sqlBuild.append(",'%Y-%m-%d') LIMIT 0 , 10");
        QueryWrapper<M> queryWrapper = new QueryWrapper<M>()
                .last(sqlBuild.toString());
        List<M> selectList = mapper.selectList(queryWrapper);
        return selectList;
    }

    @Override
    public Object searchGraphByDate(String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        Object member = selectGraphByDate(memberMapper, startDate, endDate);
        Object store = selectGraphByDate(storeMapper, startDate, endDate);
        Object order = selectGraphByDate(orderMapper, startDate, endDate);
        map.put("member", member);
        map.put("store", store);
        map.put("order", order);
        return map;
    }

    @Override
    public Object searchHotStoreByType(Integer type) {
        
        return null;
    }

    @Override
    public Object searchNotice() {
        return null;
    }
}
