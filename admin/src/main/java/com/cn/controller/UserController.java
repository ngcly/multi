package com.cn.controller;

import com.cn.model.BreadcrumbItem;
import com.cn.model.UserInfo;
import com.cn.service.UserService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author LinChen
 */
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("pageTitle", "仪表盘");
        return "pages/dashboard";
    }

    @GetMapping("/users")
    public String listUsers(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            Model model, HtmxRequest htmxRequest) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Page<UserInfo> users = userService.findUsers(PageRequest.of(page, size, sort));

        model.addAttribute("users", users);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("pageTitle", "用户管理");
        model.addAttribute("breadcrumbs", Arrays.asList(
                new BreadcrumbItem("用户管理")
        ));
        // 如果是HTMX请求，只返回内容片段
        if (htmxRequest.isHtmxRequest()) {
            return "fragments/users :: user-table";
        }

        return "pages/users/list";
    }

    @GetMapping("/users/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new UserInfo());
        model.addAttribute("isEdit", false);
        model.addAttribute("pageTitle", "新增用户");
        model.addAttribute("breadcrumbs", Arrays.asList(
                new BreadcrumbItem("用户管理", "/admin/users"),
                new BreadcrumbItem("新增用户")
        ));
        return "pages/users/form";
    }

    @GetMapping("/users/{id}/edit")
    public String editUserForm(@PathVariable Long id, Model model) {
        UserInfo user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        model.addAttribute("isEdit", true);
        model.addAttribute("breadcrumbs", Arrays.asList(
                new BreadcrumbItem("用户管理", "/admin/users"),
                new BreadcrumbItem("编辑用户")
        ));
        return "pages/users/form";
    }

    @PostMapping("/users")
    public String saveUser(@Valid @ModelAttribute UserInfo user,
                           BindingResult bindingResult,
                           Model model) {

        if (userService.isUsernameExists(user.getUsername(), user.getId())) {
            bindingResult.rejectValue("username", "error.user", "用户名已存在");
        }

        if (userService.isEmailExists(user.getEmail(), user.getId())) {
            bindingResult.rejectValue("email", "error.user", "邮箱已存在");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("isEdit", user.getId() != null);
            model.addAttribute("pageTitle", user.getId() != null ? "编辑用户" : "新增用户");
            model.addAttribute("breadcrumbs", Arrays.asList(
                    new BreadcrumbItem("用户管理", "/admin/users"),
                    new BreadcrumbItem(user.getId() != null ? "编辑用户" : "新增用户")
            ));
            return "pages/users/form";
        }

        userService.saveUserInfo(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "fragments/users :: user-table";
    }

}
