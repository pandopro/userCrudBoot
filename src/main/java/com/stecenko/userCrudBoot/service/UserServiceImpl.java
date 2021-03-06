package com.stecenko.userCrudBoot.service;

import com.stecenko.userCrudBoot.dao.RoleRepository;
import com.stecenko.userCrudBoot.dao.UserRepository;
import com.stecenko.userCrudBoot.model.User;
import com.stecenko.userCrudBoot.model.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public void add(UserDTO userDTO) {
        User user = new User();
        UserDTO.userDTOtoUser(userDTO, user, roleRepository.allRoles());
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User get(long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void update(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(userDTO.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDTO.userDTOtoUser(userDTO, user, roleRepository.allRoles());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.loadUserByUsername(email);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return (List<User>) userRepository.findAll();
    }

    //

}
