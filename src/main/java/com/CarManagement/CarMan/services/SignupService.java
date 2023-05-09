package com.CarManagement.CarMan.services;

import com.CarManagement.CarMan.model.User;

import java.io.IOException;

public interface SignupService {

    void saveUser(User user) throws IOException;
}
