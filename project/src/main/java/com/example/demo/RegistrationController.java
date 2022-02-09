package com.example.demo;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    
    @Autowired UsersRepository usersRepo;
    @Autowired RegistrationService registrationService;

    @GetMapping("/users/{id}")
    public Optional<User> getUser(@PathVariable String id){
        Optional<User> user = usersRepo.findById(id);
        registrationService.checkNull(user);
        return user;
    }

    @PostMapping
    public void postUser(@RequestBody User user){
        registrationService.register(user);
    }
}
