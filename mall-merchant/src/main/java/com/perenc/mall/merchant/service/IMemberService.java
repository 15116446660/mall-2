package com.perenc.mall.merchant.service;

import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.MemberDTO;
import com.perenc.mall.merchant.entity.vo.MemberVO;

/**
 * @ClassName: IMemberService
 * @Description: 会员服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 17:31 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
public interface IMemberService {
    /**
     * @description: 添加会员
     * @param memberDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveMember(MemberDTO memberDTO);

    /**
     * @description: 根据ID获取会员信息
     * @param id
     * @return com.perenc.mall.platform.entity.model.MemberVO
     * @author: GR
     * @date: 2019/9/17
     */
    MemberVO getMember(Integer id);


    /**
     * @description: 根据ID移除会员信息
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeMemberById(Integer id);


    /**
     * @description: 获取会员信息列表
     * @param currentPage
     * @param pageSize
     * @param name
     * @param phone
     * @param level
     * @param sex
     * @return com.perenc.mall.common.vo.PageVO
     * @author: GR
     * @date: 2019/9/20
     */
    PageVO listMember(int currentPage, int pageSize, String name, String phone, Integer level, Integer sex);

    /**
     * @description: 更新会员信息
     * @param memberDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void updateMember(MemberDTO memberDTO);

}
