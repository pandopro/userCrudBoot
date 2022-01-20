package com.stecenko.userCrudBoot.dao;

import com.stecenko.userCrudBoot.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    @Query("from Role")
    List<Role> allRoles();
     @Query("from Role r where r.role = (?1)")
    Role getRole(String role);
}
