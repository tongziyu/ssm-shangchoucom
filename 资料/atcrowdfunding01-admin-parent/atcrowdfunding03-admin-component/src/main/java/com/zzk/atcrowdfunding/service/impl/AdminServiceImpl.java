package com.zzk.atcrowdfunding.service.impl;

import com.zzk.atcrowdfunding.entity.Admin;
import com.zzk.atcrowdfunding.mapper.AdminMapper;
import com.zzk.atcrowdfunding.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzk
 * @create 2020-03-24 15:43
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
    }

    public List<Admin> getAll() {
        return adminMapper.selectByExample(null);
    }
}
