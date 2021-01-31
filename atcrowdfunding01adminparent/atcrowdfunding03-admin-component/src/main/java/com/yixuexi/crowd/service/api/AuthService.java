package com.yixuexi.crowd.service.api;

import com.yixuexi.crowd.entity.Auth;

import java.util.List;

/**
 * @date: 2021/1/21   12:41
 * @author: 易学习
 */
public interface AuthService {

    /**
     * 获得所有的auth
     * @return
     */
    List<Auth> getAll();

    /**
     * 根据adminId 获取权限
     * @param adminId
     * @return
     */
    List<String> getAssignAuthByAdminId(Integer adminId);
}
