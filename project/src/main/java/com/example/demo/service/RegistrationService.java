package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserDTO;
import com.example.demo.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegistrationService {
    private Logger log = LoggerFactory.getLogger(RegistrationService.class);
    @Autowired UsersRepository usersRepo;    
    @Autowired UserMapper userMapper;

    public void checkNull(UserDTO user){
        if(user == null){
            log.info("User not found! ");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
        }
    }

    public UserDTO getUserById(String id){
        UserDTO user = userMapper.userToUserDTO(usersRepo.findById(id).get());
        checkNull(user);
        return user;    
    }

    public boolean validUser(UserDTO user){
        if(user.getName() == null || user.getBirth() == null || user.getCountry() == null){
            log.info("Please indicate all fields (Name - Country - BirthDate)!");
            return false;
        }
        String country = user.getCountry();
        LocalDateTime birth = user.getBirth().atStartOfDay();
        return country.equals("France") && ChronoUnit.YEARS.between(birth, LocalDateTime.now()) >= 18;
    }

    public void register(UserDTO user) {
        log.info("Receiving registration " + user.toString());
        if(validUser(user)){
            usersRepo.save(userMapper.userDTOToUser(user));
            log.info("User added successfully!");
        }
        else{
            log.info("User must be an adult French!");
        }
    }
    
}
