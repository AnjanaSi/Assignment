package com.todoapp.todoApp.service;

import org.springframework.stereotype.Service;

import com.todoapp.todoApp.entity.User;
import com.todoapp.todoApp.utils.RegisterResponse;
import com.todoapp.todoApp.utils.LoginRequest;
import com.todoapp.todoApp.utils.RegisterRequest;

@Service
public interface UserService {
    RegisterResponse registerUser(RegisterRequest registerRequest);
    String login(LoginRequest loginRequest);
    User findByEmail(String email);
    User findByUsername(String username);
    User getUserById(Long id);

}
