package com.zzk.atcrowdfunding.test;

import com.zzk.atcrowdfunding.entity.Admin;
import com.zzk.atcrowdfunding.mapper.AdminMapper;
import com.zzk.atcrowdfunding.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author zzk
 * @create 2020-03-23 22:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class MybatisTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Test
    public void testConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testInsertAdmin() {
        Admin admin = new Admin(null, "tom", "123123", "汤姆", "tom@qq.com", null);
        int count = adminMapper.insert(admin);
        System.out.println(count);
    }

    @Test
    public void testLogger() {
        Logger logger = LoggerFactory.getLogger(MybatisTest.class);
        // 2.根据不同日志级别打印日志
        logger.debug("Debug level!!!");
        logger.debug("Debug level!!!");
        logger.debug("Debug level!!!");

        logger.info("Info level!!!");
        logger.info("Info level!!!");
        logger.info("Info level!!!");

        logger.warn("Warn level!!!");
        logger.warn("Warn level!!!");
        logger.warn("Warn level!!!");

        logger.error("Error level!!!");
        logger.error("Error level!!!");
        logger.error("Error level!!!");
    }

    @Test
    public void testTx() {
        Admin admin = new Admin(null, "jerry", "123456", "杰瑞", "jerry@qq.com", null);
        adminService.saveAdmin(admin);
    }
}
