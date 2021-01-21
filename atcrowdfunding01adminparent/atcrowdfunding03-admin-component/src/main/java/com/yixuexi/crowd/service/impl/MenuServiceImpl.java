package com.yixuexi.crowd.service.impl;

import com.yixuexi.crowd.entity.Menu;
import com.yixuexi.crowd.mapper.MenuMapper;
import com.yixuexi.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date: 2021/1/17   20:57
 * @author: 易学习
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(null);
    }

    /**
     * 保存
     *@param menu
     */
    @Override
    public void saveMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    /**
     *删除
     * @param menu
     */
    @Override
    public void removeMenu(Menu menu) {
        menuMapper.deleteByPrimaryKey(menu.getId());
    }

    /**
     * 更新
     * @param menu
     */
    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }
}
