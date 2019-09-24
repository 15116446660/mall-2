package com.perenc.mall.platform.controller;

import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.platform.entity.dto.MemberDTO;
import com.perenc.mall.platform.entity.vo.MemberVO;
import com.perenc.mall.platform.service.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName: MemberController
 * @Description: 会员相关
 *
 * @Author: GR
 * @Date: 2019-9-14 17:34 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@RestController
@RequestMapping("member")
public class MemberController {
    @Autowired
    private IMemberService service;

    /**
     * @description: 添加会员信息
     * @param MemberDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addMember(@RequestBody @Valid MemberDTO MemberDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveMember(MemberDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取会员列表，默认升序排列返回
     * @param currentPage
     * @param pageSize
     * @return java.util.List<com.perenc.mall.platform.entity.model.MemberDO>
     * @author: GR
     * @date: 2019/9/19
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public PageVO listMemberDO(Integer currentPage, Integer pageSize,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String phone,
                               @RequestParam(required = false) Integer level,
                               @RequestParam(required = false) Integer sex) {
        return service.listMember(currentPage, pageSize, name, phone, level, sex);
    }

    /**
     * @description: 根据指定ID获取会员信息
     * @param id
     * @return com.perenc.mall.platform.entity.model.MemberVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public MemberVO getNavMenu(@RequestParam Integer id) {
        MemberVO MemberVO = service.getMember(id);
        if (null == MemberVO) {
            throw new BusinessException("ID为" + id + "的会员不存在");
        }
        return MemberVO;
    }

    /**
     * @description: 会员更新
     * @param MemberDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateMember(@RequestBody MemberDTO MemberDTO) {
        service.updateMember(MemberDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 会员删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removeMember(@RequestParam Integer id) {
        service.removeMemberById(id);
        return JsonResult.buildResultOk();
    }

}
