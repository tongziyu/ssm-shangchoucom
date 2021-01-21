package com.yixuexi.crowd.mvc.handler;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.yixuexi.crowd.entity.Admin;
import com.yixuexi.crowd.entity.ParamData;
import com.yixuexi.crowd.service.api.AdminService;
import com.yixuexi.crowd.util.CrowdUtil;
import com.yixuexi.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @date: 2021/1/11   1:33
 * @author: 易学习
 */
@Controller
public class TestHandler {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/test.html")
    public String test() {
        return "test";
    }


    @RequestMapping("/admin/all.html")
    public String all(ModelMap modelMap) {
        modelMap.addAttribute("adminList", adminService.getAll());

        return "list";
    }

    @ResponseBody
    @RequestMapping("/send/array/one.json")
    public String arrayOne(@RequestParam("array[]") int[] array) {
        for (int i : array) {
            System.out.println(i);
        }
        return "Success";
    }

    @ResponseBody
    @RequestMapping("/send/array/two.json")
    public String arrayTwo(ParamData paramData) {
        List<Integer> list = paramData.getArray();
        for (int i : list) {
            System.out.println(i);
        }
        return "Success";
    }

    @ResponseBody
    @RequestMapping("/send/array/three.json")
    public String arrayThree(@RequestBody List<Integer> aab, HttpServletRequest request) {
        for (Integer integer : aab) {
            System.out.println(integer);
        }
        boolean b = CrowdUtil.judgeRequestType(request);
        System.out.println(b ? "ajax" : "普通请求");
        return "success";
    }

    @ResponseBody
    @RequestMapping("/show/admin.json")
    public ResultEntity showAdmin() {
        List<Admin> all = adminService.getAll();
        return new ResultEntity(ResultEntity.SUCCESS, null, all);
    }

    @RequestMapping("/xxx.html")
    public void xxx(HttpServletRequest request) {
        boolean b = CrowdUtil.judgeRequestType(request);
        System.out.println(b ? "ajax" : "普通请求");
        int i = 10 / 0;


    }
}
