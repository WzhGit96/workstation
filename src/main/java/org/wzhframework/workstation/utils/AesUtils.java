package org.wzhframework.workstation.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * aes加解密工具类
 *
 * @author wzh
 * @since 2023/1/9
 */
public class AesUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(AesUtils.class);
    private static final String INSTANCE = "AES/ECB/PKCS5Padding";
    private static final String ALGORITHM = "AES";

    /**
     * 加密
     *
     * @param src source string
     * @param key security key
     * @return encrypt string
     * @throws Exception exception
     */
    public static String encrypt(String src, String key) throws Exception {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(src)) {
            throw new Exception("key or src can not be blank");
        }
        byte[] raw = key.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
        //"算法/模式/补码方式"
        Cipher cipher = Cipher.getInstance(INSTANCE);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(src.getBytes(StandardCharsets.UTF_8));
        //此处使用BASE64做转码功能，同时能起到2次加密的作用。
        return new BASE64Encoder().encode(encrypted);
    }

    /**
     * 解密
     *
     * @param src source string
     * @param key security key
     * @return decrypt string
     */
    public static String decrypt(String src, String key) {
        try {
            // 判断Key和src是否为空
            if (StringUtils.isBlank(key) || StringUtils.isBlank(src)) {
                throw new Exception("key or src can not be blank");
            }
            byte[] raw = key.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
            Cipher cipher = Cipher.getInstance(INSTANCE);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            //先用base64解密
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(src);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                return new String(original,StandardCharsets.UTF_8);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                return null;
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
    }
}
