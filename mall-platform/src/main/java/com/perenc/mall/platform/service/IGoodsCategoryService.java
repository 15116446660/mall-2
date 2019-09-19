package com.perenc.mall.platform.service;

import com.perenc.mall.platform.entity.dto.GoodsCategoryDTO;
import com.perenc.mall.platform.entity.model.GoodsCategoryDO;
import com.perenc.mall.platform.entity.vo.GoodsCategoryVO;

import java.util.List;

/**
 * @ClassName: IGoodsCategoryService
 * @Description: 商品分类服务类
 *
 * @Author: GR
 * @Date: 2019/9/19 14:01 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/19     GR     		
 */
public interface IGoodsCategoryService {
    /**
     * @description: 添加商品分类
     * @param goodsCategoryDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveGoodsCategory(GoodsCategoryDTO goodsCategoryDTO);

    /**
     * @description: 获取商品分类
     * @param id
     * @return com.perenc.mall.platform.entity.model.GoodsCategoryVO
     * @author: GR
     * @date: 2019/9/17
     */
    GoodsCategoryVO getGoodsCategory(Integer id);


    /**
     * @description: 根据ID移除商品分类
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeGoodsCategoryById(Integer id);


    /**
     * @description: 获取商品分类列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.model.GoodsCategoryVO>
     * @author: GR
     * @date: 2019/9/17
     */
    List<GoodsCategoryVO> listGoodsCategory();

    /**
     * @description: 更新商品分类
     * @param goodsCategoryDTO
     * @return java.util.List<com.perenc.mall.platform.entity.model.GoodsCategoryDO>
     * @author: GR
     * @date: 2019/9/17
     */
    void updateGoodsCategory(GoodsCategoryDTO goodsCategoryDTO);

}
