package com.example.blogger.services.user;

import com.example.blogger.domain.user.Role;
import com.example.blogger.domain.user.User;
import com.example.blogger.domain.user.dto.UpdateUserDto;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String rolename);

    User getUser(String username);

    List<User> getUsers();

    User updateUser(UpdateUserDto userDto);

    void deleteUser(Long id);
}
