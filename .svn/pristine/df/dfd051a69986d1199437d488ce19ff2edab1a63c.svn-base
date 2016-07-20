
package com.common.lib.utils;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    /**
     * 手机号或座机号验证
     * 
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isTel(String tel) {
        boolean isTel = isMobile(tel);
        if (!isTel) {
            isTel = isPhone(tel);
        }
        return isTel;
    }

    /**
     * 手机号验证
     * 
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String mobile) {
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    /**
     * 座机号码验证
     * 
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPhone(String tel) {
        Matcher m = null;
        boolean b = false;
        Pattern p1 = Pattern.compile("^[0][1-9]{2,3}([-]?){0,1}[0-9]{7,8}$"); // 验证带区号的
        Pattern p2 = Pattern.compile("^[1-9]{1}[0-9]{6,7}$"); // 验证没有区号的
        if (tel.length() > 9) {
            m = p1.matcher(tel);
            b = m.matches();
        } else {
            m = p2.matcher(tel);
            b = m.matches();
        }
        return b;
    }

    /**
     * 提取验证短信里的验证码，查找第一个长度>=4的数字串
     * 
     * @param message
     * @return
     */
    public static String parseValidateCode(String message) {
        String code = "";
        StringBuilder sb = new StringBuilder();
        if (message.length() >= 6) {
            boolean isStart = false;
            char[] cs = message.toCharArray();
            for (char c : cs) {
                if (c >= '0' && c <= '9') {
                    if (!isStart) {
                        isStart = true;
                        sb.delete(0, sb.length());
                    }
                    sb.append(c);
                } else {
                    if (isStart) {
                        isStart = false;
                        if (sb.length() >= 4) {
                            return sb.toString();
                        }
                    }
                }
            }
        }
        return code;
    }

    /**
     * 将电话号码中间几位以****显示，如137****8257
     * 
     * @param mobile
     * @return
     */
    public static String hideSomeMobile(String mobile) {
        if (TextUtils.isEmpty(mobile) || mobile.length() != 11) {
            return mobile;
        }
        char[] chars = mobile.toCharArray();
        for (int i = 3; i < 7; i++) {
            chars[i] = '*';
        }
        return new String(chars);
    }

    /**
     * MD5加密
     * 
     * @param s
     * @return
     */
    public final static String MD5(String s) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断time对应的日期是否是今日
     * 
     * @param context
     * @param time
     * @return
     */
    public static boolean isCurrentDay(Context context, String time) {
        String currentTime = DateFormat
                .getDateFormat(context).format(new Date());

        boolean isCurrentDay = false;
        if (currentTime.equals(time))
            isCurrentDay = true;
        return isCurrentDay;
    }

    /**
     * from[i] ^= temp - i; temp为第一个字节，只要第一个字节不同，相同的明文会加密成不同的密文 i为变量，不同位置的相同明文会加密成不同的密文
     * 
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                byte[] from = str.getBytes("ISO-8859-1");
                int length = from.length;
                byte temp = from[0];
                from[0] = (byte) ~from[0];
                for (int i = 1; i < length; i++) {
                    from[i] ^= temp - i;
                }
                str = code(new String(from, "ISO-8859-1"));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * from[i] ^= temp - i; temp为第一个字节，只要第一个字节不同，相同的明文会加密成不同的密文 i为变量，不同位置的相同明文会加密成不同的密文
     * 为了确保加密和解密使用相同的temp，解密时需要先解密里第一个字节，然后在将第一个字节赋值给temp
     * 
     * @param str
     * @return
     */
    public static String decrypt(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                byte[] from = decode(str).getBytes("ISO-8859-1");
                int length = from.length;
                from[0] = (byte) ~from[0];
                byte temp = from[0];
                for (int i = 1; i < length; i++) {
                    from[i] ^= temp - i;
                }
                str = new String(from, "ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * 将str分高4位和低4位分别编码成 [PQRSTUVWXYZ[\]^_]
     * 
     * @param str
     * @return
     */
    public static String code(String str) {
        byte[] to = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                byte[] from = str.getBytes("ISO-8859-1");
                int length = from.length;
                to = new byte[length * 2];
                for (int i = 0; i < length; i++) {
                    to[i * 2] = (byte) (from[i] & 0xF | 0x50);
                    to[i * 2 + 1] = (byte) ((from[i] & 0xF0) >> 4 | 0x50);
                }
                str = new String(to, "ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * 是前一个方法code的逆向行为
     * 
     * @param str
     * @return
     */
    public static String decode(String str) {
        byte[] to = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                byte[] from = str.getBytes("ISO-8859-1");
                int length = from.length / 2;
                to = new byte[length];
                for (int i = 0; i < length; i++) {
                    int low = from[i * 2] & 0xF;
                    int high = (from[i * 2 + 1] & 0xF) << 4;
                    to[i] = (byte) (high | low);
                }
                str = new String(to, "ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     * 
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static String formatDouble2String(double value) {
        return new DecimalFormat("0.00").format(value);
    }
}
