package com.CarManagement.CarMan.services;

import com.CarManagement.CarMan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.CarManagement.CarMan.repository.UserRepository;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class SignupServiceImpl implements SignupService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void saveUser(User user) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }}
