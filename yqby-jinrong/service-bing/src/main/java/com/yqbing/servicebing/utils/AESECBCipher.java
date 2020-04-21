package com.yqbing.servicebing.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.owtelse.codec.Base64;



/**
 * AES加密工具(C#与JAVA适配版)
 * @author zhengqiao
 *
 */
public class AESECBCipher {

	/**
	 * 加密
	 * @param sSrc 待加密内容
	 * @param sKey 秘钥
	 * @return
	 * @throws Exception
	 */
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
 
        return new Base64().encode(encrypted).toString();
//        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }
 
    /**
     * 解密 
     * @param sSrc 带解密内容
     * @param sKey 秘钥
     * @return
     * @throws Exception
     */
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
 
    public static void main(String[] args) throws Exception {
		String key = "techfitsecretkey";
//		String content = "{'gym_id':1,'card_id':'AEC3QW12','card_type':0,'cabinet_id':1,'box_id':1}";
//		System.out.println("加密前：" + content);
//		String encResult = Encrypt(content, key);
		String encResult = "Bt2njtmzEC8G4TxywjPAYZ8cFJZksTFcxpgH8L7F/AeFvshtNNWblDIxJ4r1pcWOXe4eKNxbpXwI6LdLGsXQzmMq8sErw38UsyPPSjW/USM=";
		System.out.println("加密后：" + encResult);
		String denResult = Decrypt(encResult, key);
		System.out.println("解密后：" + denResult);
	}
}
