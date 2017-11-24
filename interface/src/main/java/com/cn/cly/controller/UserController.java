package com.cn.cly.controller;

import com.cn.cly.config.*;
import com.cn.cly.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.cn.cly.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TokenUtils tokenUtils;

    /**
     * 登录接口
     * @param modelMap
     * @return
     */
    @PostMapping("/login")
    public ModelMap login(@RequestBody ModelMap modelMap){
        String username = String.valueOf(modelMap.get("username"));
        User user = userService.findUserByName(username).orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
        String password = String.valueOf(modelMap.get("password"));
        if(password.equals(user.getPassword())){
            TokenDetail tokenDetail = new TokenDetailImpl(username);
            String token = tokenUtils.generateToken(tokenDetail);
            Map<String,Object> info = new HashMap<>();
            info.put("token",token);
            info.put("userInfo",user);
            return ReturnUtil.Success(info);
        }else{
            return ReturnUtil.Error(511,"用户名或密码错误");
        }
    }

    @GetMapping("/list")
    public List<User> userList() {
        ModelMap map = new ModelMap();
        map.put("state","ok");
        List<User> users = userService.userList();
        map.put("data",users);
        return users;
    }
}
