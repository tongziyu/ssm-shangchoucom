package com.yixuexi.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.yixuexi.crowd.entity.Role;

import java.util.List;

/**
 * @date: 2021/1/14   20:47
 * @author: 易学习
 */
public interface RoleService {
    /**
     * 模糊查询和分页查询 合到一起
     * @param pageNum 第几页
     * @param pageSize 每页几个
     * @param keyword 关键字
     * @return pageInfo对象
     */
    PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

    /**
     * 保存角色
     * @param role 角色
     * @return 受影响的行数
     */
    Integer saveRole(Role role);

    /**
     * 更新角色
     */
    Integer updateRole(Role role);

    /**
     * 通过名字查询角色
     * @param role
     * @return
     */
    List<Role> getRoleByName(Role role);

    /**
     * 根据id列表删除用户
     * @param roleId
     * @return
     */
    Integer removeRoleByIds(List<Integer> roleId);

    /**
     * 获取admin已经获得的角色
     * @param adminId admin id
     * @return
     */
    List<Role> getAssignAdminRole(Integer adminId);
    /**
     * 获取admin没有获得的角色
     * @param adminId admin id
     * @return
     */
    List<Role> getUnAssignAdminRole(Integer adminId);

    /**
     * 返回对应role角色的权限
     * @param roleId
     * @return
     */
    List<Integer> getAssignAuthByRoleId(Integer roleId);
}
