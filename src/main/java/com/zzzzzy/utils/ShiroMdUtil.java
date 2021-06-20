package com.zzzzzy.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @ClassName ShiroMdUtil
 * @Description TODO
 * @Author zzzzy
 * @Date 2021/5/9 12:15
 * @Version 1.0
 **/

public class ShiroMdUtil {
    // 添加user的密码加密方式
    public static String SysMd5(String username, String password){
        String hashAlgorithmName = "MD5";
        ByteSource salt = ByteSource.Util.bytes(username);
        int hashIterations = 2;
        String newPassword = new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toHex();
        return newPassword;
    }
}
