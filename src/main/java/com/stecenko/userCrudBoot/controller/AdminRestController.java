package com.stecenko.userCrudBoot.controller;

import com.stecenko.userCrudBoot.model.User;
import com.stecenko.userCrudBoot.model.UserDTO;
import com.stecenko.userCrudBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/admin")
public class AdminRestController {


    private UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public  ResponseEntity<Iterable<User>> getUsers() {
       // return userService.listUsers();
        return new ResponseEntity<Iterable<User>>(
                userService.listUsers(),
                HttpStatus.OK);
    }

    @PostMapping("/edit")

    public ResponseEntity editUser(UserDTO user) {
        userService.update(user);
      //  return user.getEmail();
        return new ResponseEntity(
                HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        //return id;
        return new ResponseEntity<>(
                id,
                HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(UserDTO user) {
        userService.add(user);
        System.out.println("------------------------------");
        System.out.println(user.toString());
        System.out.println("------------------------------");
        return new ResponseEntity<>(HttpStatus.OK);
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
