package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegistrationService {
    private Logger log = LoggerFactory.getLogger(RegistrationConsumer.class);

    public void checkNull(User user){
        if(user == null){
            log.info("User not found ");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
        }
    }
    public boolean validUser(User user){
        if(user.getName() == null || user.getBirth() == null || user.getCountry() == null){
            log.info("Please indicate all fields (Name - Country - BirthDate)!");
            return false;
        }
        String country = user.getCountry();
        LocalDate birth = user.getBirth();
        LocalDateTime date = birth.atStartOfDay();
        return country.equals("France") && ChronoUnit.YEARS.between(date, LocalDateTime.now()) >= 18;
    }
    
}
