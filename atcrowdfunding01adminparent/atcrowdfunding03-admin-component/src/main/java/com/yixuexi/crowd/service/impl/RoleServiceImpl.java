package com.yixuexi.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.yixuexi.crowd.entity.Role;
import com.yixuexi.crowd.entity.RoleExample;
import com.yixuexi.crowd.exception.RoleIsExistsException;
import com.yixuexi.crowd.mapper.RoleMapper;
import com.yixuexi.crowd.service.api.RoleService;
import com.yixuexi.crowd.util.constant.CrowdConstant;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @date: 2021/1/14   20:48
 * @author: 易学习
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据名字查询Role
     * @param role
     * @return
     */
    @Override
    public List<Role> getRoleByName(Role role) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andNameEqualTo(role.getName());
        List<Role> list = roleMapper.selectByExample(roleExample);
        return list;
    }

    /**
     *
     * @param pageNum 第几页
     * @param pageSize 每页几个
     * @param keyword 关键字
     * @return
     */
    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        // 1.开启分页
        PageHelper.startPage(pageNum, pageSize);
        // 2.查询
        List<Role> roles = roleMapper.selectRoleByKeyword(keyword);
        // 3.得到pageInfo对象
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        return pageInfo;
    }

    @Override
    public Integer saveRole(Role role) {
        List<Role> list = this.getRoleByName(role);

        if (list.size() != 0){
            throw new RoleIsExistsException(CrowdConstant.MESSAGE_ROLE_IS_EXISTS);
        }
        return roleMapper.insert(role);
    }

    /**
     * 角色更新，对更新的角色进行判断是否已存在
     * @param role
     * @return
     */
    @Override
    public Integer updateRole(Role role) {
        List<Role> list = this.getRoleByName(role);

        if (list.size() != 0){
            throw new RoleIsExistsException(CrowdConstant.MESSAGE_ROLE_IS_EXISTS);
        }
        return roleMapper.updateByPrimaryKey(role);

    }

    @Override
    public Integer removeRoleByIds(List<Integer> roleIds) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andIdIn(roleIds);
        int i = roleMapper.deleteByExample(roleExample);
        return i;
    }

    /**
     * 获取admin已经取得的role
     * @param adminId admin id
     * @return
     */
    @Override
    public List<Role> getAssignAdminRole(Integer adminId) {
        List<Role> list = roleMapper.selectAssignRole(adminId);
        return list;
    }
    /**
     * 获取admin没有取得的role
     * @param adminId admin id
     * @return
     */
    @Override
    public List<Role> getUnAssignAdminRole(Integer adminId) {
        List<Role> list = roleMapper.selectUnAssignRole(adminId);
        return list;
    }

    @Override
    public List<Integer> getAssignAuthByRoleId(Integer roleId) {
        return roleMapper.selectAssignAuthByRoleId(roleId);
    }

    @Override
    public void saveRoleAuthRelationship(Map<String, List<Integer>> map) {
        //先将该roleId所有的关系删除
        List<Integer> list = map.get("roleId");
        Integer roleId = list.get(0);
        roleMapper.deleteRoleAuthByRoleId(roleId);
        // 拿到权限id集合
        List<Integer> authIds = map.get("authIdArray");
        // 判断，如果为0则 把新关系都新增进去
        if (authIds != null && authIds.size() > 0) {
            roleMapper.insertRoleAuthRelationship(authIds,roleId);
        }

    }
}
