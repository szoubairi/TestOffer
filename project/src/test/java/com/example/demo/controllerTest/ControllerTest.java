package com.example.demo.controllerTest;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import java.time.LocalDate;
import java.util.Optional;

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
import org.springframework.web.client.HttpClientErrorException.NotFound;


@SpringBootTest
public class ControllerTest {

    @Mock 
    private UsersRepository usersRepo;
    @Mock 
    private UserMapper userMapper;
    @Mock 
    private RegistrationService registrationService;
    @InjectMocks 
    private RegistrationController registrationController;

    @Test
    public void getUserTest() {

        UserDTO expectedResponse = new UserDTO("saad", LocalDate.of(2000, 04, 05), "France", null, null);
        Mockito.doReturn(Optional.of(expectedResponse)).when(registrationService).getUserById("saad");
        UserDTO actualResponse = registrationController.getUser("saad").getBody();
        verify(registrationService).getUserById("saad");
        assertEquals(expectedResponse.getName(), actualResponse.getName());
    }

    @Test
    public void getWrongUserTest() {

        Mockito.doReturn(Optional.empty()).when(registrationService).getUserById(Mockito.anyString());
        NotFound thrown = assertThrows(NotFound.class, () -> registrationController.getUser("clara"));
        assertEquals("404 Can't found user with id clara", thrown.getMessage());
    }


    @Test
    public void registerTest() {
        UserDTO expectedResponse = new UserDTO("saad", LocalDate.of(2000, 04, 05), "France", null, null);
        Mockito.doReturn(expectedResponse).when(registrationService).register(Mockito.any(UserDTO.class));
        registrationController.postUser(expectedResponse);
        verify(registrationService).register(Mockito.any(UserDTO.class));
    }
}
