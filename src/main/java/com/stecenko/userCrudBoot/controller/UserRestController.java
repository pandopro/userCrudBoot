package com.stecenko.userCrudBoot.controller;

import com.stecenko.userCrudBoot.model.User;
import com.stecenko.userCrudBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        //   return userService.get(id);
        return new ResponseEntity<>(
                //  "Your age is " + calculateAge(yearOfBirth),
                userService.get(id),
                HttpStatus.OK);
    }
}
