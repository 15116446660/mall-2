package com.perenc.mall.common.util;

import java.security.MessageDigest;

/**
 * @ClassName: Md5Utils
 * @Description:
 *
 * @Author: GR
 * @Date: 2019-7-15 14:12 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-7-15     GR      		
 */
public class Md5Utils {

    /**
     * @description: //TODO md5字符串加密
     * @param targetStr
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 14:13
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR      		
     */
    public static String md5(String targetStr) {

        //如果待加密的数据为空，就直接返回
        if (targetStr == null || targetStr.equals("")) {
            return "";
        }

        //加密后的结果
        String value = "";
        //md5算法
        MessageDigest md5 = null;
        //缓存加密过程的数据
        StringBuffer buf = new StringBuffer("");

        try {
            //获取MD5加密算法
            md5 = MessageDigest.getInstance("MD5");
            md5.update(targetStr.getBytes("utf-8"));
            byte[] b = md5.digest();

            int temp;
            for (int n = 0; n < b.length; n++) {
                temp = b[n];
                if (temp < 0) {
                    temp += 256;
                }
                if (temp < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(temp));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //把缓存数据转化为字符串且转化为大写
        try {
            value = buf.toString().toUpperCase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value;
    }

    /**
     * @description: //TODO 获取短数据md5
     * @param source
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 14:13
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String getMD5Short(String source) {

        return md5(source).substring(8, 24);

    }

}
