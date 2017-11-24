package com.cn.cly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chen on 2017/8/5.
 */
@Controller
public class HomeController {

    /**
     * 默认根目录为spring data rest 配置文件根目录
     * 这里必须得配置个 / 不然所有静态文件全部不起作用
     * 只有security才这样 shiro正常 原因尚不知
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
