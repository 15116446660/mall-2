package com.perenc.mall.common.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: StringHelper
 * @Description: 字符串工具
 *
 * @Author: GR
 * @Date: 2019-7-15 17:06 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-7-15     GR      		
 */
public class StringHelper {

    private static final char EXTENSION_SEPARATOR = '.';

    /**
     *
     * @方法名称:hasChinese
     * @方法说明：是否包含中文字符
     * @返回类型：boolean
     * @参数说明
     * @param str
     * @return
     */
    public static boolean hasChinese(String str) {
        Pattern pat = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher matcher = pat.matcher(str);
        boolean flg = false;
        if (matcher.find()) {
            flg = true;
        }
        return flg;
    }

    /**
     * 剪切文本。如果进行了剪切，则在文本后加上"..."
     *
     * @param s
     *            剪切对象。
     * @param len
     *            编码小于256的作为一个字符，大于256的作为两个字符。
     * @return
     */
    public static String textCut(String s, int len, String append) {
        if (s == null) {
            return null;
        }
        int slen = s.length();
        if (slen <= len) {
            return s;
        }
        // 最大计数（如果全是英文）
        int maxCount = len * 2;
        int count = 0;
        int i = 0;
        for (; count < maxCount && i < slen; i++) {
            if (s.codePointAt(i) < 256) {
                count++;
            } else {
                count += 2;
            }
        }
        if (i < slen) {
            if (count > maxCount) {
                i--;
            }
            if (StringHelper.hasLength(append)) {
                if (s.codePointAt(i - 1) < 256) {
                    i -= 2;
                } else {
                    i--;
                }
                return s.substring(0, i) + append;
            } else {
                return s.substring(0, i);
            }
        } else {
            return s;
        }
    }


    /**
     *
     * vaildNull
     *
     * @描述：检测对象是否为空，如果为空，返回空串
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String vaildNull(Object obj) {
        if (StringHelper.hasLength(String.valueOf(obj))) {
            if (String.valueOf(obj).equalsIgnoreCase("null")) {
                return "";
            } else {
                return String.valueOf(obj);
            }

        } else {
            return "";
        }
    }

    /**
     *
     * generateID
     *
     * @描述：生成主键ID
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String generateID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    /**
     *
     * generateID
     *
     * @描述：生成数字主键ID
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static BigDecimal generateBigDecimalID() {
        return new BigDecimal(UUID.randomUUID().getMostSignificantBits());
    }


    /**
     * 生成主键ID
     *
     * @return
     */
    public static long uuid() {
        return UUID.randomUUID().getMostSignificantBits();
    }

    /**
     *
     * hasLength
     *
     * @描述：判断字符串是否为空，字符串<>NULL and length>0 才返回TRUE
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    /**
     *
     * hasLength
     *
     * @描述：判断字符串是否为空，字符串<>NULL and length>0 才返回TRUE
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static boolean hasLength(String str) {
        return !"null".equalsIgnoreCase(str) && hasLength((CharSequence) str);
    }

    /**
     *
     * startsWithIgnoreCase
     *
     * @描述：判断字符串是否已前缀开头，不区分大小写，如果是，返回True，否则返回false
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        if (str == null || prefix == null) {
            return false;
        }
        if (str.startsWith(prefix)) {
            return true;
        }
        if (str.length() < prefix.length()) {
            return false;
        }
        String lcStr = str.substring(0, prefix.length()).toLowerCase();
        String lcPrefix = prefix.toLowerCase();
        return lcStr.equals(lcPrefix);
    }

    /**
     *
     * endsWithIgnoreCase
     *
     * @描述：判断字符串是否以后缀结尾，如果是，返回true，否则返回false
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static boolean endsWithIgnoreCase(String str, String suffix) {
        if (str == null || suffix == null) {
            return false;
        }
        if (str.endsWith(suffix)) {
            return true;
        }
        if (str.length() < suffix.length()) {
            return false;
        }

        String lcStr = str.substring(str.length() - suffix.length())
                .toLowerCase();
        String lcSuffix = suffix.toLowerCase();
        return lcStr.equals(lcSuffix);
    }

    /**
     *
     * replace
     *
     * @描述：字符串替换函数
     * @param inString 輸入字符串 ,oldPattern 将要被替换的子串，newPattern 替换以后的子串
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String replace(String inString, String oldPattern,
                                 String newPattern) {
        if (!hasLength(inString) || !hasLength(oldPattern)
                || newPattern == null) {
            return inString;
        }
        StringBuffer sbuf = new StringBuffer();
        int pos = 0;
        int index = inString.indexOf(oldPattern);
        int patLen = oldPattern.length();
        while (index >= 0) {
            sbuf.append(inString.substring(pos, index));
            sbuf.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sbuf.append(inString.substring(pos));
        return sbuf.toString();
    }

    /**
     *
     * delete
     *
     * @描述：删除子串
     * @param inString
     *            输入字符串，pattern 将要删除的字符串
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String delete(String inString, String pattern) {
        return replace(inString, pattern, "");
    }

    /**
     *
     * getFileName
     *
     * @描述：从完整路径中获取文件名称
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String getFileName(String path) {
        if (path == null) {
            return null;
        }
        int separatorIndex = path.lastIndexOf("/");
        return (separatorIndex != -1 ? path.substring(separatorIndex + 1)
                : path);
    }

    /**
     *
     * getFileNameExtension
     *
     * @描述：取文件的后缀名
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String getFileNameExtension(String path) {
        if (path == null) {
            return null;
        }
        int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        return (sepIndex != -1 ? path.substring(sepIndex + 1) : null);
    }

    /**
     *
     * stripFileNameExtension
     *
     * @描述：去除文件的后缀名称
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String stripFileNameExtension(String path) {
        if (path == null) {
            return null;
        }
        int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        return (sepIndex != -1 ? path.substring(0, sepIndex) : path);
    }

    /**
     *
     * isEmpty
     *
     * @描述：判断数组是否为空
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static boolean isEmpty(Object[] array) {
        return (array == null || array.length == 0);
    }

    /**
     *
     * addStringToArray
     *
     * @描述：添加字符串到数组中
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String[] addStringToArray(String[] array, String str) {
        if (isEmpty(array)) {
            return new String[]{str};
        }
        String[] newArr = new String[array.length + 1];
        System.arraycopy(array, 0, newArr, 0, array.length);
        newArr[array.length] = str;
        return newArr;
    }

    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    /**
     * 判断字符串字符长度，一个中文和韩文算两个字节
     *
     * @param s
     * @return
     */
    public static int charLenght(String s) {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    /**
     *
     * concatenateStringArrays
     *
     * @描述：合并数组，但不排除两个数组中，重复的部分
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String[] concatenateStringArrays(String[] array1,
                                                   String[] array2) {
        if (isEmpty(array1)) {
            return array2;
        }
        if (isEmpty(array2)) {
            return array1;
        }
        String[] newArr = new String[array1.length + array2.length];
        System.arraycopy(array1, 0, newArr, 0, array1.length);
        System.arraycopy(array2, 0, newArr, array1.length, array2.length);
        return newArr;
    }

    /**
     *
     * mergeStringArrays
     *
     * @描述：合并数组，但是要除去两个数组相同部分
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String[] mergeStringArrays(String[] array1, String[] array2) {
        if (isEmpty(array1)) {
            return array2;
        }
        if (isEmpty(array2)) {
            return array1;
        }
        List<String> result = new ArrayList<String>();
        result.addAll(Arrays.asList(array1));
        for (int i = 0; i < array2.length; i++) {
            String str = array2[i];
            if (!result.contains(str)) {
                result.add(str);
            }
        }
        return toStringArray(result);
    }

    /**
     *
     * sortStringArray
     *
     * @描述：数组排序
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String[] sortStringArray(String[] array) {
        if (isEmpty(array)) {
            return new String[0];
        }
        Arrays.sort(array);
        return array;
    }

    /**
     *
     * toStringArray
     *
     * @描述：将序列转换成数组
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String[] toStringArray(Collection collection) {
        if (collection == null) {
            return null;
        }
        return (String[]) collection.toArray(new String[collection.size()]);
    }

    /**
     *
     * toStringArray
     *
     * @描述：将枚举类型转换成数组
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String[] toStringArray(Enumeration enumeration) {
        if (enumeration == null) {
            return null;
        }
        List list = Collections.list(enumeration);
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     *
     * trimArrayElements
     *
     * @描述：去除字符串数组中的空格
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String[] trimArrayElements(String[] array) {
        if (isEmpty(array)) {
            return new String[0];
        }
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            String element = array[i];
            result[i] = (element != null ? element.trim() : null);
        }
        return result;
    }

    /**
     *
     * removeDuplicateStrings
     *
     * @描述：去除数组中重复的字符串
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String[] removeDuplicateStrings(String[] array) {
        if (isEmpty(array)) {
            return array;
        }
        Set<String> set = new TreeSet<String>();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
        return toStringArray(set);
    }

    /**
     *
     * split
     *
     * @描述：根据分隔符来分解字符串成字符串数组
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String[] split(String toSplit, String delimiter) {
        if (!hasLength(toSplit) || !hasLength(delimiter)) {
            return null;
        }
        int offset = toSplit.indexOf(delimiter);
        if (offset < 0) {
            return null;
        }
        String beforeDelimiter = toSplit.substring(0, offset);
        String afterDelimiter = toSplit.substring(offset + delimiter.length());
        return new String[]{beforeDelimiter, afterDelimiter};
    }

    /**
     *
     * getStringToken
     *
     * @描述：分解字符串到List列表
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static List<String> getStringToken(String source, String substr) {
        List<String> substrList = new ArrayList<String>();
        if (source.equals("")) {
            return substrList;
        } else {
            int preIndex = -1;
            int curIndex = source.indexOf(substr);
            int lastIndex = source.lastIndexOf(substr);
            while (curIndex >= 0 || (preIndex == lastIndex)) {
                String str;
                if (preIndex == lastIndex) {
                    str = source.substring(preIndex + 1);
                } else {
                    str = source.substring(preIndex + 1, curIndex);
                }

                substrList.add(str);

                if (curIndex == -1)
                    break;
                preIndex = curIndex;
                curIndex = source.indexOf(substr, curIndex + 1);
            }

            return substrList;
        }
    }

    /**
     *
     * upperFirst
     *
     * @描述：将属性的第一个字母大写
     * @param str
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String upperFirst(String str) {

        if (StringHelper.hasLength(str)) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } else {
            return "";
        }
    }

    /**
     *
     * lowerFirst
     *
     * @描述：第一个字母转小写
     * @param str
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String lowerFirst(String str) {
        if (StringHelper.hasLength(str)) {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        } else {
            return "";
        }
    }

    /**
     *
     * substringAfterLast
     *
     * @描述：
     * @param str
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String substringAfterLast(String str, String split) {
        int index = str.lastIndexOf(split);
        return str.substring(index + 1);
    }

    /**
     *
     * left
     *
     * @描述：从左边取字符串
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public String left(String str, int len) {
        if (StringHelper.hasLength(str)) {
            if (len > str.length()) {
                return str;
            } else {
                return str.substring(0, len);
            }
        } else {
            return "";
        }
    }

    /**
     *
     * right
     *
     * @描述：从字符串右边开始取
     * @param str
     * @return String DOM对象
     * @Exception 异常对象
     */
    public String right(String str, int len) {
        if (StringHelper.hasLength(str)) {
            if (len > str.length()) {
                return str;
            } else {
                return str.substring(str.length() - len, str.length());
            }
        } else {
            return "";
        }
    }

    /**
     *
     * reverse
     *
     * @描述：颠倒字符串
     * @param str
     * @return String DOM对象
     * @Exception 异常对象
     */
    public String reverse(String str) {
        if (StringHelper.hasLength(str)) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(str);
            return buffer.reverse().toString();
        } else {
            return "";
        }
    }

    /**
     *
     * substr
     *
     * @描述：取字符串的子串
     * @param startIndex
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public String substr(String str, int startIndex) {
        if (StringHelper.hasLength(str)) {
            return str.substring(startIndex);
        } else {
            return "";
        }
    }

    /**
     *
     * substr
     *
     * @描述：取字符串的子串，
     *
     * @param startIndex
     *            起始坐标，len 字符串长度
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public String substr(String str, int startIndex, int len) {
        if (StringHelper.hasLength(str)) {
            if (startIndex + len > str.length()) {
                return str.substring(startIndex);
            } else {
                return str.substring(startIndex, startIndex + len);
            }
        } else {
            return "";
        }
    }

    /**
     *
     * delete
     *
     * @描述：从字符串中删除子串，
     * @param startIndex
     *            起始坐标 ,len 字符串长度
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public String delete(String str, int startIndex, int len) {
        if (StringHelper.hasLength(str)) {
            return StringHelper.delete(str, this.substr(str, startIndex, len));
        } else {
            return "";
        }
    }

    /**
     *
     * length
     *
     * @描述：获取字符串的长度
     * @param str
     * @return String DOM对象
     * @Exception 异常对象
     */
    public int length(String str) {
        if (StringHelper.hasLength(str)) {
            return str.length();
        } else {
            return 0;
        }
    }

    /**
     *
     * ltrim
     *
     * @描述：去除字符串左边的空格
     * @param str
     * @return String DOM对象
     * @Exception 异常对象
     */
    public String ltrim(String str) {
        if (!StringHelper.hasLength(str)) {
            return "";
        } else {
            return str.replaceAll("\\s*", "");
        }
    }

    /**
     *
     * rtrim
     *
     * @描述：去除字符串右边的空格
     * @param str
     * @return String DOM对象
     * @Exception 异常对象
     */
    public String rtrim(String str) {
        if (!StringHelper.hasLength(str)) {
            return "";
        } else {
            return str.replaceAll("\\s*$", "");
        }
    }

    /**
     *
     * fillChar
     *
     * @描述：填充字符串
     * @param str
     *            原字符串
     * @param fill
     *            将要填充的字符串
     * @param digit 填充后的长度
     * @param startOrend
     *            填充的起始位置
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     */
    public static String fillChar(String str, String fill, int digit,
                                  int startOrend) throws Exception {
        if (!(str.length() > digit)) {
            int slength = str.length();
            int i = digit - slength;
            StringBuffer fillStr = new StringBuffer();
            for (int x = 0; x < i; x++)
                fillStr.append(fill);
            if (startOrend >= 0)
                return fillStr + str;
            else
                return str + fillStr;
        } else {
            return "";
        }
    }

    /**
     *
     * @方法名称:getIpAddr
     * @方法说明：获取IP地址
     * @返回类型：String
     * @参数说明
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getPropertyName(String columnName) {
        if (StringHelper.hasLength(columnName)) {
            columnName = columnName.toLowerCase();
            columnName = StringHelper.replace(columnName, "_", "");
            columnName = StringHelper.replace(columnName, "-", "");
        } else {
            columnName = "";
        }
        return columnName;
    }

    /**
     * 判断是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 是否是浮点数
     *
     * @param str
     * @return
     */
    public static boolean isDouble(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     *
     *
     * @param unitnum
     * @return
     */
    public static int getLevel(String unitnum) {
        if ("520000000000".equalsIgnoreCase(unitnum)) {
            return 1;
        } else if (!unitnum.startsWith("52")) {
            return 4;
        } else if (!"00".equalsIgnoreCase(unitnum.substring(4, 6))
                && (unitnum.startsWith("52"))) {
            return 3;
        } else if (("00".equalsIgnoreCase(unitnum.substring(4, 6)))
                && (!"00".equalsIgnoreCase(unitnum.substring(2, 4)))
                && (unitnum.startsWith("52"))) {
            return 2;
        } else {
            return 4;
        }
    }

    /**
     * 判断是否能转成数字
     *
     * @param str
     * @return
     */
    public static boolean canNumber(String str) {
        try {
            new BigDecimal(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取表名称
     *
     * @param regionCode
     * @return
     */
    public static String getTablename(String regionCode) {
        if (StringHelper.hasLength(regionCode)) {
            String areanum = regionCode.substring(0, 4);
            if ("5200".equalsIgnoreCase(areanum)) {
                areanum = "info";
            }
            return areanum;
        } else {
            return "info";
        }
    }

    /**
     * 获取订单编号，订单类型+两位年+两位月+两位日+随机流水号
     *
     * @param orderType
     * @param seqNum
     * @return
     */
    public static String getOrderNum(String orderType, int seqNum) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyMMdd");
        String rtn = sdf.format(new Date());
        return orderType + rtn + String.valueOf(seqNum);
    }

    /**
     * 是否手机登陆
     *
     * @param request
     * @return
     */
    public static boolean isMoblie(HttpServletRequest request) {
        boolean isMoblie = false;
        String[] mobileAgents = {"iphone", "android", "phone", "mobile",
                "wap", "netfront", "java", "opera mobi", "opera mini", "ucweb",
                "windows ce", "symbian", "series", "webos", "sony",
                "blackberry", "dopod", "nokia", "samsung", "palmsource", "xda",
                "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin",
                "huawei", "novarra", "coolpad", "webos", "techfaith",
                "palmsource", "alcatel", "amoi", "ktouch", "nexian",
                "ericsson", "philips", "sagem", "wellcom", "bunjalloo", "maui",
                "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
                "pantech", "gionee", "portalmmm", "jig browser", "hiptop",
                "benq", "haier", "^lct", "320x320", "240x320", "176x220",
                "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq",
                "bird", "blac", "blaz", "brew", "cell", "cldc", "cmd-", "dang",
                "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs", "kddi",
                "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo",
                "midp", "mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-",
                "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play",
                "port", "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-",
                "send", "seri", "sgh-", "shar", "sie-", "siem", "smal", "smar",
                "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-",
                "upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi", "wapp",
                "wapr", "webc", "winw", "winw", "xda", "xda-",
                "Googlebot-Mobile"};
        if (request.getHeader("User-Agent") != null) {
            for (String mobileAgent : mobileAgents) {
                if (request.getHeader("User-Agent").toLowerCase()
                        .indexOf(mobileAgent) >= 0) {
                    isMoblie = true;
                    break;
                }
            }
        }
        return isMoblie;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}
