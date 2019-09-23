package com.perenc.mall.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.context.BaseContextHandler;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.common.util.Md5Utils;
import com.perenc.mall.merchant.entity.dto.UserDTO;
import com.perenc.mall.merchant.entity.model.RoleDO;
import com.perenc.mall.merchant.entity.model.UserDO;
import com.perenc.mall.merchant.entity.vo.UserVO;
import com.perenc.mall.merchant.mapper.RoleMapper;
import com.perenc.mall.merchant.mapper.UserMapper;
import com.perenc.mall.merchant.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/23 13:40 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class UserServiceImpl extends BaseService<UserMapper, UserDO> implements IUserService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void saveUser(UserDTO userDTO) {
        if (!StringUtils.isBlank(userDTO.getAccount())) {
            UserDO userDO = super.getEntityOne(new QueryWrapper<UserDO>()
                    .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())
                    .eq(CommonFiledConstants.FILED_ACCOUNT, userDTO.getAccount()));
            if (null != userDO) {
                throw new BusinessException("当前账号已使用，请重新输入");
            }
        }

        UserDO userDO = UserDO.build();
        BeanUtils.copyProperties(userDTO, userDO);
        userDO.setPassword(Md5Utils.md5(userDTO.getPassword()));

        super.saveEntity(userDO);

    }

    @Override
    public UserVO getUser(Integer id) {
        UserDO userDO = super.getEntityById(id);
        if (null == userDO) {
            throw new BusinessException("当前用户Id不存在，请重新输入");
        }

        UserVO userVO = UserVO.build();
        BeanUtils.copyProperties(userDO, userVO);

        RoleDO roleDO = roleMapper.selectOne(new QueryWrapper<RoleDO>()
                .eq(CommonFiledConstants.FILED_ID, userDO.getRoleId()));

        if (null == roleDO) {
            throw new BusinessException("角色ID不存在");
        }

        userVO.setRole(roleDO.getName());

        return userVO;
    }

    @Override
    public void removeUserById(Integer id) {
        super.removeEntityById(id);
    }

    @Override
    public List<UserVO> listUsers() {
        List<UserDO> userDOList = super.listEntitys(new QueryWrapper<UserDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId()));
        List<UserVO> userVOList = new ArrayList<>();
        userDOList.forEach(userDO -> {
            UserVO userVO = UserVO.build();
            BeanUtils.copyProperties(userDO, userVO);
            userVOList.add(userVO);
        });
        return userVOList;
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        if (null == userDTO.getId()) {
            throw new BusinessException("当前用户ID不能为空");
        }

        UserDO userDO = UserDO.build();
        BeanUtils.copyProperties(userDTO, userDO);
        userDO.setPassword(Md5Utils.md5(userDTO.getPassword()));
        super.saveEntity(userDO);
    }
}
