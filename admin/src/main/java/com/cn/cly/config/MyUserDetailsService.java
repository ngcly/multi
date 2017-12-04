package com.cn.cly.config;

import com.cn.cly.AdminService;
import com.cn.cly.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chen on 2017/6/23.
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.findUserByName(username).orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
        Set<GrantedAuthority> authorities = new HashSet<>();
        admin.getRoleList().forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getRole())));
        //这里使用securityAdmin 方便后面可以直接获取当前用户实体
        SecurityAdmin user = new SecurityAdmin(admin);
        return user;
    }
}
