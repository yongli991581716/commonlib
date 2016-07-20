
package com.common.lib.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.security.MessageDigest;

public final class MD5Util
{

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
     * 加密
     * 
     * @param source
     * @param rand 6位随机字符串，不能出现特殊字符
     * @param md5 由秘钥vikie通过md5算法生成的32位字符串
     * @return
     */
    public static String encrypt(String source, String rand, String md5) {
        String md5_blend = MD5(rand + md5);
        // 后16位校验数据完整性,会后缀到source后面一起加密
        String checkCode = md5_blend.substring(16, 32);
        int[] encrypt_book = createEncryptBook(md5_blend);

        byte[] md5_sourse = (source + checkCode).getBytes();
        byte[] tmp_str = new byte[md5_sourse.length];
        // 通过异或算法对每一位加密
        for (int i = 0; i < md5_sourse.length; ++i) {
            tmp_str[i] = (byte) (md5_sourse[i] ^ encrypt_book[i % 255]);
            // 为了兼容其它语言，如c
            if (tmp_str[i] == 0) {
                tmp_str[i] = (byte) md5_sourse[i];
            }
        }
        return rand + Base64.encodeToString(tmp_str, Base64.DEFAULT).trim().replace("\n", "");

    }

    /**
     * 解密
     * 
     * @param source
     * @param md5 由秘钥vikie通过md5算法生成的32位字符串
     * @return
     */
    public static String decrypt(String source, String md5) {

        String rand = source.substring(0, 6);
        String md5_blend = MD5(rand + md5);
        String checkCode = md5_blend.substring(16, 32);
        int[] encrypt_book = createEncryptBook(md5_blend);

        source = source.substring(6);
        byte[] decode = Base64.decode(source, Base64.DEFAULT);

        StringBuilder target = new StringBuilder();
        // 通过异或算法对每一位加密
        for (int i = 0; i < decode.length; ++i) {
            byte tmp_str = (byte) (decode[i] ^ encrypt_book[i % 255]);
            // 为了兼容其它语言，如c
            if (tmp_str == 0) {
                tmp_str = decode[i];
            }
            target.append((char) tmp_str);
        }

        String check = target.substring(decode.length - 16);
        if (check.equals(checkCode)) {
            return target.substring(0, decode.length - 16);
        }
        else {
            return "";
        }
    }

    /**
     * 生成密码簿
     * 
     * @param md5Key 由rand和md5拼接后通过md5算法生成的32位字符串
     * @return
     */
    private static int[] createEncryptBook(String md5Blend) {
        int[] encrypt_book = new int[256];
        int i = 0;
        int j = 0;
        byte[] rndkey = md5Blend.getBytes();
        // 初始化密码簿
        for (i = 0; i <= 255; i++) {
            encrypt_book[i] = i;
        }
        // 打乱密码簿
        for (i = j = 0; i <= 255; i++) {
            j = (rndkey[i % 32] + encrypt_book[i] + j) % 255;
            int tmp = encrypt_book[i];
            encrypt_book[i] = encrypt_book[j];
            encrypt_book[j] = tmp;
        }
        return encrypt_book;
    }

    public static byte uniteBytes(byte src0, byte src1)
    {
        byte _b0 = Byte.decode("0x" + new String(new byte[]
        {
                src0
        })).byteValue();
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[]
        {
                src1
        })).byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }

    /**
     * 16进制字符串转化成字节数组
     * 
     * @param src
     * @return
     */
    public static byte[] hexString2Bytes(String src)
    {
        src = src.toUpperCase();
        byte[] ret = new byte[src.length() / 2];
        byte[] tmp = src.getBytes();
        for (int i = 0; i < tmp.length / 2; i++)
        {
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return ret;
    }

    /**
     * 字节数组转化成16进制字符串
     * 
     * @param b
     * @return
     */
    public static String bytes2HexString(byte[] b)
    {
        String ret = "";
        for (int i = 0; i < b.length; i++)
        {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1)
            {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();
        char[] cs = string.toCharArray();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = cs[i];
            if (c >= 19968 && c <= 171941)
            {
                unicode.append("\\u");
                // 转换为unicode
                String s = Integer.toHexString(c);
                int length = s.length();
                while (length < 4) {
                    unicode.append("0");
                    length++;
                }
                unicode.append(s);
            }
            else {
                unicode.append(c);
            }
        }

        return unicode.toString();
    }

    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }

    /**
     * utf-8 转unicode
     * 
     * @param inStr
     * @return String
     * @throws UnsupportedEncodingException
     */
    public static String utf8ToUnicode(String Str) throws UnsupportedEncodingException {
        // 先转换成UTF-8的字符串
        String inStr = new String(Str.getBytes(), "UTF-8");
        char[] myBuffer = inStr.toCharArray();
        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < inStr.length(); i++) {
            UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);
            if (ub == UnicodeBlock.BASIC_LATIN) {
                unicode.append(myBuffer[i]);
            }
            // 当全角＼置换为小写\时，如\(json解析将出错
            // else if (ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            // int j = (int) myBuffer[i] - 65248;
            // unicode.append((char) j);
            // }
            else {
                unicode.append("\\u");
                // 转换为unicode
                short s = (short) myBuffer[i];
                String hexS = Integer.toHexString(s & 0x0000FFFF);
                int length = hexS.length();
                while (length < 4) {
                    unicode.append("0");
                    length++;
                }
                unicode.append(hexS);
            }
        }
        return unicode.toString();
    }
}
