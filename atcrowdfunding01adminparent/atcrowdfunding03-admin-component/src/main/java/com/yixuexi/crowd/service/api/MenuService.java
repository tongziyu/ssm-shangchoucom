package com.yixuexi.crowd.service.api;

import com.yixuexi.crowd.entity.Menu;

import java.util.List;

/**
 * @date: 2021/1/17   20:56
 * @author: 易学习
 */
public interface MenuService {
    /**
     * 查找
     * @return
     */
    List<Menu> getAll();

    /**
     * 保存
     * @param menu
     */
    void saveMenu(Menu menu);

    /**
     * 删除
     */
    void removeMenu(Menu menu);

    /**
     * 修改
     * @param menu
     */
    void updateMenu(Menu menu);
}
