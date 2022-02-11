package com.example.demo.mapperTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserDTO;
import com.example.demo.user.User;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MapperTest {

    @Autowired UserMapper userMapper;
      
    private Logger log = LoggerFactory.getLogger(MapperTest.class);

    @Test
    public void testUserToUserDTO() {
        User user = new User("saad", LocalDate.of(2000,05,04), "France", null, null);
        log.info("" + user.toString());
        UserDTO userDTO = userMapper.userToUserDTO(user);
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getBirth(), userDTO.getBirth());
        assertEquals(user.getCountry(), userDTO.getCountry());
        assertEquals(user.getGender(), userDTO.getGender());
    }

    @Test
    public void testUserDTOToUser() {
        UserDTO userDTO = new UserDTO("saad", LocalDate.of(2000,05,04), "France", null, null);
        log.info("" + userDTO.toString());
        User user = userMapper.userDTOToUser(userDTO);
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getBirth(), userDTO.getBirth());
        assertEquals(user.getCountry(), userDTO.getCountry());
        assertEquals(user.getGender(), userDTO.getGender());

    }
    
}
