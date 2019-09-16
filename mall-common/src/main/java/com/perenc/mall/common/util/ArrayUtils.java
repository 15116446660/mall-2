package com.perenc.mall.common.util;

/**
 * @ClassName: ArrayUtils
 * @Description:
 *
 * @Author: GR
 * @Date: 2019-7-12 17:15 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-7-12     GR      		
 */
public class ArrayUtils {
    /**
     * @description: //TODO 合并数组
     * @param array1
     * @param array2
     * @return byte[]
     * @throws
     * @author: GR
     * @date: 2019-7-12 17:16
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-12       GR
     */
    public static byte[] addAll(byte[] array1, byte[] array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        byte[] joinedArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * @description: //TODO clone array
     * @param array
     * @return byte[]
     * @throws
     * @author: GR
     * @date: 2019-7-12 17:16
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-12       GR      		
     */
    public static byte[] clone(byte[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }
}
