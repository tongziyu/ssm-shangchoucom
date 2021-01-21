package com.yixuexi.crowd.mvc.interceptor;

import com.yixuexi.crowd.entity.Admin;
import com.yixuexi.crowd.exception.AccessForbiddenException;
import com.yixuexi.crowd.util.constant.CrowdConstant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @date: 2021/1/12   21:00
 * @author: 易学习
 * 登录拦截
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 1.通过request对象获取session对象
        HttpSession session = httpServletRequest.getSession();
        // 2.尝试从Session域中获取admin
        Admin admin = (Admin)session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        // 3.判断admin对象是否为空
        if (admin == null) {
            // 4.如果没有则抛出异常
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);
        }

        // 5.如果执行到这里说明不为空，放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
