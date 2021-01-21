package com.yixuexi;


import com.yixuexi.crowd.entity.Admin;
import com.yixuexi.crowd.entity.Role;
import com.yixuexi.crowd.mapper.AdminMapper;
import com.yixuexi.crowd.mapper.RoleMapper;
import com.yixuexi.crowd.service.api.AdminService;
import com.yixuexi.crowd.util.CrowdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @date: 2021/1/10   15:08
 * @author: 易学习
 * 在类上标记必要的注解，spring整合junit
 * @ContextConfiguration: 加载Spring配置文件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml"})
public class SpringTest {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleMapper roleMapper;
    @Test
    public void testRoleSave(){
        for (int i = 1; i < 256; i ++){
            roleMapper.insert(new Role(null,"name"+i));
        }
    }

    /**
     * 测试数据库连接
     * @throws SQLException
     */
    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    /**
     * 测试dao对象是否注入成功
     */
    @Test
    public void testAdminMapper(){
        Admin admin = new Admin();

        admin.setLoginAcct("zhangsan");
        admin.setUserPswd(CrowdUtil.md5("0000"));
        admin.setUserName("tom");
        admin.setEmail("tom@qq.com");
        adminMapper.insert(admin);
    }

    /**
     * 测试查询，同时测试日志
     */
    @Test
    public void testSelect(){
        Admin admin = adminMapper.selectByPrimaryKey(1);
        // 传入当前类的class属性
        Logger logger = LoggerFactory.getLogger(SpringTest.class);
        logger.debug(admin.toString());
    }

    @Test
    public void testSaveAdmin(){
        Admin admin = new Admin();
        admin.setLoginAcct("jkghjkg");
        admin.setUserPswd("0000");
        admin.setUserName("tom");
        admin.setEmail("tom@qq.com");
        adminService.saveAdmin(admin);
    }

    /**
     * 测试md5加密
     */
    @Test
    public void testMd5(){
        String source = "123123";
        String s = CrowdUtil.md5(source);
        System.out.println(s);
    }

    /**
     * 往数据库中制造假数据
     */
    @Test
    public void testAddAdmin(){
        for (int i = 0; i < 238; i++){
            adminMapper.insert(new Admin(null,"loginAcct"+i,"userPswd"+i,"username"+1,"229832403@qq.com"+1,null));

        }
    }

    @Test
    public void testGetAdminByLoginAcct(){
    }
}
