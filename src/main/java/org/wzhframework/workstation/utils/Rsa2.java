package org.wzhframework.workstation.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author wzh
 * @since 2023/2/27
 */
public final class Rsa2 {
    private Rsa2() {

    }

    /**
     * 加密
     *
     * @param str    目标字符串
     * @param pubKey 公钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String str, String pubKey) throws Exception {
        byte[] decoded = Base64.decodeBase64(pubKey);
        RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        // RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
        return Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 解密
     *
     * @param str str
     * @param priKey 私钥
     * @return
     * @throws Exception
     */
    public static String decrypt(String str, String priKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(priKey);
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
        return new String(cipher.doFinal(inputByte));
    }


    /**
     * 生成key
     *
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // 初始化密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024, new SecureRandom());
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String priKey = new String(Base64.encodeBase64(privateKey.getEncoded()));
        String pubKey = new String(Base64.encodeBase64(publicKey.getEncoded()));
        System.out.println("pub key: " + pubKey);
        System.out.println("pri key: " + priKey);
    }
}
