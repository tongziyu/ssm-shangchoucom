package com.yixuexi.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.yixuexi.crowd.entity.Admin;

import java.util.List;

/**
 * @date: 2021/1/10   22:35
 * @author: 易学习
 */
public interface AdminService {
    /**
     * 保存一个用户
     * @param admin
     */
    void saveAdmin(Admin admin);

    /**
     * 查询所有用户
     * @return
     */
    List<Admin> getAll();

    /**
     * 用户登录验证
     * @param loginAcct 账号
     * @param userPswd 密码
     * @return 返回admin对象
     */
    Admin getAdminByLoginAcct(String loginAcct, String userPswd);

    /**
     * 分页查询，并且模糊查询
     * @param keyword 需要模糊的字段
     * @param pageNum 第几页
     * @param pageSize 一页几条
     * @return
     */
    PageInfo<Admin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);

    /**
     * 根据id删除
     * @param adminId id
     * @return 被影响的行数
     */
    Integer remove(Integer adminId);

    /**
     * 根据id查询用户
     * @param adminId id
     * @return admin对象
     */
    Admin getAdminById(Integer adminId);

    /**
     * 有选择的更新用户
     * @param admin 用户
     * @param oldId
     */
    void updateAdminSelective(Admin admin, Integer oldId);

    /**
     * 保存adminId旧的关系
     * @param adminId
     * @param roleIdList
     */
    void saveAdminRoleRelationship(Integer adminId,List<Integer> roleIdList);

    /**
     * 通过loginAcct 来查询出来admin对象
     * @param loginAcct
     * @return
     */
    Admin getAdminByLoginAcct(String loginAcct);
}
