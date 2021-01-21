package com.yixuexi.crowd.mvc.handler;

import com.yixuexi.crowd.entity.Menu;
import com.yixuexi.crowd.service.api.MenuService;
import com.yixuexi.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @date: 2021/1/17   21:08
 * @author: 易学习
 */
@Controller
public class MenuHandler {

    @Autowired
    private MenuService menuService;

    /**
     * 返回一颗树
     * @return
     */
    @ResponseBody
    @RequestMapping("/menu/get/whole/tree.json")
    public ResultEntity<Menu> getWholeTreeNew(){
        // 1.查询所有的节点
        List<Menu> menuList = menuService.getAll();
        // 2.声明一个父节点
        Map<Integer,Menu> menuMap = new HashMap<>(16);
        // 3.将所有的节点放入Map中
        for (Menu menu : menuList) {
            menuMap.put(menu.getId(),menu);
        }
        Menu root = null;
        // 4.遍历所有的节点，并找到父节点
        for (Menu menu : menuList) {
            // 父节点没有pid
            if (menu.getPid() == null){
                root = menu;
                continue;
            }
            // 5.该节点有父节点则去找到父节点
            Menu fatherMenu = menuMap.get(menu.getPid());
            // 6.将该子节点放入到父节点的children中
            fatherMenu.getChildren().add(menu);
        }
        // 返回一个根节点，就返回了一整颗树，因为根下面有 children属性...

        return ResultEntity.successWithData(root);
    }
    /**
     * 保存
     */
    @ResponseBody
    @RequestMapping("/menu/save.json")
    public ResultEntity<String> saveMenu(Menu menu){
        menuService.saveMenu(menu);
        return ResultEntity.successWithoutData();
    }
    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/menu/remove.json")
    public ResultEntity<Menu> deleteMenu(Menu menu){
        menuService.removeMenu(menu);
        return ResultEntity.successWithoutData();
    }
    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/menu/update.json")
    public ResultEntity<Menu> updateMenu(Menu menu){
        menuService.updateMenu(menu);
        return ResultEntity.successWithoutData();
    }
}
