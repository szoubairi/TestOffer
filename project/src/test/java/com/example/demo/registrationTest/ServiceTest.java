package com.example.demo.registrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void getUserByIdTest() {
        
        User response = new User("saad", LocalDate.of(2000, 04, 05), "France", null, null);
        UserDTO expectedResponse = new UserDTO("saad", LocalDate.of(2000, 04, 05), "France", null, null);
        
        Mockito.when(usersRepo.findById(Mockito.anyString())).thenReturn(Optional.of(response));
        Mockito.when(userMapper.userToUserDTO(Mockito.any(User.class))).thenReturn(expectedResponse);

        UserDTO actualResponse = registrationService.getUserById("saad").get();

        verify(usersRepo).findById("saad");
        verify(userMapper).userToUserDTO(Mockito.any(User.class));
        
        assertEquals(expectedResponse.getName(), actualResponse.getName());
        assertEquals(expectedResponse.getBirth(), actualResponse.getBirth());
    }



    @Test
    public void registerSuccessTest(){

        User response = new User("saad", LocalDate.of(2000, 04, 05), "France", null, null);
        UserDTO expectedResponse = new UserDTO("saad", LocalDate.of(2000, 04, 05), "France", null, null);

        Mockito.when(userMapper.userDTOToUser(Mockito.any(UserDTO.class))).thenReturn(response);
        Mockito.when(usersRepo.save(Mockito.any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        registrationService.register(expectedResponse);
        verify(usersRepo).save(Mockito.any(User.class));
    }
}
