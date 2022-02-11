package com.example.demo.mapper;

import com.example.demo.model.UserDTO;
import com.example.demo.user.User;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public UserDTO userToUserDTO(User user){
        if(user == null)
            return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setBirth(user.getBirth());
        userDTO.setCountry(user.getCountry());
        userDTO.setGender(user.getGender());
        userDTO.setPhone(user.getPhone());
        return userDTO;
    }

    public User userDTOToUser(UserDTO userDTO){
        if(userDTO == null)
            return null;
        User user = new User();
        user.setName(userDTO.getName());
        user.setBirth(userDTO.getBirth());
        user.setCountry(userDTO.getCountry());
        user.setGender(userDTO.getGender());
        user.setPhone(userDTO.getPhone());
        return user;
    }
}
