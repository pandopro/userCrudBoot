package com.stecenko.userCrudBoot.service;

import com.stecenko.userCrudBoot.model.Role;

import java.util.List;

public interface RoleService {
    Role getRole(String role);

    List<Role> allRoles();
}
