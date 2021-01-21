package com.yixuexi.crowd.util;

import cn.hutool.crypto.digest.DigestUtil;
import com.yixuexi.crowd.util.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * @date: 2021/1/11   17:38
 * @author: 易学习
 * 工具类
 */
public class CrowdUtil {

    /**
     * 判断请求是普通请求还是ajax请求的工具方法
     * @param request 请求对象
     * @return
     *      true: 表示是ajax请求
     *      false: 表示是普通请求
     */
    public static boolean judgeRequestType(HttpServletRequest request){
        // 获取请求消息头
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Requested-With");
        // 判断 并返回
        return (
                (acceptHeader != null && acceptHeader.contains("application/json")) ||
                        (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest")));
    }

    /**
     * md5加密
     * @param source 需要被MD5加密的字符串
     * @return 返回密文
     */
    public static String md5(String source){
        // 1.首先判断是不是无效的字符串
        if (source == null || source.length() == 0){
            // 2.如果是直接抛出异常
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALID);
        }

        // 3.调用HuTool的工具方法进行md5加密
        String encoded = DigestUtil.md5Hex(source).toUpperCase();
        return encoded;
    }
}


