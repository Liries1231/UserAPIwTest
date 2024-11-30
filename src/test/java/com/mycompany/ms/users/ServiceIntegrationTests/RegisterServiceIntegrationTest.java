package com.mycompany.ms.users.ServiceIntegrationTests;

import com.mycompany.ms.users.AbstractIntegrationTest;
import com.mycompany.ms.users.dto.UserCreationDto;
import com.mycompany.ms.users.entity.UserProfile;
import com.mycompany.ms.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegisterServiceIntegrationTest extends AbstractIntegrationTest {


    @Autowired
    private UserService userService;


    @Test
    void createUser() {
        UserCreationDto userCreationDto = new UserCreationDto();
        userCreationDto.setLogin("KIRILL");
        userCreationDto.setPassword("011101");
        userCreationDto.setBirthYear("2000-01-01 00:00:00");
        userCreationDto.setAbout("Hello IM KIRILL");
        UserProfile userProfile = userService.createUserWithProfile(userCreationDto);
        assertNotNull(userProfile);

    }
}
