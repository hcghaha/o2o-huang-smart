package com.huang.smart.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * AES对称加密工具类
 * 加密过程：AES加密 后 base64
 * 解密过程：base64 后 AES解密
 * 生成数字签名：结合base64实现md5加密
 * User: huangchaoguang
 * Date: 2016-8-22
 * Time: 11:16:12
 * Version 1.0
 */
public class AESUtil {

    /**
     * 将byte[]转为各种进制的字符串
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix){
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * base 64 encode
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes){
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * base 64 decode
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception {
        return base64Code == null ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }

    /**
     * 获取byte[]的md5值
     * @param bytes byte[]
     * @return md5
     * @throws Exception
     */
    public static byte[] md5(byte[] bytes) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes);

        return md.digest();
    }

    /**
     * 获取字符串md5值
     * @param msg
     * @return md5
     * @throws Exception
     */
    public static byte[] md5(String msg) throws Exception {
        return msg == null ? null : md5(msg.getBytes());
    }

    /**
     * 结合base64实现md5加密
     * @param msg 待加密字符串
     * @return 获取md5后转为base64
     * @throws Exception
     */
    public static String md5Encrypt(String msg) throws Exception {
        return msg == null ? null : base64Encode(md5(msg));
    }

    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(encryptKey.getBytes()));

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }

    /**
     * AES加密为base 64 code
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(decryptKey.getBytes()));

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }

    /**
     * 将base 64 code AES解密
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return encryptStr == null ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    public static void main(String[] args) throws Exception {
        String commonKey="jddj1471831644820"; //AES对称加密的key
        System.out.println("加密密钥和解密密钥：" + commonKey);

        String phone = "13112345678";
        String code = "fda66caccc914236a4368ae0e85e941a";
        String fromSource = "2";
        String sign = AESUtil.md5Encrypt(phone + commonKey);
        String param = phone + "&" + code + "&" + fromSource + "&" + sign;

        System.out.println("加密前：" + param);


        String encrypt = AESUtil.aesEncrypt(param, commonKey);
        System.out.println("加密后：" + encrypt);

        String decrypt = AESUtil.aesDecrypt(encrypt, commonKey);
        System.out.println("解密后：" + decrypt);

        //2. 解析参数
        phone = param.split("&")[0].trim();
        code = param.split("&")[1].trim();
        fromSource = param.split("&")[2].trim();
        sign = param.split("&")[3].trim();
        //3.生成本地数字签名
        String oSign = AESUtil.md5Encrypt(phone + commonKey);
        //4. 验证签名是否正确
        if (!oSign.equals(sign)) {
            System.out.println("对第三方数字签名校验失败");
        }
        //4. 验证fromSource是否正确
        if (!"2".equals(fromSource)){
            System.out.println("第三方的fromSource值非2");
        }
        System.out.println("phone= " +phone +", code= " +code +", fromSource= " +fromSource);
        System.out.println("成功");
    }
}