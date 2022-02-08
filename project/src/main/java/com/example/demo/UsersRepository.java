package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersRepository {
    @Autowired RegistrationService registrationService;

    private Map<String, User> users = new HashMap<>();
    private Logger log = LoggerFactory.getLogger(RegistrationConsumer.class);

    public synchronized void put(User user) {
        users.put(user.getName(), user);
    }

    public synchronized User getById(String id) {
        User user = users.get(id);
        registrationService.checkNull(user);
        log.info("User found : " + user.toString());
        return user;
    }
}
