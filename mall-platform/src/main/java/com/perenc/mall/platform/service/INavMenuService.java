package com.perenc.mall.platform.service;

import com.perenc.mall.platform.entity.dto.BannerDTO;
import com.perenc.mall.platform.entity.dto.NavMenuDTO;
import com.perenc.mall.platform.entity.model.BannerDO;
import com.perenc.mall.platform.entity.model.NavMenuDO;
import com.perenc.mall.platform.entity.vo.NavMenuVO;

import java.util.List;

/**
 * @ClassName: INavMenuService
 * @Description: 导航服务类
 *
 * @Author: GR
 * @Date: 2019-9-14 14:26 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-14     GR      		
 */
public interface INavMenuService {

    /**
     * @description: 添加导航菜单
     * @param navMenuDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveNavMenu(NavMenuDTO navMenuDTO);

    /**
     * @description: 根据ID获取导航菜单
     * @param id
     * @return com.perenc.mall.platform.entity.model.NavMenuVO
     * @author: GR
     * @date: 2019/9/17
     */
    NavMenuVO getNavMenu(Integer id);


    /**
     * @description: 根据ID移除导航菜单
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeNavMenuById(Integer id);


    /**
     * @description: 获取导航菜单列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.model.NavMenuDO>
     * @author: GR
     * @date: 2019/9/17
     */
    List<NavMenuDO> listNavMenus();

    /**
     * @description: 更新导航菜单
     * @param bannerDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void updateNavMenu(NavMenuDTO bannerDTO);
}
