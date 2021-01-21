package com.yixuexi.crowd.exception;

/**
 * @date: 2021/1/13   16:55
 * @author: 易学习
 * 删除自己异常
 */
public class DeleteOneSelfException extends RuntimeException {
    public DeleteOneSelfException() {
        super();
    }

    public DeleteOneSelfException(String message) {
        super(message);
    }
}
