package com.CarManagement.CarMan.services;

import com.CarManagement.CarMan.model.User;
import com.CarManagement.CarMan.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//@Transactional
public class UsersDetailsServicesImpl implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(UsersDetailsServicesImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getCurrentUser() {
        // Get the Authentication object from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            // Check if the principal is a UserDetails object
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                // Find the user in the database based on the username
                return userRepository.findByUsername(userDetails.getUsername());
            }
        }

        return null;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}



