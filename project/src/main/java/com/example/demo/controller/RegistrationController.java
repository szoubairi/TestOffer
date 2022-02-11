package com.example.demo.controller;

import com.example.demo.model.UserDTO;
import com.example.demo.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    
    @Autowired RegistrationService registrationService;

    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable String id){
        return registrationService.getUserById(id);

    }

    @PostMapping
    public void postUser(@RequestBody UserDTO user){
        registrationService.register(user);
    }
}
