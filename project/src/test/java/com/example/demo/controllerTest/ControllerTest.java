package com.example.demo.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import com.example.demo.controller.RegistrationController;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserDTO;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.RegistrationService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ControllerTest {

    @Mock private UsersRepository usersRepo;
    @Mock private UserMapper userMapper;
    @Mock private RegistrationService registrationService;
    
    @InjectMocks RegistrationController registrationController;

    @Test
    public void getUserTest() {

        UserDTO expectedResponse = new UserDTO("saad", LocalDate.of(2000, 04, 05), "France", null, null);
        Mockito.doReturn(expectedResponse).when(registrationService).getUserById("saad");

        UserDTO actualResponse = registrationController.getUser("saad");
        
        verify(registrationService).getUserById("saad");

        assertEquals(expectedResponse.getName(), actualResponse.getName());
    }


    @Test
    public void registerTest() {
        UserDTO expectedResponse = new UserDTO("saad", LocalDate.of(2000, 04, 05), "France", null, null);
        Mockito.doNothing().when(registrationService).register(Mockito.any(UserDTO.class));

        registrationController.postUser(expectedResponse);

        verify(registrationService).register(Mockito.any(UserDTO.class));
    }

}
