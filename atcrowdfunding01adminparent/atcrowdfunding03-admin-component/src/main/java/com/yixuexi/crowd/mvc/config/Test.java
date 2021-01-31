package com.yixuexi.crowd.mvc.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @date: 2021/1/28   16:08
 * @author: 易学习
 */
public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder c = new BCryptPasswordEncoder();
        String encode = c.encode("123456");
        System.out.println(encode);
    }
}
