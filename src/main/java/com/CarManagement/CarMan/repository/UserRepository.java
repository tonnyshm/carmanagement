package com.CarManagement.CarMan.repository;

import com.CarManagement.CarMan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
   // @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User findByUsername(String username);
}
