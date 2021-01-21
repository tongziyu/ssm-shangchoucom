package com.yixuexi.crowd.mvc.handler;

import com.yixuexi.crowd.entity.Auth;
import com.yixuexi.crowd.entity.Role;
import com.yixuexi.crowd.service.api.AdminService;
import com.yixuexi.crowd.service.api.AuthService;
import com.yixuexi.crowd.service.api.RoleService;
import com.yixuexi.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @date: 2021/1/18   21:05
 * @author: 易学习
 * 角色分配控制器
 */
@Controller
public class AssignHandler {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AuthService authService;

    /**
     * 将对应用户的角色数据，放到request中，并转发到assign-page.jsp页面
     * @param adminId
     * @param modelMap
     * @return
     */
    @RequestMapping("/assign/to/assign/role/page/.html")
    public String getAdminAssign(
            @RequestParam("adminId") Integer adminId,
            ModelMap modelMap

    ){
        // 获得用户已经获得的角色
        List<Role> assignAdminRole = roleService.getAssignAdminRole(adminId);
        // 获得用户没有获取的角色
        List<Role> unAssignAdminRole = roleService.getUnAssignAdminRole(adminId);
        //将获取的和没有获取的放到model中 ,放到model实质就是放到了request作用域中

        modelMap.addAttribute("assignRoleList",assignAdminRole);
        modelMap.addAttribute("unAssignRoleList",unAssignAdminRole);
        return "assign-page";
    }

    /**
     * 保存前端修改的用户角色信息
     * @param roleIdList
     * @return 重定向到admin-page页面
     */
    @RequestMapping("/assign/do/role/assign.html")
    public String saveAdminRoleRelationship(
            @RequestParam("adminId") Integer adminId,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword,
            // 允许不带值
            @RequestParam(value = "roleIdList",required = false) List<Integer> roleIdList){

        adminService.saveAdminRoleRelationship(adminId,roleIdList);

        return "redirect:/admin/get/page.html?"+"keyword="+keyword+"&pageNum="+pageNum;
    }

    /**
     * 回显
     */
    @ResponseBody
    @RequestMapping("/assign/get/assigned/auth/id/by/role/id.json")
    public ResultEntity<List<Integer>> getAssignAuthByRoleId(@RequestParam("roleId") Integer roleId){
        List<Integer> assignAuthByRoleId = roleService.getAssignAuthByRoleId(roleId);
        return ResultEntity.successWithData(assignAuthByRoleId);
    }


    /**
     * 返回auth数据
     * @return
     */
    @RequestMapping("/assign/get/all/auth.json")
    @ResponseBody
    public ResultEntity<List<Auth>> getAuthAllList(){
        List<Auth> all = authService.getAll();
        return ResultEntity.successWithData(all);
    }

}
