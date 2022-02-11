package com.example.demo.registrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import com.example.demo.model.UserDTO;
import com.example.demo.service.RegistrationService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
public class ServiceTest {
    
    @Autowired private RegistrationService registrationService;
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


}
