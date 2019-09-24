package com.perenc.mall.merchant.service;


import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.MemberGradeDTO;
import com.perenc.mall.merchant.entity.vo.MemberGradeVO;

import java.util.List;

/**
 * @ClassName: IMemberGradeService
 * @Description: 会员等级服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 17:44 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
public interface IMemberGradeService {

    /**
     * @description: 添加会员等级
     * @param memberGradeDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveMemberGrade(MemberGradeDTO memberGradeDTO);

    /**
     * @description: 根据ID获取会员等级信息
     * @param id
     * @return com.perenc.mall.platform.entity.model.MemberGradeVO
     * @author: GR
     * @date: 2019/9/17
     */
    MemberGradeVO getMemberGrade(Integer id);


    /**
     * @description: 根据ID移除会员等级信息
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeMemberGradeById(Integer id);


    /**
     * @description: 获取会员等级信息列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.model.MemberGradeDO>
     * @author: GR
     * @date: 2019/9/19
     */
    PageVO<MemberGradeVO> listMemberGrade(Integer currentPage, Integer pageSize);

    /**
     * @description: 更新会员等级信息
     * @param memberGradeDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void updateMemberGrade(MemberGradeDTO memberGradeDTO);
}
