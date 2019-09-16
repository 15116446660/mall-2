package com.perenc.mall.common.util;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName: GenerationCodes
 * @Description: 生成随机数工具类根据ip地址，MAC地址，访问时间以及哈希算法生成唯一不重复的唯一编号以及访问IP地址获取工具类
 *
 * @Author: GR
 * @Date: 2019-7-15 16:52 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-7-15     GR      		
 */
public class GenerationCodes {

    private String sep = "";
    private static final int IP;

    static {
        int ipadd;
        try {
            ipadd = toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipadd = 0;
        }
        IP = ipadd;
    }

    private static short counter = (short) 0;
    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

    public static int toInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
    }

    /**
     * @description: //TODO 本地网络中唯一的id
     * @return int
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:55
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    protected int getIP() {
        return IP;
    }

    /**
     * @description: //TODO 降到毫秒
     * @return short
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:55
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    protected short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    protected int getLoTime() {
        return (int) System.currentTimeMillis();
    }

    /**
     * @description: //TODO 在JVMs上独一无二的机器（除非把这个类加载到相同的第二次-非常不可能)
     * @return int
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:55
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    protected int getJVM() {
        return JVM;
    }

    protected String format(int intval) {
        String formatted = Integer.toHexString(intval);
        StringBuffer buf = new StringBuffer("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    protected String format(short shortval) {
        String formatted = Integer.toHexString(shortval);
        StringBuffer buf = new StringBuffer("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }

    public String generate() {
        return new StringBuilder(36).append(format(getIP())).append(sep)
                .append(format(getJVM())).append(sep)
                .append(format(getHiTime())).append(sep)
                .append(format(getLoTime())).append(sep)
                .append(format(getCount())).toString();
    }

    /**
     * @description: //TODO 此JVM实例在毫秒中是唯一的（除非有在毫秒中创建的SimultMax值实例）
     * @param
     * @return short
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:54
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    protected short getCount() {
        synchronized (GenerationCodes.class) {
            if (counter < 0)
                counter = 0;
            return counter++;
        }
    }

    public Map<String, Object> RandomCode(int flag) {
        Map<String, Object> map = new HashMap<>();
        String data = new SimpleDateFormat("yyyyMMdd-ssSSS").format(new Date());
        String code = "";
        String val = "";
        Random random = new Random();
        for (int i = 0; i < 5; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        if (flag == 1) {
            code = "SYS-" + data + val;
        }
        if (flag == 2) {
            code = "SER-" + data + val;
        }
        if (flag == 3) {
            code = "CAT-" + data + val;
        }
        map.put("code", code);
        return map;
    }
}


