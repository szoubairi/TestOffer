package com.example.demo.repository;

import org.springframework.stereotype.Service;
import com.example.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Service
public interface UsersRepository extends JpaRepository<User, String> {
    
}
