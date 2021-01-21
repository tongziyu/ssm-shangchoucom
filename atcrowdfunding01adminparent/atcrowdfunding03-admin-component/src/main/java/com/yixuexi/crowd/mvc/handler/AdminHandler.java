package com.yixuexi.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.yixuexi.crowd.entity.Admin;
import com.yixuexi.crowd.exception.DeleteOneSelfException;
import com.yixuexi.crowd.service.api.AdminService;
import com.yixuexi.crowd.util.constant.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @date: 2021/1/12   13:32
 * @author: 易学习
 */
@Controller
public class AdminHandler {
    @Autowired
    private AdminService adminService;

    /**
     * 用户登录的路由
     * @param loginAcct 账号
     * @param userPswd 密码
     * @param session session作用域
     * @return
     */
    @RequestMapping("/admin/do/login.html")
    public String doLogin(@RequestParam("loginAcct") String loginAcct,
                          @RequestParam("userPswd") String userPswd,
                          HttpSession session
                          ){
        // 这个方法如果返回一个admin对象则说明账号密码正确
        // 如果密码不正确，则上一级就已经抛出异常了
        Admin adminByLoginAcct = adminService.getAdminByLoginAcct(loginAcct, userPswd);
        // 将对象【用户信息】存到session作用域中
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, adminByLoginAcct);
        // return "admin-main" 这样是转发的形式到页面，每次用户F5刷新时，就又会提交一次,进行查询数据库，验证...
        // 使用重定向，因为admin-main.jsp是在/WEB-INF/ 所以就需要用view-controller 专门来配一个请求路径
        return "redirect:/admin/to/main/page.html";
    }

    /**
     * 用户退出登录
     * @param session
     * @return
     */
    @RequestMapping("/admin/do/logout.html")
    public String logout(HttpSession session){
        // 强制session失效
        session.invalidate();
        // 重定向发请求，被view-controller解析
        return "redirect:/admin/to/login/page.html";
    }

    /**
     * 分页查询路由
     * @param keyword 关键字
     * @param pageNum 当前页
     * @param pageSize 一页有几个
     * @param modelMap 模型
     * @return
     */
    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(
            // 如果请求中没有携带keyword的话 就是用默认值 空字符串
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            // 当前默认为第 1 页
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            // 当前默认页面显示 5 条
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            ModelMap modelMap
    ){
        // 查询出来
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        // 放到模型中
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,pageInfo);
        return "admin-page";
    }

    /***
     * rest风格 删除用户
     * ${admin.id}.html 转换到后端为 {adminId} 再使用@PathVariable 来接收一下
     * @param adminId
     * @return
     */
    @RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String removeAdmin(@PathVariable("adminId") Integer adminId,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("keyword") String keyword,
                              HttpServletRequest request
    ){
        // 拿到请求对象并得到session里面的admin的id进行判断
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        // 如果删除的记录和当前用户一致 抛出异常
        if (Objects.equals(adminId,admin.getId())){
            // 给出提示消息：请勿删除自己
            throw new DeleteOneSelfException(CrowdConstant.MESSAGE_DELETE_ONESELF_ERROR);
        }
        System.out.println(adminService.remove(adminId));

        // 删除完成后回到分页的页面。
        // 使用重定向，重定向回页面
        // 尝试方案1：直接转发到 admin-page.jsp 会无法显示分页数据  return "admin-age.jsp";
        // 尝试方案2：转发到 return "forward:/admin/get/page.html"; 删除完成后 再刷新会再此发一次请求 进行删除【浪费性能】

        // 尝试方案3：重定向到 /admin/get/page.html页面。
        // 同时为了保持原本所在的页面和查询关键词再附加 pageNum和keyword两个请求参数
        return "redirect:/admin/get/page.html?"+"keyword="+keyword+"&pageNum="+pageNum;

    }

    /**
     * 保存用户
     * @param admin 用户
     * @return 返回并且给一个最大页，让他去最后一页
     */
    @RequestMapping("/admin/save.html")
    public String saveAdmin(Admin admin){
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;
    }

    /**
     * 回显表单
     * @param adminId
     * @param request
     * @return
     */
    @RequestMapping("/admin/edit/{adminId}.html")
    public String editAdmin(@PathVariable("adminId") Integer adminId,HttpServletRequest request){
        Admin adminById = adminService.getAdminById(adminId);
        request.getSession().setAttribute("admin",adminById);
        return "admin-edit";
    }

    /**
     * 用户更新路由
     * @param admin
     * @param request
     * @return
     */
    @RequestMapping("/admin/to/update.html")
    public String updateAdmin(Admin admin,HttpServletRequest request){
        // 因为之前在session里面放了一个admin对象 现在获取出来 获取老的admin的id
        Admin oldAdmin =(Admin) request.getSession().getAttribute("admin");
        adminService.updateAdminSelective(admin,oldAdmin.getId());
        // 重定向回页面，避免重复的提交表单
        return "redirect:/admin/get/page.html?keyword="+admin.getLoginAcct();
    }
}
