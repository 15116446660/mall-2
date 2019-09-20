package com.perenc.mall.platform.controller;

import com.perenc.mall.common.controller.BaseController;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.exception.ValidResultException;
import com.perenc.mall.common.result.JsonResult;
import com.perenc.mall.common.result.Result;
import com.perenc.mall.platform.entity.dto.MemberGradeDTO;
import com.perenc.mall.platform.entity.model.MemberGradeDO;
import com.perenc.mall.platform.entity.model.MemberGradeDO;
import com.perenc.mall.platform.entity.vo.MemberGradeVO;
import com.perenc.mall.platform.service.IMemberGradeService;
import com.perenc.mall.platform.service.impl.MemberGradeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: MemberGradeController
 * @Description: 会员等级相关
 *
 * @Author: GR
 * @Date: 2019-9-14 17:47 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
@Slf4j
@RestController
@RequestMapping("memberGrade")
public class MemberGradeController {
    @Autowired
    private IMemberGradeService service;


    /**
     * @description: 添加会员等级
     * @param MemberGradeDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addMemberGrade(@RequestBody @Valid MemberGradeDTO MemberGradeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidResultException(bindingResult);
        }
        service.saveMemberGrade(MemberGradeDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 获取会员等级列表，默认升序排列返回
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.MemberGradeVO>
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<MemberGradeVO> listMemberGrades() {
        List<MemberGradeVO> listMemberGradeVO = service.listMemberGrade();
        return listMemberGradeVO;
    }

    /**
     * @description: 根据指定ID获取MemberGrade信息
     * @param id
     * @return com.perenc.mall.platform.entity.vo.MemberGradeVO
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public MemberGradeVO getMemberGrade(@RequestParam Integer id) {
        MemberGradeVO MemberGradeVO = service.getMemberGrade(id);
        if (null == MemberGradeVO) {
            throw new BusinessException("ID为" + id + "的会员等级不存在");
        }
        return MemberGradeVO;
    }

    /**
     * @description: 会员等级更新
     * @param MemberGradeDTO
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateMemberGrade(@RequestBody MemberGradeDTO MemberGradeDTO) {
        service.updateMemberGrade(MemberGradeDTO);
        return JsonResult.buildResultOk();
    }

    /**
     * @description: 会员等级删除
     * @param id
     * @return com.perenc.mall.common.result.Result
     * @author: GR
     * @date: 2019/9/17
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Result removeMemberGrade(@RequestParam Integer id) {
        service.removeMemberGradeById(id);
        return JsonResult.buildResultOk();
    }

}
