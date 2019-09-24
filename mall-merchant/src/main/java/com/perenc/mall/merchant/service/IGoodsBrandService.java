package com.perenc.mall.merchant.service;

import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.GoodsBrandDTO;
import com.perenc.mall.merchant.entity.model.GoodsBrandDO;
import com.perenc.mall.merchant.entity.vo.GoodsBrandVO;

import java.util.List;

/**
 * @ClassName: IGoodsBrandService
 * @Description: 商品品牌服务类
 *
 * @Author: GR
 * @Date: 2019/9/20 20:58 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/20     GR     		
 */
public interface IGoodsBrandService {
    /**
     * @description: 添加商品品牌
     * @param goodsBrandDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveGoodsBrand(GoodsBrandDTO goodsBrandDTO);

    /**
     * @description: 获取商品品牌
     * @param id
     * @return com.perenc.mall.platform.entity.vo.GoodsBrandVO
     * @author: GR
     * @date: 2019/9/17
     */
    GoodsBrandVO getGoodsBrand(Integer id);


    /**
     * @description: 根据ID移除商品品牌
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeGoodsBrandById(Integer id);


    /**
     * @description: 获取商品品牌列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.GoodsBrandVO>
     * @author: GR
     * @date: 2019/9/17
     */
    PageVO<GoodsBrandVO> listGoodsBrands(Integer currentPage, Integer pageSize);

    /**
     * @description: 获取商品品牌列表
     * @param goodsBrandDTO
     * @return java.util.List<com.perenc.mall.platform.entity.model.GoodsBrandDO>
     * @author: GR
     * @date: 2019/9/17
     */
    void updateGoodsBrand(GoodsBrandDTO goodsBrandDTO);
}
