package com.CarManagement.CarMan.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsersDetailsServices {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}

