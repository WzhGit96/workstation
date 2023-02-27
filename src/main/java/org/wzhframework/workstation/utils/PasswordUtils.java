package org.wzhframework.workstation.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.util.CastUtils;

/**
 * @author wzh
 * @since 2023/1/9
 */
public final class PasswordUtils {
    private PasswordUtils() {
        super();
    }

    private static final String AES_KEY = "workstation@2023_pwd_aeskey";

    /**
     * 加密
     *
     * @param src src
     * @return encrypt string
     * @throws Exception exception
     */
    public static String encrypt(String src) throws Exception {
        String key = CastUtils.cast(AES_KEY);
        if (StringUtils.isBlank(key)) {
            throw new NullPointerException("encrypt key can not be blank");
        }
        String encryptString = AesUtils.encrypt(src, key);
        return DigestUtils.sha1Hex(encryptString);
    }
}
