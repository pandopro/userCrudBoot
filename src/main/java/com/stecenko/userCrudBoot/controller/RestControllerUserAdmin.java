package com.stecenko.userCrudBoot.controller;

import com.stecenko.userCrudBoot.model.User;
import com.stecenko.userCrudBoot.model.UserDTO;
import com.stecenko.userCrudBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestControllerUserAdmin {


    private UserService userService;

    @Autowired
    public RestControllerUserAdmin(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("admin/all")
    public Iterable<User> getUsers() {
        return userService.listUsers();
    }

    @PostMapping("admin/edit")

    public String editUser(UserDTO user) {
        userService.update(user);
        return user.getEmail();
    }

    @PostMapping("admin/delete")
    public Long deleteUser(Long id) {
        userService.delete(id);
        return id;
    }

    @PostMapping("admin/add")
    public String addUser(UserDTO user) {
        userService.add(user);
        return user.toString();
    }

    @PostMapping("admin/get")
    public User getUser(Long id) {
        return userService.get(id);
    }

}
