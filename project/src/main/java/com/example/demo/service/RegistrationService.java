package com.example.demo.service;

import java.util.Optional;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserDTO;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired UsersRepository usersRepo;    
    @Autowired UserMapper userMapper;


    public Optional<UserDTO> getUserById(String id){
        return usersRepo.findById(id).map(userMapper::userToUserDTO);
    }


    public UserDTO register(UserDTO user) {
        return userMapper.userToUserDTO(usersRepo.save(userMapper.userDTOToUser(user)));
    }
    
}
