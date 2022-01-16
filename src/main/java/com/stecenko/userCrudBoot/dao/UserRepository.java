package com.stecenko.userCrudBoot.dao;

import com.stecenko.userCrudBoot.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("from User u JOIN FETCH u.roles where u.email = (?1)")
    User loadUserByUsername(String email);
}
