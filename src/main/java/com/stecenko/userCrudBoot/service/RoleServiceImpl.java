package com.stecenko.userCrudBoot.service;

import com.stecenko.userCrudBoot.dao.RoleRepository;
import com.stecenko.userCrudBoot.model.Role;

import java.util.List;

public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRole(String role) {
        return roleRepository.getRole(role);
    }

    @Override
    public List<Role> allRoles() {
        return roleRepository.allRoles();
    }
}
