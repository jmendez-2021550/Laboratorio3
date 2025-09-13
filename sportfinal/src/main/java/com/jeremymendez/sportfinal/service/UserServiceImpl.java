package com.jeremymendez.sportfinal.service;

import com.jeremymendez.sportfinal.model.User;
import com.jeremymendez.sportfinal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Integer id, User user) {
        User existinUser = userRepository.findById(id).orElse(null);
        if (existinUser != null){
            existinUser.setFirstName(user.getFirstName());
            existinUser.setLastname(user.getLastname());
            existinUser.setEmail(user.getEmail());
            return userRepository.save(existinUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}

