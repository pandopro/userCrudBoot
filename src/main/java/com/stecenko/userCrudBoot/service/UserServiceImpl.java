package com.stecenko.userCrudBoot.service;

import com.stecenko.userCrudBoot.dao.RoleRepository;
import com.stecenko.userCrudBoot.dao.UserRepository;
import com.stecenko.userCrudBoot.model.Role;
import com.stecenko.userCrudBoot.model.User;
import com.stecenko.userCrudBoot.model.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
     //   Set<Role> roles = new HashSet();
        System.out.println("------------------------------");
        System.out.println(user.toString());
        System.out.println("------------------------------");
//        if (userDTO.getRoles() != null && userDTO.getRoles().contains(",")) {
//            for (String role : userDTO.getRoles().split(",")) {
//                roles.add(userRepository.getRole(role));
//            }
//            user.setRoles(roles);
//        } else if (userDTO.getRoles() != null) {
//            roles.add(userRepository.getRole(userDTO.getRoles()));
//            user.setRoles(roles)
//        }
     ;
//        сделать метод гет роль фор наме
//                после этого вставлять роль по имени
//                или передавать полностью роль через фронтенд
        // user
//        for (Role role : userRepository.allRoles()) {
//            if (role.getRole().equals(user.getRoles())) {
//                user.setRoles((Set<Role>) role);
//            }
//        }
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
        System.out.println("++++++++++++++ " + userDTO);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDTO.userDTOtoUser(userDTO, user, roleRepository.allRoles());
//            Set<Role> roles = new HashSet();
//
//            if (userDTO.getRoles() != null && userDTO.getRoles().contains(",")) {
//                for (String role : userDTO.getRoles().split(",")) {
//                    roles.add(userRepository.getRole(role));
//                }
//                user.setRoles(roles);
//            } else if (userDTO.getRoles() != null) {
//                roles.add(userRepository.getRole(userDTO.getRoles()));
//                user.setRoles(roles);
//            }
//
//            userRepository.save(user);
//        }
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
