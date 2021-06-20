package com.zzzzzy;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

// 测试md5加密后的密码
public class Md5Test {

    @Test
    public void testMD5(){

        String hashName = "md5";

        String pwd = "123456";

        Object result = new SimpleHash(hashName, pwd, "guest", 2);

        System.out.println(result);


    }

}
