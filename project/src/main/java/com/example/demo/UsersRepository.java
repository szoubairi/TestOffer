package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.data.repository.CrudRepository;

@Service
public interface UsersRepository extends CrudRepository<User, String> {
    
}
