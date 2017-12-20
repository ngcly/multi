package com.cn.cly.controller;

import com.cn.cly.Util.MenuTreeUtil;
import com.cn.cly.config.SecurityAdmin;
import com.cn.cly.dao.PermissionRepository;
import com.cn.cly.entity.Admin;
import com.cn.cly.entity.Permission;
import com.cn.cly.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by chen on 2017/8/5.
 */
@Controller
public class HomeController {
    @Autowired
    PermissionRepository permissionRepository;

    /**
     * 默认根目录为spring data rest 配置文件根目录
     * 这里必须得配置个 / 不然所有静态文件全部不起作用
     * 只有security才这样 shiro正常 原因尚不知
     */
    @RequestMapping("/")
    public String index(Principal principal,Model model){
        SecurityAdmin securityAdmin = (SecurityAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Role> roleList = securityAdmin.getRoleList();
        List<Permission> menuList = new ArrayList<>();
        if(!"admin".equals(principal.getName())){
            roleList.forEach(role -> menuList.addAll(role.getPermissions()));
        }else {
            menuList.addAll(permissionRepository.findMenuAll());
        }
        model.addAttribute("menuList", MenuTreeUtil.makeTreeList(menuList));
        return "index";
    }

    /**
     * 登录页面
     * @return login.html
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
