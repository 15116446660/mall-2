package com.perenc.mall.common.converter;

/**
 * @ClassName: Converter
 * @Description:
 *
 * @Author: GR
 * @Date: 2019-9-13 21:20 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-9-13     GR      		
 */
public interface Converter<DO, VO, DTO> {

    /**
     * @description:  dto转do
     * @author: GR
     * @date: 2019-9-13 21:38
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    public interface DtoConvertDo<DTO, DO> {
        DO doConvertDo(DTO dto);
    }

    /**
     * @description:  do转vo
     * @author: GR
     * @date: 2019-9-13 21:38
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    public interface DoConvertVo<DO, VO> {
        VO doConvertVo(DO d);
    }

    /**
     * @description:  dto转vo
     * @author: GR
     * @date: 2019-9-13 21:38
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-9-13       GR
     */
    public interface DtoConvertVo<DTO, VO> {
        VO doConvertVo(DTO dto);
    }
}
