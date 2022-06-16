package com.songjz.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static String getType(Object o) {
        return o.getClass().toString();
    }

    private static final String salt="1a2b3c4d";

    public static String inputPassToFormPass(String inputPass) {
        String str_error = salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(6)+salt.charAt(3);
        String str = String.valueOf(salt.charAt(0))+salt.charAt(2)+inputPass+salt.charAt(6)+salt.charAt(3);
//        System.out.println(salt.charAt(0));
//        System.out.println(getType(salt.charAt(0)));
//        System.out.println(salt.charAt(2));
//        System.out.println(str);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(6)+salt.charAt(3);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass, String salt) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, salt);
        return dbPass;
    }

    // 测试用
    public static void main(String[] args) {
        //        2de3064e2cb006920b6655c58262c6e1
        System.out.println(inputPassToFormPass("123456"));
        System.out.println(formPassToDBPass("2de3064e2cb006920b6655c58262c6e1", "1a2b3c4d"));
        System.out.println(inputPassToDBPass("123456", "1a2b3c4d"));
    }
}
