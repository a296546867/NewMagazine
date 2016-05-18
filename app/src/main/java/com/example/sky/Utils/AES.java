package com.example.sky.Utils;

import android.util.Base64;
import android.util.Log;
import com.example.sky.Net.Configurator;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/18 9:20
 * 修改人：meigong
 * 修改时间：2016/5/18 9:20
 * 修改备注：
 */
public class AES {
    public static final String VIPARA = "1234567890123456";
    public static final String bm = "UTF-8";
    private final static String SEED = Configurator.AES_KEY;

    //加密
    public static String encrypt(String cleartext) throws Exception {
        try {
            SecretKeySpec key = new SecretKeySpec(SEED.getBytes(bm), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedData = cipher.doFinal(cleartext.getBytes(bm), 0, cleartext.getBytes(bm).length);

            return Base64.encodeToString(encryptedData,Base64.DEFAULT);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //解密
    public static String decrypt(String encrypted) throws Exception {
        try {
            byte[] byteMi = Base64.decode(encrypted,Base64.DEFAULT);
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            SecretKeySpec key = new SecretKeySpec(SEED.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedData = cipher.doFinal(byteMi);

            return new String(decryptedData, bm);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }

    }
}
