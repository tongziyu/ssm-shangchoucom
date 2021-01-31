package com.yixuexi.crowd.mvc.config;

import com.yixuexi.crowd.util.constant.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @date: 2021/1/25   22:39
 * @author: 易学习
 * @Configuration: 表示这是一个配置类
 * @EnableWebSecurity: 表示启用web安全
 * @EnableGolbalMethodSecurity： 表示启用全局方法权限管理功能
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 多态，装配一下自己实现的CrowdUserDetailsService类
     */
    @Autowired
    private UserDetailsService crowdUserDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    /**
     * 将盐值加密放到 容器中
     * @return
     * 在这里声明无法在Service中获取到该对象，把该对象声明到spring的容器中
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用基于数据库认证
        auth.userDetailsService(crowdUserDetailsService)
                // 使用盐值加密 判断密码
                .passwordEncoder(bCryptPasswordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()// 放行主页登录
                .antMatchers("/admin/to/login/page.html")
                // 无条件访问
                .permitAll()
                // 放行index.jsp页面
                .antMatchers("/index.jsp")
                .permitAll()
                // 放行静态资源
                .antMatchers("/bootstrap/**")
                .permitAll()
                .antMatchers("/crowd/**")
                .permitAll()
                .antMatchers("/css/**")
                .permitAll()
                .antMatchers("/fonts/**")
                .permitAll()
                .antMatchers("/img/**")
                .permitAll()
                .antMatchers("/jquery/**")
                .permitAll()
                .antMatchers("/layer/**")
                .permitAll()
                .antMatchers("/script/**")
                .permitAll()
                .antMatchers("/ztree/**")
                .permitAll()
                // 针对分页显示admin数据 设定访问控制
                .antMatchers("/admin/get/page.html")
                // 要求需要具备经理的角色或者 user:get 权限
                .access("hasRole('经理') or hasAuthority('user:get')")

                // 其余请求
                .anyRequest()
                // 需要认证后才能访问
                .authenticated()
                .and()
                // 自定义403错误页面
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse,
                                       AccessDeniedException e)
                            throws IOException, ServletException {
                        // 给出提示消息
                        httpServletRequest.
                                setAttribute(CrowdConstant.ATTR_NAME_EXCEPTION,
                                        new Exception(CrowdConstant.MESSAGE_ACCESS_ERROR));
                        // 转发到 system-error.jsp页面
                        httpServletRequest
                                .getRequestDispatcher("/WEB-INF/system-error.jsp")
                                .forward(httpServletRequest,httpServletResponse);
                    }
                })
                .and()
                // 禁用csrf 跨域请求伪造
                .csrf()
                .disable()
                // 开启表单登录功能
                .formLogin()
                // 指定登录页面
                .loginPage("/admin/to/login/page.html")
                // 指定处理请求
                .loginProcessingUrl("/security/do/login.html")
                // 用户密码请求参数映射
                .usernameParameter("loginAcct")
                .passwordParameter("userPswd")
                // 登录成功去哪里
                .defaultSuccessUrl("/admin/to/main/page.html")
                // 退出
                .and()
                // 开启退出功能
                .logout()
                // 退出的地址
                .logoutUrl("/security/do/logout.html")
                // 退出成功后的地址
                .logoutSuccessUrl("/admin/to/login/page.html");
    }
}
