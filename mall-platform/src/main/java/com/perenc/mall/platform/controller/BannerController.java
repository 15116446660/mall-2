package com.perenc.mall.platform.controller;

import com.perenc.mall.common.controller.BaseController;
import com.perenc.mall.platform.entity.model.BannerDO;
import com.perenc.mall.platform.entity.vo.BannerVO;
import com.perenc.mall.platform.service.impl.BannerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: BannerController
 * @Description: 轮播图相关
 *
 * @Author: GR
 * @Date: 2019-9-13 21:39 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-13     GR      		
 */
@Slf4j
@RestController
@RequestMapping("banner")
public class BannerController extends BaseController<BannerServiceImpl, BannerDO> {

    @RequestMapping("list")
    public List<BannerVO> listBanners() {
        log.info("执行list方法,开始查询所有可用的轮播图数据");
        List<BannerDO> listBannerDO = service.listBanners();
        List<BannerVO> listBannerVO = new ArrayList<>();
        Iterator<BannerDO> iterator = listBannerDO.iterator();
        if (iterator.hasNext()) {
            BannerVO bannerVO = BannerVO.build();
            BeanUtils.copyProperties(bannerVO, iterator.next());
            listBannerVO.add(bannerVO);
        }
        log.info("执行结果数据：{}", listBannerVO.toString());
        return listBannerVO;
    }

}
