package com.stecenko.userCrudBoot.model;

import java.util.*;

public class UserDTO {
    private long id;
    private String name;
    private String lastname;
    private int age;
    private String email;
    private String password;
    private String roles;

    public static User userDTOtoUser(UserDTO userDTO, User user, List<Role> roles) {
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setLastname(userDTO.getLastname());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        Set<Role> userRoles = new HashSet<>();
        for (Role role : roles) {
            System.out.println("1)role "+role.getRole());
            if ( userDTO.getRoles() != null && userDTO.getRoles().contains(role.getRole())){
                userRoles.add(role);
                System.out.println("win "+role.toString());
            }
        }
        user.setRoles(userRoles);
//        Set<Role> roles = new HashSet();
//        if (userDTO.getRoles() != null && userDTO.getRoles().contains(",")) {
//            for (String role : userDTO.getRoles().split(",")) {
//                roles.add(new Role(role));
//            }
//            user.setRoles(roles);
//        } else if (userDTO.getRoles() != null) {
//            roles.add(new Role(userDTO.getRoles()));
//            user.setRoles(roles);
//        }
        return user;
    }

    public UserDTO() {
    }

    public UserDTO(String name, String lastname, int age, String email, String password, String roles) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDTO(long id, String name, String lastname, int age, String email, String password, String roles) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id && age == userDTO.age && Objects.equals(name, userDTO.name) && Objects.equals(lastname, userDTO.lastname) && Objects.equals(email, userDTO.email) && Objects.equals(password, userDTO.password) && Objects.equals(roles, userDTO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, age, email, password, roles);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
