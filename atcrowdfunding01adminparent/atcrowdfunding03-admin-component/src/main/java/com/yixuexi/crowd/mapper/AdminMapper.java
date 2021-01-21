package com.yixuexi.crowd.mapper;

import com.yixuexi.crowd.entity.Admin;
import com.yixuexi.crowd.entity.AdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    /**
     * 查询所有admin
     * @param keyword 要匹配的值
     * @return 返回所有用户
     */
    List<Admin> selectAdminByKeyWord(String keyword);

    /**
     * 删除旧的关系
     * @param adminId
     */
    void deleteOldRelationship(Integer adminId);

    /**
     * 保存新的关系
     * @param adminId
     * @param roleIdList
     */
    void insertNewRelationship(@Param("adminId") Integer adminId,@Param("roleIdList") List<Integer> roleIdList);
}