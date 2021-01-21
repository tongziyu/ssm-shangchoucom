package com.yixuexi.crowd.exception;

/**
 * @date: 2021/1/13   22:51
 * @author: 易学习
 * 用户修改账号时 账号已存在 异常
 */
public class AdminAcctExistsException extends RuntimeException{
    public AdminAcctExistsException() {
        super();
    }

    public AdminAcctExistsException(String message) {
        super(message);
    }
}
