package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationConsumer {

    private Logger log = LoggerFactory.getLogger(RegistrationConsumer.class);

    @Autowired RegistrationService registrationService;
    @Autowired UsersRepository usersRepo;    

    public void register(User user) {
        log.info("Receiving registration " + user.toString());
        if(registrationService.validUser(user)){
            usersRepo.put(user);
            log.info("User added successfully!");
        }
        else{
            log.info("User must be an adult French!");
        }
    }

}
