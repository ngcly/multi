package com.cn.cly.config;

import com.cn.cly.UserService;
import com.cn.cly.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by chen on 2017/6/23.
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByName(username).orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        user.getRoleList().forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getRole())));
        //这里使用securityUser 方便后面可以直接获取当前用户实体
        SecurityUser user1 = new SecurityUser(user);
        return user1;

        //这种方式无法获取当前用户的实体 只能拿到用户名
//        return new org.springframework.security.core.userdetails.User(
//                username, user.getPassword(),
//                true,//是否可用
//                true,//是否过期
//                true,//证书不过期为true
//                true,//账户未锁定为true
//                authorities);
    }
}