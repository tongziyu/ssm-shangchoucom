package com.zzk.atcrowdfunding.mvc.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzk.atcrowdfunding.entity.Admin;
import com.zzk.atcrowdfunding.entity.Student;
import com.zzk.atcrowdfunding.service.api.AdminService;
import com.zzk.atcrowdfunding.util.CrowdUtil;
import com.zzk.atcrowdfunding.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zzk
 * @create 2020-03-24 16:35
 */
@Controller
public class TestHandler {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/test/ssm.html")
    public String testSsm(Model model, HttpServletRequest request) {
        System.out.println(CrowdUtil.judgeRequestType(request));
        List<Admin> admins = adminService.getAll();
        model.addAttribute("admins", admins);
//        String s = null;
//        s.equals("abc");
        int i = 10 / 0;
        return "target";
    }

    @RequestMapping("/test/json.json")
    @ResponseBody
    public Admin testJson() {
        Admin admin = new Admin(1001, "jerry", "123456", "杰瑞", "jerry@qq.com", "now");
        return admin;
    }

    @ResponseBody
    @RequestMapping("/send/array.html")
    public String testReceiveArray(@RequestBody List<Integer> array) {
        array.forEach(System.out::println);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/send/obj.json")
    public ResultEntity<Student> testReceiveObj(@RequestBody Student student, HttpServletRequest request) {
        System.out.println(CrowdUtil.judgeRequestType(request));
        System.out.println(student);
        String s = null;
        s.equals("abc");
        ResultEntity<Student> resultEntity = ResultEntity.successWithData(student);
        return resultEntity;
    }
}
