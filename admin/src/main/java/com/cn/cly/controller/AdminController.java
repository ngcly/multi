package com.cn.cly.controller;

import com.cn.cly.AdminService;
import com.cn.cly.config.SecurityAdmin;
import com.cn.cly.dao.AdminRepository;
import com.cn.cly.entity.Admin;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping("/login")
    public String login(){
//        Admin admin = adminService.findUserByName("admin").get();
//        BCryptPasswordEncoder bc=new BCryptPasswordEncoder(4);//将密码加密
//        admin.setPassword(bc.encode("123456"));
//        adminRepository.save(admin);
        return "login";
    }

    //  @PreAuthorize("hasAuthority()") //方法调用前判断是否有权限
//  @PostAuthorize("returnObject.id%2==0") 方法调用完后判断 若为false则无权限  基本用不上
//  @PostFilter("filterObject.id%2==0") 对返回结果进行过滤  filterObject内置为返回对象
//  @PreFilter(filterTarget="ids", value="filterObject%2==0") 对方法参数进行过滤 如有多参则指定参数 ids为其中一个参数
    @RequestMapping("/home")
    public String index(){
        //获取当前用户
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof SecurityAdmin) {
            String username = ((SecurityAdmin)principal).getUsername();
        } else {
            String username = principal.toString();
        }
        return "index";
    }

    @RequestMapping("/kaptcha")
    public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception{
        byte[] captchaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute("validateCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }


    @RequestMapping("hehe")
    // 这两个底层实现一样  唯一区别就是hasRole默认添加 ROLE_ 前缀
//    @PreAuthorize("hasAuthority('ROLE_admin')")
//    @PreAuthorize("hasRole('admin')")
    @PreAuthorize("hasPermission('','sys:user')")
    public String test(){
        return "heh";
    }

}
