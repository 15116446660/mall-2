package com.perenc.mall.platform.service;

import com.perenc.mall.platform.entity.dto.SysMenuDTO;
import com.perenc.mall.platform.entity.vo.SysMenuVO;

import java.util.List;

/**
 * @ClassName: ISysMenuService
 * @Description: 系统菜单服务类
 *
 * @Author: GR
 * @Date: 2019/9/21 15:25 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/21     GR     		
 */
public interface ISysMenuService {
    /**
     * @description: 添加系统菜单
     * @param sysMenuDTO
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void saveSysMenu(SysMenuDTO sysMenuDTO);

    /**
     * @description: 获取系统菜单
     * @param id
     * @return com.perenc.mall.platform.entity.vo.SysMenuVO
     * @author: GR
     * @date: 2019/9/17
     */
    SysMenuVO getSysMenu(Integer id);


    /**
     * @description: 根据ID移除系统菜单
     * @param id
     * @return void
     * @author: GR
     * @date: 2019/9/17
     */
    void removeSysMenuById(Integer id);


    /**
     * @description: 获取系统菜单列表
     * @param
     * @return java.util.List<com.perenc.mall.platform.entity.vo.SysMenuVO>
     * @author: GR
     * @date: 2019/9/17
     */
    List<SysMenuVO> listSysMenu();

    /**
     * @description: 更新系统菜单
     * @param sysMenuDTO
     * @return java.util.List<com.perenc.mall.platform.entity.model.SysMenuDO>
     * @author: GR
     * @date: 2019/9/17
     */
    void updateSysMenu(SysMenuDTO sysMenuDTO);
}
