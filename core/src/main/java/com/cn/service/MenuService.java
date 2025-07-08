package com.cn.service;

import com.cn.model.MenuDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LinChen
 */
@Service
public class MenuService {
    public List<MenuDTO> getMenusForCurrentUser() {
        List<MenuDTO> menus = new ArrayList<>();
        MenuDTO m1 = new MenuDTO();
        m1.setId(1L); m1.setName("用户管理"); m1.setPath("/admin/user");
        menus.add(m1);
        // 更多模拟菜单...
        return menus;
    }
}
