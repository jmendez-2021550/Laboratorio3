package com.jeremymendez.sportfinal.service;

import com.jeremymendez.sportfinal.model.User;
import jakarta.persistence.Id;

import java.util.List;

public interface UserService {
    List<User>getAllUsers();
    User getUserById (Integer id);
    User saveUser(User user);
    User updateUser(Integer id, User user);
    void deleteUser(Integer id);
}

