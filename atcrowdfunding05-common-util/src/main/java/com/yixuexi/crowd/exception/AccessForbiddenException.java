package com.yixuexi.crowd.exception;

/**
 * @date: 2021/1/12   21:13
 * @author: 易学习
 * 资源禁止访问异常，表示用户没有登录就访问受保护资源时抛出的异常
 */
public class AccessForbiddenException extends RuntimeException {
    public AccessForbiddenException() {
        super();
    }

    public AccessForbiddenException(String message) {
        super(message);
    }
}
