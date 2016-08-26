package com.jd.o2o.smart.common.utils;

/**
 * Created with IntelliJ IDEA.
 * User: zhouhuawei
 * Date: 14-8-12
 * Time: 上午9:30
 * To change this template use File | Settings | File Templates.
 */

import java.security.MessageDigest;

public class MD5Util {
    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5(String origin) {
        return MD5(origin, null);
    }

    public static String MD5(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString.toUpperCase();
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static void main(String[] s) {
        String st = "{\"orders\"：[{\"orderId\":\"123456\",\"orderType\":\"37\",\"body\":\" 手机充值  30元\"}],\"pin\":\"guohaokai\",\"timestamp\":123456789,\"clientInfo\":{\"client\":\"iphone\",\"osVersion\":\"\",\"clientVersion\":\"\",\"screen\":\"200x800\",\"deviceId\":\"123123213123123213\"},\"notify_url\":\"http://ebook.gw.m.jd.com/callback.action\",\"back_url\":\" http://ebook.gw.m.jd.com/index.action\",\"appid\":\"standroid\",\"ip\":\"192.168.1.1\",\"sign\":\"FFREWREWRWERWEWERWEREW\"}";
        String st2 = "{\"orders\"：[{\"orderId\":\"1234567\",\"orderType\":\"37\",\"body\":\" 手机充值  30元\"}],\"pin\":\"guohaokai\",\"timestamp\":123456789,\"clientInfo\":{\"client\":\"iphone\",\"osVersion\":\"\",\"clientVersion\":\"\",\"screen\":\"200x800\",\"deviceId\":\"123123213123123213\"},\"notify_url\":\"http://ebook.gw.m.jd.com/callback.action\",\"back_url\":\" http://ebook.gw.m.jd.com/index.action\",\"appid\":\"standroid\",\"ip\":\"192.168.1.1\",\"sign\":\"FFREWREWRWERWEWERWEREW\"}";
        System.out.println(MD5(st, "GBK"));
        System.out.println(MD5(st2, "GBK"));
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

}
