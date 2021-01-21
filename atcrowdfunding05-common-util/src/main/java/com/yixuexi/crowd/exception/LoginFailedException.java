package com.yixuexi.crowd.exception;

/**
 * @date: 2021/1/12   13:14
 * @author: 易学习
 * 自定义异常：登录失败后抛出的异常。
 */
public class LoginFailedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String message) {
        super(message);
    }
}
