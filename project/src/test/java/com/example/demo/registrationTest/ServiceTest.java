package com.example.demo.registrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.Optional;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserDTO;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.RegistrationService;
import com.example.demo.user.User;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
public class ServiceTest {

    @Mock 
    private UsersRepository usersRepo;
    @Mock 
    private UserMapper userMapper;
    @Spy 
    @InjectMocks
     private RegistrationService registrationService;
            
    @Test
    public void checkNullTest() {
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> registrationService.checkNull(null));
        assertEquals("404 NOT_FOUND \"Entity not found\"", thrown.getMessage());
    }

    @Test
    public void validUserTest() {
        UserDTO userDTO1 = new UserDTO("saad", LocalDate.of(2000,05, 04), "France", null, null);
        UserDTO userDTO2 = new UserDTO("saad", LocalDate.of(2005,05, 04), "France", null, null);
        UserDTO userDTO3 = new UserDTO("saad", LocalDate.of(2000,05, 04), "Maroc", null, null);
        UserDTO userDTO4 = new UserDTO("saad", LocalDate.of(2000,05, 04), null, null, null);

        assertEquals(true, registrationService.validUser(userDTO1));
        assertEquals(false, registrationService.validUser(userDTO2));
        assertEquals(false, registrationService.validUser(userDTO3));
        assertEquals(false, registrationService.validUser(userDTO4));
    }

    @Test
    public void getUserByIdTest() {
        
        User response = new User("saad", LocalDate.of(2000, 04, 05), "France", null, null);
        UserDTO expectedResponse = new UserDTO("saad", LocalDate.of(2000, 04, 05), "France", null, null);
        
        Mockito.when(usersRepo.findById(Mockito.anyString())).thenReturn(Optional.of(response));
        Mockito.when(userMapper.userToUserDTO(Mockito.any(User.class))).thenReturn(expectedResponse);
        Mockito.doNothing().when(registrationService).checkNull(Mockito.any(UserDTO.class));

        UserDTO actualResponse = registrationService.getUserById("saad");

        verify(registrationService).checkNull(Mockito.any(UserDTO.class));
        verify(usersRepo).findById("saad");
        verify(userMapper).userToUserDTO(Mockito.any(User.class));
        
        assertEquals(expectedResponse.getName(), actualResponse.getName());
        assertEquals(expectedResponse.getBirth(), actualResponse.getBirth());
    }

    @Test
    public void registerFailTest(){
        UserDTO expectedResponse = new UserDTO("saad", LocalDate.of(2000, 04, 05), "France", null, null);

        Mockito.doReturn(false).when(registrationService).validUser(Mockito.any(UserDTO.class));

        registrationService.register(expectedResponse);
        verify(usersRepo, never()).save(Mockito.any(User.class));
    }

    @Test
    public void registerSuccessTest(){

        User response = new User("saad", LocalDate.of(2000, 04, 05), "France", null, null);
        UserDTO expectedResponse = new UserDTO("saad", LocalDate.of(2000, 04, 05), "France", null, null);

        Mockito.doReturn(true).when(registrationService).validUser(Mockito.any(UserDTO.class));
        Mockito.when(userMapper.userDTOToUser(Mockito.any(UserDTO.class))).thenReturn(response);
        Mockito.when(usersRepo.save(Mockito.any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        registrationService.register(expectedResponse);
        verify(usersRepo).save(Mockito.any(User.class));
    }
}
