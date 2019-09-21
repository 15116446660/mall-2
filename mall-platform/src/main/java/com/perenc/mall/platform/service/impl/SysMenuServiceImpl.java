package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.constant.CommonConstants;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.dto.SysMenuDTO;
import com.perenc.mall.platform.entity.model.SysMenuDO;
import com.perenc.mall.platform.entity.vo.SysMenuVO;
import com.perenc.mall.platform.mapper.SysMenuMapper;
import com.perenc.mall.platform.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SysMenuServiceImpl
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/21 15:27 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/21     GR     		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class SysMenuServiceImpl extends BaseService<SysMenuMapper, SysMenuDO> implements ISysMenuService {
    @Override
    public void saveSysMenu(SysMenuDTO sysMenuDTO) {
        SysMenuDO sysMenuDO = SysMenuDO.build();
        BeanUtils.copyProperties(sysMenuDTO, sysMenuDO);

        auditSysMenuName(sysMenuDO);

        super.saveEntity(sysMenuDO);
    }

    @Override
    public SysMenuVO getSysMenu(Integer id) {
        SysMenuDO sysMenuDO = super.getEntityById(id);
        // 系统菜单不存在
        if (null == sysMenuDO) {
            throw new BusinessException("请输入正确的系统菜单ID");
        }

        SysMenuVO sysMenuVO = SysMenuVO.build();
        BeanUtils.copyProperties(sysMenuDO, sysMenuVO);
        // 若为顶级分类，则直接返回
        if (CommonConstants.NUMBER_ZERO == sysMenuDO.getParentId()) {
            return sysMenuVO;
        }

        // 获取父级分类
        while (true) {
            SysMenuVO buildVO = SysMenuVO.build();
            // 存储当前菜单
            BeanUtils.copyProperties(sysMenuDO, buildVO);
            // 获取上级菜单
            sysMenuDO = super.getEntityById(sysMenuDO.getParentId());
            if (null == sysMenuDO) {
                break;
            }

            buildVO.setParentId(sysMenuDO.getId());
            buildVO.setParentName(sysMenuDO.getName());
            BeanUtils.copyProperties(sysMenuDO, sysMenuVO);
            sysMenuVO.add(buildVO);
        }
        return sysMenuVO;
    }

    @Override
    public void removeSysMenuById(Integer id) {
        SysMenuDO sysMenuDO = super.getEntityById(id);
        if (null == sysMenuDO) {
            throw new BusinessException("当前菜单ID不存在");
        }

        if (CommonConstants.NUMBER_ZERO == sysMenuDO.getParentId()) {
            super.removeEntityById(id);
        }

        // 方式一
        List<SysMenuDO> sysMenuDOList = super.listEntitys(null);
        List<SysMenuVO> sysMenuVOList = recursiveQuery(sysMenuDOList, id);
        List<Integer> sysMenuDOIdList = new ArrayList<>();
        // 遍历所有要删除的菜单ID
        sysMenuVOList.forEach(sysMenuVO -> {
            if (sysMenuDO.getId().intValue() != id) {
                sysMenuDOIdList.add(sysMenuDO.getId());
            }
        });

        super.removeEntityBatchByIds(sysMenuDOIdList);

    }


    /**
     * @description: 递归查询
     * @param parentId
     * @return void
     * @author: GR
     * @date: 2019/9/19
     */
    public List<SysMenuVO> recursiveQuery(List<SysMenuDO> sysMenuDOList, int parentId) {
        List<SysMenuVO> parentList = new ArrayList<>();
        sysMenuDOList.forEach(sysMenuDO -> {
            if (parentId == sysMenuDO.getParentId()) {
                parentList.add(SysMenuVO.build()
                        .setId(sysMenuDO.getId())
                        .setName(sysMenuDO.getName())
                        .setParentId(sysMenuDO.getParentId())
                        .setChildren(recursiveQuery(sysMenuDOList, sysMenuDO.getId())));
            }
        });
        return parentList;
    }

    @Override
    public List<SysMenuVO> listSysMenu() {
        List<SysMenuDO> sysMenuDOList = super.listEntitys(null);
        return recursiveQuery(sysMenuDOList, CommonConstants.NUMBER_ZERO);
    }

    @Override
    public void updateSysMenu(SysMenuDTO sysMenuDTO) {
        if (null == sysMenuDTO.getId()) {
            throw new BusinessException("菜单ID不能为空");
        }

        SysMenuDO sysMenuDO = SysMenuDO.build();
        BeanUtils.copyProperties(sysMenuDTO, sysMenuDO);
        // 不设置上级分类，默认此次分类为顶级分类，默认parent为0；
        if (null == sysMenuDO.getParentId()) {
            sysMenuDO.setParentId(CommonConstants.NUMBER_ZERO);
        }
        super.updateEntity(sysMenuDO);
    }

    /**
     * @description: 校验系统菜单是否为空
     * @param sysMenuDO
     * @return void
     * @author: GR
     * @date: 2019/9/21
     */
    private void auditSysMenuName(SysMenuDO sysMenuDO) {
        if (StringUtils.isBlank(sysMenuDO.getName())) {
            return;
        }

        // 插查询菜单名称是否被占用
        SysMenuDO entityOne = super.getEntityOne(new QueryWrapper<SysMenuDO>()
                .eq(CommonFiledConstants.FILED_NAME, sysMenuDO.getName()));

        if (null != entityOne) {
            throw new BusinessException("系统菜单名称已被使用，请重新输入");
        }
    }
}
