package com.yixuexi.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.yixuexi.crowd.entity.Role;
import com.yixuexi.crowd.service.api.RoleService;
import com.yixuexi.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @date: 2021/1/14   20:49
 * @author: 易学习
 */
@Controller
public class RoleHandler {
    @Autowired
    private RoleService roleService;

    /**
     * 角色查询和带关键字的查询路由
     * @param pageNum 第几页
     * @param pageSize 每页几个
     * @param keyword 关键字
     * @return 返回一个ResultEntity里面封装了结果和数据
     */
    @PreAuthorize("hasRole('部长')")
    @ResponseBody
    @RequestMapping(value = "/role/get/page/info.json",method = RequestMethod.POST)
    public ResultEntity<PageInfo<Role>> getPageInfo(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "") String keyword

    ){
        PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);
        return ResultEntity.successWithData(pageInfo);
    }

    /**
     * 角色保存路由
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/role/save.json",method = RequestMethod.POST)
    public ResultEntity<String> saveRole(Role role){
        roleService.saveRole(role);
        return ResultEntity.successWithoutData();
    }

    /**
     * 角色更新路由
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/update.json")
    public ResultEntity<String> updateRole(Role role){
        System.out.println(role);
        Integer integer = roleService.updateRole(role);
        return ResultEntity.successWithoutData();

    }

    /**
     * 将批量删除和单条删除合并到一起处理
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/remove/by/role/id/array.json")
    public ResultEntity<String> deleteRole(@RequestBody List<Integer> roleIdList){
        roleService.removeRoleByIds(roleIdList);
        return ResultEntity.successWithoutData();
    }



}
