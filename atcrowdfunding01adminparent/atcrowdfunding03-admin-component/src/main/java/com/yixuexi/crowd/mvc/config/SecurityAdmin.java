package com.yixuexi.crowd.mvc.config;

import com.yixuexi.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.List;

/**
 * 考虑到Security框架提供的User对象，仅仅包含用户名和密码
 * 为了能够获取到原始Admin对象的，专门创建这个类，对User类进行拓展
 * @date: 2021/1/27   12:55
 * @author: 易学习
 */
public class SecurityAdmin extends User {

    private Admin originalAdmin;
    /**
     * 因为User没有空构造器，而子类有子类的空构造器在执行时会有 super()
     * 所以需要修改一个子类的构造器
     * @param originalAdmin 原始的admin对象
     * @param authorities 传入角色权限信息的集合
     */
    public SecurityAdmin(Admin originalAdmin, List<GrantedAuthority> authorities){
        // 调用父类的构造器
        super(originalAdmin.getLoginAcct(),originalAdmin.getUserPswd(),authorities);
        // 给本类原始的Admin 进行赋值
        this.originalAdmin = originalAdmin;
        // 擦除admin对象的密码
        getOriginalAdmin().setUserPswd(null);
    }

    /**
     * 获取原始admin对象的get方法
     * @return
     */
    public Admin getOriginalAdmin(){
        return originalAdmin;
    }
}
