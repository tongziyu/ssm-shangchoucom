package com.yixuexi.crowd.exception;

/**
 * @date: 2021/1/13   21:17
 * @author: 易学习
 * 保存或更新时 loginAcct 已存在异常
 */
public class AdminExistsException extends RuntimeException {
    public AdminExistsException() {
        super();
    }

    public AdminExistsException(String message) {
        super(message);
    }
}
