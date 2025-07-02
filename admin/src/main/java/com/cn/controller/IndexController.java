package com.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author LinChen
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String dashboard() {
        return "index";
    }

}
