package com.yixuexi.crowd.mapper;

import com.yixuexi.crowd.entity.Role;
import com.yixuexi.crowd.entity.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 根据关键字查询
     * @param keyword 关键字
     * @return 返回一个Role集合
     */
    List<Role> selectRoleByKeyword(String keyword);

    List<Role> selectAssignRole(Integer adminId);

    List<Role> selectUnAssignRole(Integer adminId);

    List<Integer> selectAssignAuthByRoleId(Integer roleId);

}