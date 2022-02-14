package com.example.demo.mapper;

import com.example.demo.model.UserDTO;
import com.example.demo.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    @Autowired ModelMapper modelMapper;

    public UserDTO userToUserDTO(User user){
        return user == null ? null : modelMapper.map(user, UserDTO.class);
    }

    public User userDTOToUser(UserDTO userDTO){
        return userDTO == null ? null: modelMapper.map(userDTO, User.class);
    }
}
