package com.yixuexi.crowd.mvc.config;

import com.yixuexi.crowd.entity.Admin;
import com.yixuexi.crowd.entity.Role;
import com.yixuexi.crowd.service.api.AdminService;
import com.yixuexi.crowd.service.api.AuthService;
import com.yixuexi.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2021/1/27   13:33
 * @author: 易学习
 */
@Component
public class CrowdUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 1.根据账号名称查询admin对象
        Admin admin = adminService.getAdminByLoginAcct(s);

        // 2.获得adminId
        Integer id = admin.getId();

        // 3.通过adminId 获取角色列表
        List<Role> roles = roleService.getAssignAdminRole(id);

        // 4.通过adminId 获取对应角色的权限
        List<String> authList = authService.getAssignAuthByAdminId(id);

        // 5.创建集合对象用来保存GrantedAuthority
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 6.遍历角色集合，将角色添加到 authorities 列表中
        for (Role role : roles) {
            // 角色需要添加前缀
            String roleName = "ROLE_" + role.getName();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
            authorities.add(simpleGrantedAuthority);
        }
        // 7.遍历权限集合，将权限加入到 authorities
        for (String auth : authList) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(auth);
            authorities.add(simpleGrantedAuthority);
        }
        // 8.这里不使用security给的User 使用自己拓展的SecurityAdmin类
        // 封装SecurityAdmin对象
        SecurityAdmin securityAdmin = new SecurityAdmin(admin,authorities);
        return securityAdmin;
    }
}
