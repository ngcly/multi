package com.cn.controller;

import com.cn.model.UserInfo;
import com.cn.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author LinChen
 */
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public String listUsers(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            Model model) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Page<UserInfo> users = userService.findUsers(PageRequest.of(page, size, sort));

        model.addAttribute("users", users);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "users/list";
    }

    @GetMapping("/users/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new UserInfo());
        model.addAttribute("isEdit", false);
        return "users/form";
    }

    @GetMapping("/users/{id}/edit")
    public String editUserForm(@PathVariable Long id, Model model) {
        UserInfo user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        model.addAttribute("isEdit", true);
        return "users/form";
    }

    @PostMapping("/users")
    public String saveUser(@Valid @ModelAttribute UserInfo user,
                           BindingResult bindingResult,
                           Model model) {

        // 验证用户名唯一性
        if (userService.isUsernameExists(user.getUsername(), user.getId())) {
            bindingResult.rejectValue("username", "error.user", "用户名已存在");
        }

        // 验证邮箱唯一性
        if (userService.isEmailExists(user.getEmail(), user.getId())) {
            bindingResult.rejectValue("email", "error.user", "邮箱已存在");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", user.getId() != null);
            return "users/form";
        }

        userService.saveUserInfo(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "users/fragments :: user-table";
    }

    // HTMX 专用端点
    @GetMapping("/users/table")
    public String getUserTable(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            Model model) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Page<UserInfo> users = userService.findUsers(PageRequest.of(page, size, sort));

        model.addAttribute("users", users);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "users/fragments :: user-table";
    }
}
