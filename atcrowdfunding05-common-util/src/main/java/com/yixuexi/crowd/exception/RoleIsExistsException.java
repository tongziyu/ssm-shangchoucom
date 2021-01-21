package com.yixuexi.crowd.exception;

/**
 * @date: 2021/1/16   19:37
 * @author: 易学习
 * 角色已存在异常
 */
public class RoleIsExistsException extends RuntimeException {
    public RoleIsExistsException() {
        super();
    }

    public RoleIsExistsException(String message) {
        super(message);
    }
}
