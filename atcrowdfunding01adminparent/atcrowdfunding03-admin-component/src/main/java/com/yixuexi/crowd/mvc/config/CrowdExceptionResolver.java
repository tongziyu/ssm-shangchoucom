package com.yixuexi.crowd.mvc.config;

import com.google.gson.Gson;
import com.yixuexi.crowd.exception.*;
import com.yixuexi.crowd.util.CrowdUtil;
import com.yixuexi.crowd.util.ResultEntity;
import com.yixuexi.crowd.util.constant.CrowdConstant;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @date: 2021/1/11   18:37
 * @author: 易学习
 * 基于注解的异常映射
 * @ControllerAdvice: 表示当前这个类是一个异常处理器类【基于注解】
 * @ExceptionHandler: 将一个具体的异常类型和一个方法关联起来
 *
 */

@ControllerAdvice
public class CrowdExceptionResolver {


    /**
     * 角色已存在异常 已存在异常
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(RoleIsExistsException.class)
    public ModelAndView resolverRoleIsExistsException(RoleIsExistsException exception,
                                                         HttpServletRequest request,
                                                         HttpServletResponse response)throws IOException {
        // 用户名已被占用，回到更新页面，告诉用户
        String viewError = "system-error";
        ModelAndView modelAndView = commonResolve(viewError, exception, request, response);
        return modelAndView;
    }
    /**
     * 更新时 loginAcct 已存在异常
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(AdminAcctExistsException.class)
    public ModelAndView resolverAdminAcctExistsException(AdminAcctExistsException exception,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response)throws IOException {
        // 用户名已被占用，回到更新页面，告诉用户
        String viewError = "system-error";
        ModelAndView modelAndView = commonResolve(viewError, exception, request, response);
        return modelAndView;
    }


    /**
     * 保存时 loginAcct 已存在异常
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(AdminExistsException.class)
    public ModelAndView resolverAdminExistsException(AdminExistsException exception,
                                                       HttpServletRequest request,
                                                       HttpServletResponse response)throws IOException {
        // 用户名已被占用，回到新增页面，告诉用户
        String viewError = "system-error";
        ModelAndView modelAndView = commonResolve(viewError, exception, request, response);
        return modelAndView;
    }


    /**
     * 用户自己删除自己异常
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(DeleteOneSelfException.class)
    public ModelAndView resolverDeleteOneSelfException(DeleteOneSelfException exception,
                                                         HttpServletRequest request,
                                                         HttpServletResponse response)throws IOException {
        // 自己删除自己 跳转到system-error
        String viewError = "system-error";
        ModelAndView modelAndView = commonResolve(viewError, exception, request, response);
        return modelAndView;
    }
    /**
     * 恶意登录
     * @param exception
     * @param request
     * @param response
     * @return 恶意登录，回到登录页面
     * @throws IOException
     */
    @ExceptionHandler(AccessForbiddenException.class)
    public ModelAndView resolverAccessForbiddenException(AccessForbiddenException exception,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response)throws IOException {
        // 恶意登录回到登录页面
        String viewError = "admin-login";
        ModelAndView modelAndView = commonResolve(viewError, exception, request, response);
        return modelAndView;
    }

    /**
     * 用户名不存在异常
     * @param exception
     * @param request
     * @param response
     * @return 登录失败，回到登录页面
     * @throws IOException
     */
    @ExceptionHandler(AdminErrorException.class)
    public ModelAndView resolverAdminErrorException(AdminErrorException exception,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response)throws IOException {
        // 回到登录页面
        String viewError = "system-error";
        ModelAndView modelAndView = commonResolve(viewError, exception, request, response);
        return modelAndView;
    }

    /**
     * 用户登录失败异常
     * @param exception
     * @param request
     * @param response
     * @return 登录失败，回到登录页面
     * @throws IOException
     */
    @ExceptionHandler(LoginFailedException.class)
    public ModelAndView resolverLoginFailedException(LoginFailedException exception,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response)throws IOException {
        // 回到登录页面
        String viewError = "admin-login";
        ModelAndView modelAndView = commonResolve(viewError, exception, request, response);
        return modelAndView;
    }


    /**
     * 运行时异常
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView resolverLRuntimeException(RuntimeException exception,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response)throws IOException {
        String viewError = "system-error";
        ModelAndView modelAndView = commonResolve(viewError, exception, request, response);
        return modelAndView;
    }


    /**
     * 被0除异常 统一处理
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView resolverArithmeticException(ArithmeticException exception,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) throws IOException {
        String viewError = "system-error";
        ModelAndView modelAndView = commonResolve(viewError, exception, request, response);
        return modelAndView;
    }


    /**
     * 空指针异常处理
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(NullPointerException.class)
    public ModelAndView resolverNullPointException(NullPointerException exception,
                                                   HttpServletRequest request,
                                                    HttpServletResponse response
                                                    ) throws IOException {
        String viewError = "system-error";
        ModelAndView modelAndView = commonResolve(viewError, exception, request, response);
        return modelAndView;
    }


    /**
     *
     * @param viewName 异常处理完成后要去的页面
     * @param e  实际捕获的异常
     * @param request 当前的请求对象
     * @param response 当前的响应对象
     * @return
     * @throws IOException
     */
    private ModelAndView commonResolve(String viewName,
                                       Exception e,
                                       HttpServletRequest request,
                                       HttpServletResponse response

                                        ) throws IOException {

        // 1.判断请求的类型，如果是普通请求就返回页面，如果是ajax就返回提示错误的json
        boolean judgeRequest = CrowdUtil.judgeRequestType(request);
        // 2.如果是AJAX请求，直接返回null
        if (judgeRequest){
            // 3.创建ResultEntity对象,
            ResultEntity<Object> failed = ResultEntity.failed(e.getMessage());
            // 4.转成JSON  创建gson对象
            Gson gson = new Gson();
            // 5.调用toJson() 方法 转换成Json字符串
            String json = gson.toJson(failed);
            // 6.将json字符串返回给浏览器
            response.getWriter().write(json);
            // 7.上面通过原生的response对象，所以不提供ModelAndView对象了
            return null;
        }
        // 8.如果不是AJax请求，则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        // 9.将异常对象放到model中
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION,e);
        // 10.设置对应的视图名称
        modelAndView.setViewName(viewName);

        // 11. 返回modelAndView对象
        return modelAndView;

    }
}
