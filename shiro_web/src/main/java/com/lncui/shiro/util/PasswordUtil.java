package com.lncui.shiro.util;

/**
 * 加密解密工具
 */
public class PasswordUtil {

    /**
     * 安全密码(UUID生成)，作为盐值用于用户密码的加密
     */
    public static final String SHIRO_SECURITY_KEY = "929123f8f17944e8b0a531045453e1f1";

    /**
     * AES 加密
     * @param password
     *         未加密的密码
     * @param salt
     *         盐值，默认使用用户名就可
     * @return
     * @throws Exception
     */
    public static String encrypt(String password, String salt) throws Exception {
        return AesUtil.encrypt(Md5Util.MD5(salt + SHIRO_SECURITY_KEY), password);
    }

    /**
     * AES 解密
     * @param encryptPassword
     *         加密后的密码
     * @param salt
     *         盐值，默认使用用户名就可
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptPassword, String salt) throws Exception {
        return AesUtil.decrypt(Md5Util.MD5(salt + SHIRO_SECURITY_KEY), encryptPassword);
    }
}
