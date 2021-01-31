package com.yixuexi.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yixuexi.crowd.entity.Admin;
import com.yixuexi.crowd.entity.AdminExample;
import com.yixuexi.crowd.exception.AdminErrorException;
import com.yixuexi.crowd.exception.AdminExistsException;
import com.yixuexi.crowd.exception.LoginFailedException;
import com.yixuexi.crowd.mapper.AdminMapper;
import com.yixuexi.crowd.service.api.AdminService;
import com.yixuexi.crowd.util.CrowdUtil;
import com.yixuexi.crowd.util.constant.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @date: 2021/1/10   22:36
 * @author: 易学习
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 有选择的更新用户
     * @param admin 用户
     * @param oldId 要更新的admin的id
     */
    @Override
    public void updateAdminSelective(Admin admin, Integer oldId) {
        // 设置admin对象的id
        admin.setId(oldId);
        // 对密码进行加密
        admin.setUserPswd(CrowdUtil.md5(admin.getUserPswd()));
        try{
            // 进行更新
            adminMapper.updateByPrimaryKeySelective(admin);
            System.err.println("------------"+admin);
        }catch (Exception e){
            // DuplicateKeyException 是spring的一个异常类，表示如果数据库中插入异常(因为唯一的原因)时抛出
            if(e instanceof DuplicateKeyException){
                // 如果更新的用户名已存在 则抛出异常
                throw new AdminExistsException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USR);
            }
        }
    }

    /**
     * 根据id查询用户
     * @param adminId id
     * @return
     */
    @Override
    public Admin getAdminById(Integer adminId) {
        Admin admin = adminMapper.selectByPrimaryKey(adminId);
        return admin;
    }

    /**
     * 用户保存，并且对loginAcct进行查询判断是否被占用
     * @param admin
     */
    @Override
    public void saveAdmin(Admin admin) {
        // 判断账号是否已被占用
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(admin.getLoginAcct());
        // 通过loginAcct字段来查询用户，如果查到了就说明此用户已经被占用了
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if ((admins.size() != 0)){
            throw new AdminExistsException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USR);
        }

        // 密码加密
        admin.setUserPswd(bCryptPasswordEncoder.encode(admin.getUserPswd()));
        // 用户创建时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        admin.setCreateTime(format);
        adminMapper.insert(admin);
    }
    @Override
    public List<Admin> getAll() {
        List<Admin> admins = adminMapper.selectByExample(null);
        return admins;
    }

    /**
     * 用户登录逻辑
     * @param loginAcct 账号
     * @param userPswd 密码
     * @return
     */
    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        // 1.根据登录账号查询Admin对象
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> admins = adminMapper.selectByExample(adminExample);

        // 2.判断Admin对象是否为null
        // ①如果是集合是null，或者集合中元素等于0个 那说明没有查到
        if (admins == null || admins.size() == 0){
            throw new AdminErrorException(CrowdConstant.MESSAGE_ACCT_IS_NULL);
        }
        // ②一般是不会出现这个问题的，不可能有两个loginAcct相同
        if (admins.size() > 1){
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_ACCT_NOT_UNIQUE);
        }
        Admin admin = admins.get(0);
        if (admin == null){
            // 3.如果为null则抛出异常 【账号不存在!】
            throw  new AdminErrorException(CrowdConstant.MESSAGE_ACCT_IS_NULL);
        }
        // 4.如果存在,将密码从数据库中取出来
        String passwordDB = "";
        passwordDB = admin.getUserPswd();

        // 5.将表单提交的明文密码进行加密
        String md5Str = CrowdUtil.md5(userPswd);
        // 6.对密码进行比较，不一致抛异常，一致返回Admin对象
        // ①这里使用Objects工具类提供的工具方法
        if (Objects.equals(md5Str,passwordDB)){
            return admin;
        }
        // 抛出异常 【账号或密码不正确】
        throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);

    }

    /**
     * 分页查询逻辑
     * @param keyword 需要模糊的字段
     * @param pageNum 第几页
     * @param pageSize 一页几条
     * @return
     */
    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 1.调用pageHelper静态方法 开启分页功能
        PageHelper.startPage(pageNum,pageSize);
        // 2.进行查询
        List<Admin> admins = adminMapper.selectAdminByKeyWord(keyword);
        // 3.封装到PageInfo中，PageInfo类里面封装了总页数，总条数...
        return new PageInfo(admins);
    }

    /**
     * 根据id删除
     * @param adminId id
     */
    @Override
    public Integer remove(Integer adminId) {
        return adminMapper.deleteByPrimaryKey(adminId);
    }

    /**
     * 保存adminId新关系
     * @param adminId
     */
    @Override
    public void saveAdminRoleRelationship(Integer adminId,List<Integer> roleIdList) {
        // 为了简化操作，直接将该adminId 所有的关系都删掉，然后再去插入前端发来的新关系
        // 删除该admin的关系
        adminMapper.deleteOldRelationship(adminId);

        // 进行判断 如果roleIdList为空的话 就不进行保存了
        if (roleIdList != null && roleIdList.size() > 0){
            // 保存他新的关系
            adminMapper.insertNewRelationship(adminId,roleIdList);
        }
    }

    /**
     * 根据loginAcct查询到admin对象
     * @param loginAcct
     * @return
     */
    @Override
    public Admin getAdminByLoginAcct(String loginAcct) {
        Admin admin = adminMapper.selectAdminByLoginAcct(loginAcct);
        return admin;
    }
}

