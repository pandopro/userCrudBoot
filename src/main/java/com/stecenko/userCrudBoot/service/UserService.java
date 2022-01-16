package com.stecenko.userCrudBoot.service;

import com.stecenko.userCrudBoot.model.User;
import com.stecenko.userCrudBoot.model.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void add(UserDTO user);

    void delete(long id);

    User get(long id);

    void update(UserDTO user);

    List<User> listUsers();

}
