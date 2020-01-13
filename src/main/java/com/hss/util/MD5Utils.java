package com.hss.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
 
/**
 * md5加密工具类
 * @author Lenovo
 *
 */
public class MD5Utils {
	/**
	 * 对字符串进行加密
	 * @param text
	 * @return
	 */
	public static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
 
        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
 
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
 
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
 
    }
 
    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr) {
 
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
 
    }
 
    public static String md5Decode(String str) {
        return convertMD5(convertMD5(str));
    }
 
    // 测试主函数
    public static void main(String args[]) throws UnsupportedEncodingException {
        String s = new String("哈哈呵呵呵哈哈嘿嘿嘿哈111122356");
        System.out.println(md5Decode("a6aeb3ffa55fc7d664406af9c3bd0f1b"));
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + string2MD5(s));
		System.out.println("MD5解密:"	+md5Decode(string2MD5(s)));
		System.out.println("原始：" + s);
        System.out.println("加密的：" + convertMD5(s));
        System.out.println("解密的：" + convertMD5(convertMD5(new String(s.getBytes(),"UTF-8"))));
        System.out.println("-------------ok-------------");
        String passWord = "狗狗狗admin";
        System.out.println("MD5后：" + string2MD5(passWord));
		System.out.println("MD5解密:"	+md5Decode(string2MD5(passWord)));
        System.out.println("加密的：" + convertMD5(passWord));
        System.out.println("解密的：" + convertMD5(convertMD5(new String(passWord.getBytes(),"UTF-8"))));
        System.out.println(convertMD5("犣犣犣"));
    }
 
}
