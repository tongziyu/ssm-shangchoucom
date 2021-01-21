package com.zzk.atcrowdfunding.service.api;

import com.zzk.atcrowdfunding.entity.Admin;

import java.util.List;

/**
 * @author zzk
 * @create 2020-03-24 15:42
 */
public interface AdminService {
    void saveAdmin(Admin admin);

    List<Admin> getAll();
}
