package com.yixuexi.crowd.exception;

/**
 * @date: 2021/1/12   16:26
 * @author: 易学习
 * 用户登录账号不存再或密码错误异常类
 */
public class AdminErrorException extends RuntimeException{
    private static final long serialVersionUID = 2L;
    public AdminErrorException() {
        super();
    }

    public AdminErrorException(String message) {
        super(message);
    }
}
