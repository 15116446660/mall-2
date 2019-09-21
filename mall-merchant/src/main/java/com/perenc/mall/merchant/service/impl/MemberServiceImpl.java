package com.perenc.mall.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.context.BaseContextHandler;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.MemberDTO;
import com.perenc.mall.merchant.entity.model.MemberDO;
import com.perenc.mall.merchant.entity.model.PlatFormMemberDO;
import com.perenc.mall.merchant.entity.vo.MemberVO;
import com.perenc.mall.merchant.mapper.MemberMapper;
import com.perenc.mall.merchant.mapper.PlatformMemberMapper;
import com.perenc.mall.merchant.service.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MemberServiceImpl
 * @Description: 会员服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 17:32 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class MemberServiceImpl extends BaseService<MemberMapper, MemberDO> implements IMemberService {

    @Autowired
    private PlatformMemberMapper platformMemberMapper;

    @Override
    public void saveMember(MemberDTO memberDTO) {
        MemberDO memberDO = MemberDO.build();
        BeanUtils.copyProperties(memberDTO, memberDO);
        // 校验当前手机号是否存在
        if (StringUtils.isBlank(memberDTO.getPhone())) {
            throw new BusinessException("请输入手机号");
        }

        // 平台端查询当前电话号码是否已存在
        PlatFormMemberDO platFormMemberDO = platformMemberMapper.selectOne(new QueryWrapper<PlatFormMemberDO>()
                .eq(CommonFiledConstants.FILED_PHONE, memberDTO.getPhone()));
        if (null == platFormMemberDO) {
            throw new BusinessException("请先注册成为平台会员并绑定手机号");
        }

        memberDO.setPlatformMemberId(platFormMemberDO.getId());

        super.saveEntity(memberDO);
    }

    @Override
    public MemberVO getMember(Integer id) {
        MemberDO memberDO = super.getEntityById(id);
        if (null == memberDO) {
            throw new BusinessException("当前用户会员不存在");
        }

        PlatFormMemberDO platFormMemberDO = platformMemberMapper.selectOne(new QueryWrapper<PlatFormMemberDO>()
                .eq(CommonFiledConstants.FILED_ID, memberDO.getPlatformMemberId()));

        MemberVO memberVO = MemberVO.build();
        // 获取平台的基本会员数据
        BeanUtils.copyProperties(platFormMemberDO, memberVO);
        // 获取店铺独有的会员数据
        BeanUtils.copyProperties(memberDO, memberVO);

        return memberVO;
    }

    @Override
    public void removeMemberById(Integer id) {
        super.removeEntityById(id);
    }

    @Override
    public PageVO listMember(int currentPage, int pageSize, String name, String phone, Integer level, Integer sex) {
        Page<PlatFormMemberDO> page = new Page<>(currentPage, pageSize);
        QueryWrapper<PlatFormMemberDO> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(name)) {
            queryWrapper.like("name", name);
        }

        if (!StringUtils.isBlank(phone)) {
            queryWrapper.eq("phone", phone);
        }

//        if (null != level) {
//            queryWrapper.eq("level", level);
//        }

        if (null != sex) {
            queryWrapper.eq("sex", sex);
        }

//        IPage<PlatFormMemberDO> iPage = super.listEntitysByPage(page, queryWrapper);
//        List<MemberDO> storeDOList = iPage.getRecords();
//        List<MemberVO> storeVOList = new ArrayList<>();
//        storeDOList.forEach(storeDO -> {
//            MemberVO storeVO = MemberVO.build();
//            BeanUtils.copyProperties(storeDO, storeVO);
//            storeVOList.add(storeVO);
//        });
//
//        return PageVO.<MemberVO>build().setCurrentPage(currentPage)
//                .setPageSize(pageSize)
//                .setTotal(super.count(queryWrapper))
//                .setList(storeVOList);
        return PageVO.build();
    }


    @Override
    public void updateMember(MemberDTO memberDTO) {
//        MemberDO memberDO = MemberDO.build();
//        BeanUtils.copyProperties(memberDTO, memberDO);
//        // 校验当前手机号是否存在
//        if (!StringUtils.isBlank(memberDO.getPhone())) {
//            MemberDO oldMemberDO = super.getEntityOne(new QueryWrapper<MemberDO>()
//                    .eq(CommonFiledConstants.FILED_PHONE, memberDO.getPhone()));
//            // 判断当前会员是否是同一个会员
//            if (null != oldMemberDO && !oldMemberDO.getId().equals(memberDO.getId())) {
//                throw new BusinessException("当前手机号已被注册,请重新输入");
//            }
//        }
//        super.updateEntity(memberDO);
    }
}
