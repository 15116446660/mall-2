package com.perenc.mall.platform.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: BannerVO
 * @Description: 轮播图VO
 *
 * @Author: GR
 * @Date: 2019/9/17 9:30 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/17     GR     		
 */
@Data
@NoArgsConstructor(staticName = "build")
public class BannerVO {
    private Integer id;
    /**
     * @description: 轮播图文件url
     * @author: GR
     * @date: 2019/9/17 11:52
     */
    private String file;
    /**
     * @description: 轮播图标题
     * @author: GR
     * @date: 2019/9/17 11:52
     */
    private String title;
    private String skipType;
    private String skipContent;
    private Integer sort;
    private Integer desc;
    private Integer status;
}
