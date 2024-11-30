package com.mycompany.ms.users.ControllerIntegrationTests;

import com.mycompany.ms.users.AbstractIntegrationTest;
import com.mycompany.ms.users.dto.UserCreationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class RegisterControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUser() throws Exception {
        UserCreationDto userCreationDto = new UserCreationDto();
        userCreationDto.setLogin("KIRILL");
        userCreationDto.setPassword("011101");
        userCreationDto.setBirthYear("2000-01-01 00:00:00");
        userCreationDto.setAbout("Hello IM KIRILL");
        String userProfile =  objectMapper.writeValueAsString(userCreationDto);

        mockMvc.perform(post("/api/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userProfile))
                .andExpect(status().isCreated());
    }
}