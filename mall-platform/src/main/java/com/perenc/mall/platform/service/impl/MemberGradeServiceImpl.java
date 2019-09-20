package com.perenc.mall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.platform.entity.dto.MemberGradeDTO;
import com.perenc.mall.platform.entity.model.MemberDO;
import com.perenc.mall.platform.entity.model.MemberGradeDO;
import com.perenc.mall.platform.entity.vo.MemberGradeVO;
import com.perenc.mall.platform.mapper.MemberGradeMapper;
import com.perenc.mall.platform.service.IMemberGradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MemberGradeServiceImpl
 * @Description:
 *
 * @Author: GR
 * @Date: 2019-9-14 17:45 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class MemberGradeServiceImpl extends BaseService<MemberGradeMapper, MemberGradeDO> implements IMemberGradeService {
    @Override
    public void saveMemberGrade(MemberGradeDTO memberGradeDTO) {
        MemberGradeDO memberGradeDO = MemberGradeDO.build();
        BeanUtils.copyProperties(memberGradeDTO, memberGradeDO);

        auditNameAndLevel(memberGradeDO);

        super.saveEntity(memberGradeDO);
    }

    @Override
    public MemberGradeVO getMemberGrade(Integer id) {
        MemberGradeDO memberGradeDO = super.getEntityById(id);
        if (null == memberGradeDO) {
            throw new BusinessException("当前会员等级ID不存在");
        }
        MemberGradeVO memberGradeVO = MemberGradeVO.build();
        BeanUtils.copyProperties(memberGradeDO, memberGradeVO);
        return memberGradeVO;
    }

    @Override
    public void removeMemberGradeById(Integer id) {
        super.removeEntityById(id);
    }

    @Override
    public List<MemberGradeVO> listMemberGrade() {
        List<MemberGradeDO> memberGradeDOList = super.listEntitys(null);
        List<MemberGradeVO> memberGradeVOList = new ArrayList<>();
        memberGradeDOList.forEach(memberGradeDO -> {
            MemberGradeVO memberGradeVO = MemberGradeVO.build();
            BeanUtils.copyProperties(memberGradeDO, memberGradeVO);
            memberGradeVOList.add(memberGradeVO);
        });
        return memberGradeVOList;
    }

    @Override
    public void updateMemberGrade(MemberGradeDTO memberGradeDTO) {
        MemberGradeDO memberGradeDO = MemberGradeDO.build();
        BeanUtils.copyProperties(memberGradeDTO, memberGradeDO);
        
        auditNameAndLevel(memberGradeDO);

        super.updateEntity(memberGradeDO);

    }

    /**
     * @description: 校验当前会员等级名称和会员等级是否被占用
     * @param memberGradeDO
     * @return void
     * @author: GR
     * @date: 2019/9/20
     */
    private void auditNameAndLevel(MemberGradeDO memberGradeDO) {
        // 查询当前会员等级或会员名称是否被占用
        MemberGradeDO oldMemberGradeDO = super.getEntityOne(new QueryWrapper<MemberGradeDO>()
                .eq(CommonFiledConstants.FILED_LEVEL, memberGradeDO.getLevel())
                .or()
                .eq(CommonFiledConstants.FILED_NAME, memberGradeDO.getName()));
        if (null != oldMemberGradeDO && oldMemberGradeDO.getName().equals(memberGradeDO.getName())) {
            throw new BusinessException("当前会员名称已被占用，请重新设置");
        }

        if (null != oldMemberGradeDO && oldMemberGradeDO.getLevel().equals(memberGradeDO.getLevel())) {
            throw new BusinessException("当前会员等级已被占用，请重新设置");
        }
    }
}
