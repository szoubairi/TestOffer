package com.example.demo.mapper;

import com.example.demo.model.UserDTO;
import com.example.demo.user.User;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    @Autowired ModelMapper modelMapper;
    private Logger log = LoggerFactory.getLogger(UserMapper.class);
    public UserDTO userToUserDTO(User user)
    {
        log.info("" + user.toString());
        return user == null ? null : modelMapper.map(user, UserDTO.class);
    }

    public User userDTOToUser(UserDTO userDTO)
    {
        log.info("" + userDTO.toString());
        return userDTO == null ? null: modelMapper.map(userDTO, User.class);
    }
}
