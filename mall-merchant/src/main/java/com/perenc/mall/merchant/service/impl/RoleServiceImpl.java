package com.perenc.mall.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.constant.CommonConstants;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.constant.ContextConstants;
import com.perenc.mall.common.constant.PunctuationConstants;
import com.perenc.mall.common.context.BaseContextHandler;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.common.util.EntityUtils;
import com.perenc.mall.common.util.ListUtils;
import com.perenc.mall.merchant.entity.dto.RoleDTO;
import com.perenc.mall.merchant.entity.model.RelatedRoleMenuDO;
import com.perenc.mall.merchant.entity.model.RoleDO;
import com.perenc.mall.merchant.entity.model.SysMenuDO;
import com.perenc.mall.merchant.entity.vo.RoleVO;
import com.perenc.mall.merchant.entity.vo.SysMenuVO;
import com.perenc.mall.merchant.mapper.RelatedRoleMennuMapper;
import com.perenc.mall.merchant.mapper.RoleMapper;
import com.perenc.mall.merchant.mapper.SysMenuMapper;
import com.perenc.mall.merchant.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: RoleServiceImpl
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/23 9:52 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class RoleServiceImpl extends BaseService<RoleMapper, RoleDO> implements IRoleService {

    @Autowired
    private RelatedRoleMennuMapper relatedRoleMennuMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public void saveRole(RoleDTO roleDTO) {

        // 校验角色名称是否被占用
        if (!StringUtils.isBlank(roleDTO.getName())) {
            RoleDO roleDO = super.getEntityOne(new QueryWrapper<RoleDO>()
                    .eq(CommonFiledConstants.FILED_NAME, roleDTO.getName()));
            if (null != roleDO) {
                throw new BusinessException("角色名称已存在，请重新输入");
            }
        }

        RoleDO roleDO = RoleDO.build();
        BeanUtils.copyProperties(roleDTO, roleDO);
        // 保存实体对象时会将当前对象ID返回给原对象
        super.saveEntity(roleDO);
        // 获取上传到菜单ID列表
        List<Integer> menuIdList = ListUtils.getIntegerListByString(roleDTO.getMenuValues(), PunctuationConstants.COMMAS);
        // 去重
        menuIdList = menuIdList.stream().distinct().collect(Collectors.toList());
        menuIdList.forEach(menuId -> {
            // 便利存储
            RelatedRoleMenuDO relatedRoleMenuDO = RelatedRoleMenuDO.build()
                    .setMenuId(menuId)
                    .setRoleId(roleDO.getId());
            EntityUtils.build().setCreateInfo(relatedRoleMenuDO);
            relatedRoleMennuMapper.insert(relatedRoleMenuDO);
        });
    }

    @Override
    public RoleVO getRole(Integer id) {
        RoleDO roleDO = super.getEntityById(id);
        if (null == roleDO) {
            throw new BusinessException("当前角色ID不存在，请重新输入");
        }
        // 查询当前角色所拥有的所有权限
        List<RelatedRoleMenuDO> relatedRoleMenuDOList = relatedRoleMennuMapper.selectList(new QueryWrapper<RelatedRoleMenuDO>()
                .eq(CommonFiledConstants.FILED_ROLE_ID, roleDO.getId()));
        // 去重
        Set<Integer> menuIdList = new HashSet<>();
        relatedRoleMenuDOList.forEach(relatedRoleMenuDO -> menuIdList.add(relatedRoleMenuDO.getMenuId()));

        List<SysMenuDO> sysMenuDOList = sysMenuMapper.selectBatchIds(menuIdList);
        // 递归排列
        List<SysMenuVO> sysMenuVOList = recursiveQuery(sysMenuDOList, CommonConstants.NUMBER_ZERO);

        // 封装角色显示数据
        RoleVO roleVO = RoleVO.build();
        BeanUtils.copyProperties(roleDO, roleVO);
        // 设置当前角色对象信息
        roleVO.setList(sysMenuVOList);

        return roleVO;
    }

    @Override
    public void removeRoleById(Integer id) {
        RoleDO roleDO = super.getEntityById(id);
        if (null == roleDO) {
            throw new BusinessException("当前角色ID不存在，请重新输入");
        }

        // 删除所有关联的角色——菜单信息
        relatedRoleMennuMapper.delete(new QueryWrapper<RelatedRoleMenuDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, roleDO.getStoreId())
                .eq(CommonFiledConstants.FILED_ROLE_ID, roleDO.getId()));

        super.removeEntityById(id);
    }

    @Override
    public List<RoleVO> listRoles() {
        List<RoleDO> roleDOList = super.listEntitys(new QueryWrapper<RoleDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId()));

        List<RoleVO> roleVOList = new ArrayList<>();
        roleDOList.forEach(roleDO -> {
            RoleVO roleVO = RoleVO.build();
            BeanUtils.copyProperties(roleDO, roleVO);
            roleVOList.add(roleVO);
        });
        return roleVOList;
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
    public void updateRole(RoleDTO roleDTO) {
        if (null == roleDTO.getId()) {
            throw new BusinessException("当前ID不能为空");
        }

        // 获取当前角色对象
        RoleDO roleDO = super.getEntityById(roleDTO.getId());
        if (null == roleDO) {
            throw new BusinessException("当前ID不存在，请输入正确的角色ID");
        }

        // 获取已存在的所有role——menu
        List<RelatedRoleMenuDO> oldRelatedRoleMenuDOList = relatedRoleMennuMapper.selectList(new QueryWrapper<RelatedRoleMenuDO>()
                .eq(CommonFiledConstants.FILED_ROLE_ID, roleDO.getId()));
        List<Integer> oldMenuIdList = new ArrayList<>();
        oldRelatedRoleMenuDOList.forEach(relatedRoleMenuDO -> oldMenuIdList.add(relatedRoleMenuDO.getMenuId()));
        // 获取新上传的菜单ID列表
        List<Integer> newMenuIdList = ListUtils.getIntegerListByString(roleDTO.getMenuValues(), PunctuationConstants.COMMAS);
        // 过滤要删除的菜单ID
        Set<Integer> deleteMenuIdList = ListUtils.getNotExistBySource(newMenuIdList, oldMenuIdList);
        // 过滤要新增插入的菜单ID
        Set<Integer> insertMenuIdList = ListUtils.getNotExistBySource(oldMenuIdList, newMenuIdList);
        // 根据menuId与RoleId删除指定数据
        if (deleteMenuIdList.size() > 0) {
            relatedRoleMennuMapper.delete(new QueryWrapper<RelatedRoleMenuDO>()
                    .eq(CommonFiledConstants.FILED_ROLE_ID, roleDO.getId())
                    .in(CommonFiledConstants.FILED_MENU_ID, deleteMenuIdList));
        }

        // 遍历插入
        insertMenuIdList.forEach(menuId -> {
            RelatedRoleMenuDO relatedRoleMenuDO = RelatedRoleMenuDO.build()
                    .setRoleId(roleDO.getId())
                    .setMenuId(menuId);
            EntityUtils.build().setCreateInfo(relatedRoleMenuDO);
            relatedRoleMennuMapper.insert(relatedRoleMenuDO);
        });

        super.updateEntity(roleDO);
    }

}
