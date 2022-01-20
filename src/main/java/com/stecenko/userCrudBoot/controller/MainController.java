package com.stecenko.userCrudBoot.controller;

import com.stecenko.userCrudBoot.dao.RoleRepository;
import com.stecenko.userCrudBoot.model.Role;
import com.stecenko.userCrudBoot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private UserService userService;
    private RoleRepository roleService;

    public MainController(UserService userService, RoleRepository roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String mainPage() {
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin")
    public String adminGet(ModelMap model, HttpSession httpSession) {
        model.addAttribute("user", httpSession.getAttribute("user"));
        List<Role> allRoles = roleService.allRoles();
        model.addAttribute("roles", allRoles);
        return "admin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String user(ModelMap model, HttpSession httpSession) {
        model.addAttribute("user", httpSession.getAttribute("user"));
        List<Role> allRoles = new ArrayList<>();
        allRoles.add(new Role("ROLE_ADMIN"));
        allRoles.add(new Role("ROLE_USER"));
        model.addAttribute("roles", allRoles);
        return "admin";
    }
}

