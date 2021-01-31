package com.yixuexi.crowd.service.impl;

import com.yixuexi.crowd.entity.Auth;
import com.yixuexi.crowd.mapper.AuthMapper;
import com.yixuexi.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date: 2021/1/21   12:41
 * @author: 易学习
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;

    /**
     * 获得所有auth
     * @return
     */
    @Override
    public List<Auth> getAll() {
        return authMapper.selectByExample(null);
    }

    @Override
    public List<String> getAssignAuthByAdminId(Integer adminId) {
        return authMapper.selectAssignAuthByAdminId(adminId);
    }
}
