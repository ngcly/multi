package com.cn.config;

import com.cn.model.MenuDTO;
import com.cn.service.MenuService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * @author LinChen
 */
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalViewConfig {
    private final MenuService menuService;

    @ModelAttribute("menuList")
    public List<MenuDTO> menuList() {
        return menuService.getMenusForCurrentUser();
    }

    @ModelAttribute("currentPath")
    public String getCurrentPath(HttpServletRequest request) {
        return request.getRequestURI();
    }
}

