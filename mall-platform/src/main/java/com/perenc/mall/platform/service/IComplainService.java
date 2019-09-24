package com.perenc.mall.platform.service;

import com.perenc.mall.platform.entity.vo.ComplainVO;

import java.util.List;

/**
 * @className IComplainService
 * @description 投诉相关服务类
 *
 * @author GR
 * @date 2019/9/24 18:50 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/24     GR     		
 */
public interface IComplainService {

    /**
     * @description 分页查询
     * @param currentPage
     * @param pageSize
     * @return java.util.List<com.perenc.mall.platform.entity.vo.ComplainVO>
     * @author GR
     * @date 2019/9/24 19:03
     */
    List<ComplainVO> listComplain(Integer currentPage, Integer pageSize);

    /**
     * @description 根据投诉ID获取投诉详情信息
     * @param complainId
     * @return com.perenc.mall.platform.entity.vo.ComplainVO
     * @author GR
     * @date 2019/9/24 19:03
     */
    ComplainVO getComplainDetails(Integer complainId);
}
